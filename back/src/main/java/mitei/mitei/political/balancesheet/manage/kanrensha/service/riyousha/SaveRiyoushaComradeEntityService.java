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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaComradeDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SaveRiyoushaComradeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaOrgComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.user.InsertCombineUserRiyoushaLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaOrgComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeNameRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.ConvertPersonNameToAllNameUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 利用者仲間エンティティを保存するサービスクラス
 */
@Service
public class SaveRiyoushaComradeEntityService {

    /** 利用者APIユーザマスタRespoitory */
    @Autowired
    private RiyoushaComradeRepository riyoushaComradeRepository;

    /** 利用者APIユーザ連絡先Respoitory */
    @Autowired
    private RiyoushaComradeAccessRepository riyoushaComradeAccessRepository;

    /** 利用者APIユーザ住所Respoitory */
    @Autowired
    private RiyoushaComradeAddressRepository riyoushaComradeAddressRepository;

    /** 利用者APIユーザ名称Respoitory */
    @Autowired
    private RiyoushaComradeNameRepository riyoushaComradeNameRepository;

    /** 利用者APIユーザ組織個人紐づけRespoitory */
    @Autowired
    private RiyoushaOrgComradeRepository riyoushaOrgComradeRepository;

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
    public Integer practice(final SaveRiyoushaComradeCapsuleDto capsuleDto) {

        RiyoushaComradeDto comradeDto = capsuleDto.getRiyoushaComradeDto();
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        // 1) RiyoushaComradeEntity の保存
        // masterIdが0でない場合は、既存のエンティティを履歴化する
        Integer code = 1;
        boolean isNew = 0 == comradeDto.getRiyoushaComradeId();

        if (isNew) {
            // 悲観ロックでcodeを取得
            Optional<RiyoushaComradeEntity> optional = riyoushaComradeRepository
                    .findFirstByOrderByRiyoushaComradeCodeDesc();
            if (!optional.isEmpty()) {
                code += optional.get().getRiyoushaComradeCode();
            }

        } else {
            Optional<RiyoushaComradeEntity> optional = riyoushaComradeRepository
                    .findById(comradeDto.getRiyoushaComradeId());
            if (optional.isEmpty()) {
                throw new EmptyResultDataAccessException(1);
            } else {
                RiyoushaComradeEntity oldEntity = optional.get();
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                code = oldEntity.getRiyoushaComradeCode();
                riyoushaComradeRepository.save(oldEntity);
            }
        }

        // DTOから新しいエンティティを作成して保存
        // DTOからEntityへのフィールドマッピングを実装
        RiyoushaComradeEntity newComradeEntity = new RiyoushaComradeEntity();
        newComradeEntity.setRiyoushaComradeCode(code);
        newComradeEntity.setIsNotOrg(comradeDto.getIsNotOrg());
        // 個人か組織かで名称の取得方法がわかれる
        if (comradeDto.getIsNotOrg()) {
            newComradeEntity.setRiyoushaComradeName(
                    ConvertPersonNameToAllNameUtil.practiceName(comradeDto.getInputPersonNameDto()));
        } else {
            newComradeEntity.setRiyoushaComradeName(comradeDto.getInputOrgNameDto().getOrgName());
        }

        setTableDataHistoryUtil.practiceInsert(userDto, newComradeEntity);
        newComradeEntity.setRiyoushaComradeId(0); // auto increment明示

        RiyoushaComradeEntity savedComradeEntity = riyoushaComradeRepository.save(newComradeEntity);

        this.saveAccess(comradeDto, userDto, savedComradeEntity);

        this.saveAddress(comradeDto, userDto, savedComradeEntity);

        this.saveName(comradeDto, userDto, savedComradeEntity);

        // 新規の場合はユーザと利用者を紐づけ
        if (isNew) {
            // TODO 他人が編集している可能性を考慮する
            insertCombineUserRiyoushaLogic.practcie(userDto.getUserPersonCode(), UserRoleConstants.ROLE_COMRADE, code,
                    userDto);
        }

        // 過去データを履歴にして新しいデータを追加
        List<RiyoushaOrgComradeEntity> listOrgin = riyoushaOrgComradeRepository.findByRiyoushaOrgCodeAndIsLatest(
                comradeDto.getRiyoushaComradeCode(), SetTableDataHistoryUtil.INSERT_STATE);
        for (RiyoushaOrgComradeEntity entityPerson : listOrgin) {
            setTableDataHistoryUtil.practiceDelete(userDto, entityPerson);
        }
        riyoushaOrgComradeRepository.saveAll(listOrgin);

        // リストを紐づけに変換してすべて保存
        List<RiyoushaOrgComradeEntity> listOrgPerson = new ArrayList<>();

        for (RiyoushaComradeEntity entityPerson : comradeDto.getListPerson()) {
            listOrgPerson.add(this.createOrgCombineEntity(entityPerson, code, userDto));
        }
        riyoushaOrgComradeRepository.saveAllAndFlush(listOrgPerson);

        return savedComradeEntity.getRiyoushaComradeId();
    }

