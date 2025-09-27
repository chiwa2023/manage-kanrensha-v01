package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SaveRiyoushaManagerCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaOrgManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.user.InsertCombineUserRiyoushaLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaOrgManagerRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerNameRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.ConvertPersonNameToAllNameUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 利用者仲間エンティティを保存するサービスクラス
 */
@Service
public class SaveRiyoushaManagerEntityService {

    /** 利用者APIユーザマスタRespoitory */
    @Autowired
    private RiyoushaManagerRepository riyoushaManagerRepository;

    /** 利用者APIユーザ連絡先Respoitory */
    @Autowired
    private RiyoushaManagerAccessRepository riyoushaManagerAccessRepository;

    /** 利用者APIユーザ住所Respoitory */
    @Autowired
    private RiyoushaManagerAddressRepository riyoushaManagerAddressRepository;

    /** 利用者APIユーザ名称Respoitory */
    @Autowired
    private RiyoushaManagerNameRepository riyoushaManagerNameRepository;

    /** 利用者APIユーザ組織個人紐づけRespoitory */
    @Autowired
    private RiyoushaOrgManagerRepository riyoushaOrgManagerRepository;

    /** テーブル履歴設定utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** ユーザ関連者紐づけ挿入Logic */
    @Autowired
    private InsertCombineUserRiyoushaLogic insertCombineUserRiyoushaLogic;

    /**
     * 利用者仲間エンティティをDBに新規・変更保存する
     *
     * @param capsuleDto 保存用データを含むカプセルDTO
     * @return 保存した利用者仲間エンティティのテーブルID
     */
    @Transactional
    public Integer practice(final SaveRiyoushaManagerCapsuleDto capsuleDto) {

        RiyoushaManagerDto managerDto = capsuleDto.getRiyoushaManagerDto();
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        // 1) RiyoushaManagerEntity の保存
        // masterIdが0でない場合は、既存のエンティティを履歴化する
        Integer code = 1;
        boolean isNew = 0 == managerDto.getRiyoushaManagerId();
        if (isNew) {
            // 悲観ロックでcodeを取得
            Optional<RiyoushaManagerEntity> optional = riyoushaManagerRepository
                    .findFirstByOrderByRiyoushaManagerCodeDesc();
            if (!optional.isEmpty()) {
                code += optional.get().getRiyoushaManagerCode();
            }

        } else {
            Optional<RiyoushaManagerEntity> optional = riyoushaManagerRepository
                    .findById(managerDto.getRiyoushaManagerId());
            if (optional.isEmpty()) {
                throw new EmptyResultDataAccessException(1);
            } else {
                RiyoushaManagerEntity oldEntity = optional.get();
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                code = oldEntity.getRiyoushaManagerCode();
                riyoushaManagerRepository.save(oldEntity);
            }
        }

        // DTOから新しいエンティティを作成して保存
        // DTOからEntityへのフィールドマッピングを実装
        RiyoushaManagerEntity newManagerEntity = new RiyoushaManagerEntity();
        newManagerEntity.setRiyoushaManagerCode(code);
        newManagerEntity.setIsNotOrg(managerDto.getIsNotOrg());
        // 個人か組織かで名称の取得方法がわかれる
        if (managerDto.getIsNotOrg()) {
            newManagerEntity.setRiyoushaManagerName(
                    ConvertPersonNameToAllNameUtil.practiceName(managerDto.getInputPersonNameDto()));
        } else {
            newManagerEntity.setRiyoushaManagerName(managerDto.getInputOrgNameDto().getOrgName());
        }

        setTableDataHistoryUtil.practiceInsert(userDto, newManagerEntity);
        newManagerEntity.setRiyoushaManagerId(0); // auto increment明示

        RiyoushaManagerEntity savedManagerEntity = riyoushaManagerRepository.save(newManagerEntity);

        this.saveAccess(managerDto, userDto, savedManagerEntity);

        this.saveAddress(managerDto, userDto, savedManagerEntity);

        this.saveName(managerDto, userDto, savedManagerEntity);

        // 新規の場合はユーザと利用者を紐づけ
        if(isNew) {
            // TODO 他人が編集している可能性を考慮する
            insertCombineUserRiyoushaLogic.practcie(userDto.getUserPersonCode(), UserRoleConstants.ROLE_MANAGER, code, userDto);
        }

        // 過去データを履歴にして新しいデータを追加
        List<RiyoushaOrgManagerEntity> listOrgin = riyoushaOrgManagerRepository.findByRiyoushaOrgCodeAndIsLatest(
                managerDto.getRiyoushaManagerCode(), SetTableDataHistoryUtil.INSERT_STATE);
        for (RiyoushaOrgManagerEntity entityPerson : listOrgin) {
            setTableDataHistoryUtil.practiceDelete(userDto, entityPerson);
        }
        riyoushaOrgManagerRepository.saveAll(listOrgin);

        // リストを紐づけに変換してすべて保存
        List<RiyoushaOrgManagerEntity> listOrgPerson = new ArrayList<>();

        for (RiyoushaManagerEntity entityPerson : managerDto.getListPerson()) {
            listOrgPerson.add(this.createOrgCombineEntity(entityPerson, code, userDto));
        }
        riyoushaOrgManagerRepository.saveAllAndFlush(listOrgPerson);

        return savedManagerEntity.getRiyoushaManagerId();
    }