    private Integer saveAccess(final RiyoushaComradeDto comradeDto, final UserPersonLeastDto userDto,
            final RiyoushaComradeEntity savedComradeEntity) {

        // 2) RiyoushaComradeAccessEntity の保存
        // accessIdが0でない場合は、既存のエンティティを履歴化する
        if (comradeDto.getAccessId() != 0) {
            riyoushaComradeAccessRepository.findById(comradeDto.getAccessId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaComradeAccessRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成
        RiyoushaComradeAccessEntity newAccessEntity = new RiyoushaComradeAccessEntity();

        // DTOからEntityへのフィールドマッピングを実装
        BeanUtils.copyProperties(comradeDto.getInputAccessDto(), newAccessEntity);
        newAccessEntity.setRiyoushaComradeId(savedComradeEntity.getRiyoushaComradeId());
        newAccessEntity.setRiyoushaComradeCode(savedComradeEntity.getRiyoushaComradeCode());
        newAccessEntity.setRiyoushaComradeName(savedComradeEntity.getRiyoushaComradeName());

        setTableDataHistoryUtil.practiceInsert(userDto, newAccessEntity);
        return riyoushaComradeAccessRepository.save(newAccessEntity).getRiyoushaComradeAccessId();

    }

    private Integer saveAddress(final RiyoushaComradeDto comradeDto, final UserPersonLeastDto userDto,
            final RiyoushaComradeEntity savedComradeEntity) {

        // 3) RiyoushaComradeAddressEntity の保存
        // addressIdが0でない場合は、既存のエンティティを履歴化する
        if (comradeDto.getAddressId() != 0) {
            riyoushaComradeAddressRepository.findById(comradeDto.getAddressId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaComradeAddressRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成して保存
        RiyoushaComradeAddressEntity newAddressEntity = new RiyoushaComradeAddressEntity();
        // DTOからEntityへのフィールドマッピングを実装
        BeanUtils.copyProperties(comradeDto.getInputAddressDto(), newAddressEntity);
        newAddressEntity.setPostal1(comradeDto.getInputAddressDto().getPostalcode1());
        newAddressEntity.setPostal2(comradeDto.getInputAddressDto().getPostalcode2());
        newAddressEntity.setRiyoushaComradeId(savedComradeEntity.getRiyoushaComradeId());
        newAddressEntity.setRiyoushaComradeCode(savedComradeEntity.getRiyoushaComradeCode());
        newAddressEntity.setRiyoushaComradeName(savedComradeEntity.getRiyoushaComradeName());
        setTableDataHistoryUtil.practiceInsert(userDto, newAddressEntity);

        return riyoushaComradeAddressRepository.save(newAddressEntity).getRiyoushaComradeAddressId();

    }

    private Integer saveName(final RiyoushaComradeDto comradeDto, final UserPersonLeastDto userDto,
            final RiyoushaComradeEntity savedComradeEntity) {

        // 4) RiyoushaComradeNameEntity の保存
        // nameIdが0でない場合は、既存のエンティティを履歴化する
        if (comradeDto.getNameId() != 0) {
            riyoushaComradeNameRepository.findById(comradeDto.getNameId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaComradeNameRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成して保存
        RiyoushaComradeNameEntity newNameEntity = new RiyoushaComradeNameEntity();
        // DTOからEntityへのフィールドマッピングを実装
        if (comradeDto.getIsNotOrg()) {
            BeanUtils.copyProperties(comradeDto.getInputPersonNameDto(), newNameEntity);
        } else {
            BeanUtils.copyProperties(comradeDto.getInputOrgNameDto(), newNameEntity);
        }
        newNameEntity.setRiyoushaComradeId(savedComradeEntity.getRiyoushaComradeId());
        newNameEntity.setRiyoushaComradeCode(savedComradeEntity.getRiyoushaComradeId());
        newNameEntity.setRiyoushaComradeName(savedComradeEntity.getRiyoushaComradeName());
        newNameEntity.setIsLatest(comradeDto.getIsNotOrg());

        setTableDataHistoryUtil.practiceInsert(userDto, newNameEntity);
        return riyoushaComradeNameRepository.save(newNameEntity).getRiyoushaComradeNameId();

    }

    private RiyoushaOrgComradeEntity createOrgCombineEntity(final RiyoushaComradeEntity entityPerson,
            final Integer code, final UserPersonLeastDto userDto) {
        RiyoushaOrgComradeEntity entityCombine = new RiyoushaOrgComradeEntity();
        entityCombine.setRiyoushaPersonCode(entityPerson.getRiyoushaComradeCode());
        entityCombine.setRiyoushaOrgCode(code);
        setTableDataHistoryUtil.practiceInsert(userDto, entityCombine);
        entityCombine.setRiyoushaOrgComradeId(0);

        return entityCombine;
    }
}