    private Integer saveAccess(final RiyoushaManagerDto managerDto, final UserPersonLeastDto userDto,
            final RiyoushaManagerEntity savedManagerEntity) {

        // 2) RiyoushaManagerAccessEntity の保存
        // accessIdが0でない場合は、既存のエンティティを履歴化する
        if (managerDto.getAccessId() != 0) {
            riyoushaManagerAccessRepository.findById(managerDto.getAccessId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaManagerAccessRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成
        RiyoushaManagerAccessEntity newAccessEntity = new RiyoushaManagerAccessEntity();

        // DTOからEntityへのフィールドマッピングを実装
        BeanUtils.copyProperties(managerDto.getInputAccessDto(), newAccessEntity);
        newAccessEntity.setRiyoushaManagerId(savedManagerEntity.getRiyoushaManagerId());
        newAccessEntity.setRiyoushaManagerCode(savedManagerEntity.getRiyoushaManagerCode());
        newAccessEntity.setRiyoushaManagerName(savedManagerEntity.getRiyoushaManagerName());

        setTableDataHistoryUtil.practiceInsert(userDto, newAccessEntity);
        return riyoushaManagerAccessRepository.save(newAccessEntity).getRiyoushaManagerAccessId();

    }

    private Integer saveAddress(final RiyoushaManagerDto managerDto, final UserPersonLeastDto userDto,
            final RiyoushaManagerEntity savedManagerEntity) {

        // 3) RiyoushaManagerAddressEntity の保存
        // addressIdが0でない場合は、既存のエンティティを履歴化する
        if (managerDto.getAddressId() != 0) {
            riyoushaManagerAddressRepository.findById(managerDto.getAddressId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaManagerAddressRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成して保存
        RiyoushaManagerAddressEntity newAddressEntity = new RiyoushaManagerAddressEntity();
        // DTOからEntityへのフィールドマッピングを実装
        BeanUtils.copyProperties(managerDto.getInputAddressDto(), newAddressEntity);
        newAddressEntity.setPostal1(managerDto.getInputAddressDto().getPostalcode1());
        newAddressEntity.setPostal2(managerDto.getInputAddressDto().getPostalcode2());
        newAddressEntity.setRiyoushaManagerId(savedManagerEntity.getRiyoushaManagerId());
        newAddressEntity.setRiyoushaManagerCode(savedManagerEntity.getRiyoushaManagerCode());
        newAddressEntity.setRiyoushaManagerName(savedManagerEntity.getRiyoushaManagerName());
        setTableDataHistoryUtil.practiceInsert(userDto, newAddressEntity);

        return riyoushaManagerAddressRepository.save(newAddressEntity).getRiyoushaManagerAddressId();

    }

    private Integer saveName(final RiyoushaManagerDto managerDto, final UserPersonLeastDto userDto,
            final RiyoushaManagerEntity savedManagerEntity) {

        // 4) RiyoushaManagerNameEntity の保存
        // nameIdが0でない場合は、既存のエンティティを履歴化する
        if (managerDto.getNameId() != 0) {
            riyoushaManagerNameRepository.findById(managerDto.getNameId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaManagerNameRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成して保存
        RiyoushaManagerNameEntity newNameEntity = new RiyoushaManagerNameEntity();
        // DTOからEntityへのフィールドマッピングを実装
        if (managerDto.getIsNotOrg()) {
            BeanUtils.copyProperties(managerDto.getInputPersonNameDto(), newNameEntity);
        } else {
            BeanUtils.copyProperties(managerDto.getInputOrgNameDto(), newNameEntity);
        }
        newNameEntity.setRiyoushaManagerId(savedManagerEntity.getRiyoushaManagerId());
        newNameEntity.setRiyoushaManagerCode(savedManagerEntity.getRiyoushaManagerId());
        newNameEntity.setRiyoushaManagerName(savedManagerEntity.getRiyoushaManagerName());
        newNameEntity.setIsLatest(managerDto.getIsNotOrg());
        
        setTableDataHistoryUtil.practiceInsert(userDto, newNameEntity);
        return riyoushaManagerNameRepository.save(newNameEntity).getRiyoushaManagerNameId();

    }

    private RiyoushaOrgManagerEntity createOrgCombineEntity(final RiyoushaManagerEntity entityPerson,
            final Integer code, final UserPersonLeastDto userDto) {
        RiyoushaOrgManagerEntity entityCombine = new RiyoushaOrgManagerEntity();
        entityCombine.setRiyoushaPersonCode(entityPerson.getRiyoushaManagerCode());
        entityCombine.setRiyoushaOrgCode(code);
        setTableDataHistoryUtil.practiceInsert(userDto, entityCombine);
        entityCombine.setRiyoushaOrgManagerId(0);

        return entityCombine;
    }

}
