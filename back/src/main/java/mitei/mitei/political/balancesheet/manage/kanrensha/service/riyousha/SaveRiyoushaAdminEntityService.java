package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaAdminDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SaveRiyoushaAdminCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.user.InsertCombineUserRiyoushaLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminNameRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.ConvertPersonNameToAllNameUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 利用者仲間エンティティを保存するサービスクラス
 */
@Service
public class SaveRiyoushaAdminEntityService {

    /** 利用者APIユーザマスタRespoitory */
    @Autowired
    private RiyoushaAdminRepository riyoushaAdminRepository;

    /** 利用者APIユーザ連絡先Respoitory */
    @Autowired
    private RiyoushaAdminAccessRepository riyoushaAdminAccessRepository;

    /** 利用者APIユーザ住所Respoitory */
    @Autowired
    private RiyoushaAdminAddressRepository riyoushaAdminAddressRepository;

    /** 利用者APIユーザ名称Respoitory */
    @Autowired
    private RiyoushaAdminNameRepository riyoushaAdminNameRepository;

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
    public Integer practice(final SaveRiyoushaAdminCapsuleDto capsuleDto) {

        RiyoushaAdminDto adminDto = capsuleDto.getRiyoushaAdminDto();
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        // 1) RiyoushaAdminEntity の保存
        // masterIdが0でない場合は、既存のエンティティを履歴化する
        Integer code = 1;
        boolean isNew = 0 == adminDto.getRiyoushaAdminId();
        if (isNew) {
            // 悲観ロックでcodeを取得
            Optional<RiyoushaAdminEntity> optional = riyoushaAdminRepository
                    .findFirstByOrderByRiyoushaAdminCodeDesc();
            if (!optional.isEmpty()) {
                code += optional.get().getRiyoushaAdminCode();
            }

        } else {
            Optional<RiyoushaAdminEntity> optional = riyoushaAdminRepository
                    .findById(adminDto.getRiyoushaAdminId());
            if (optional.isEmpty()) {
                throw new EmptyResultDataAccessException(1);
            } else {
                RiyoushaAdminEntity oldEntity = optional.get();
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                code = oldEntity.getRiyoushaAdminCode();
                riyoushaAdminRepository.save(oldEntity);
            }
        }

        // DTOから新しいエンティティを作成して保存
        // DTOからEntityへのフィールドマッピングを実装
        RiyoushaAdminEntity newAdminEntity = new RiyoushaAdminEntity();
        newAdminEntity.setRiyoushaAdminCode(code);
        newAdminEntity.setIsNotOrg(adminDto.getIsNotOrg());
        // 個人か組織かで名称の取得方法がわかれる
        if (adminDto.getIsNotOrg()) {
            newAdminEntity.setRiyoushaAdminName(
                    ConvertPersonNameToAllNameUtil.practiceName(adminDto.getInputPersonNameDto()));
        } else {
            newAdminEntity.setRiyoushaAdminName(adminDto.getInputOrgNameDto().getOrgName());
        }

        setTableDataHistoryUtil.practiceInsert(userDto, newAdminEntity);
        newAdminEntity.setRiyoushaAdminId(0); // auto increment明示

        RiyoushaAdminEntity savedAdminEntity = riyoushaAdminRepository.save(newAdminEntity);

        this.saveAccess(adminDto, userDto, savedAdminEntity);

        this.saveAddress(adminDto, userDto, savedAdminEntity);

        this.saveName(adminDto, userDto, savedAdminEntity);

        // 新規の場合はユーザと利用者を紐づけ
        if(isNew) {
            // TODO 他人が編集している可能性を考慮する
            insertCombineUserRiyoushaLogic.practcie(userDto.getUserPersonCode(), UserRoleConstants.ROLE_ADMIN, code, userDto);
        }
        
        return savedAdminEntity.getRiyoushaAdminId();
    }

    private Integer saveAccess(final RiyoushaAdminDto adminDto, final UserPersonLeastDto userDto,
            final RiyoushaAdminEntity savedAdminEntity) {

        // 2) RiyoushaAdminAccessEntity の保存
        // accessIdが0でない場合は、既存のエンティティを履歴化する
        if (adminDto.getAccessId() != 0) {
            riyoushaAdminAccessRepository.findById(adminDto.getAccessId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaAdminAccessRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成
        RiyoushaAdminAccessEntity newAccessEntity = new RiyoushaAdminAccessEntity();

        // DTOからEntityへのフィールドマッピングを実装
        BeanUtils.copyProperties(adminDto.getInputAccessDto(), newAccessEntity);
        newAccessEntity.setRiyoushaAdminId(savedAdminEntity.getRiyoushaAdminId());
        newAccessEntity.setRiyoushaAdminCode(savedAdminEntity.getRiyoushaAdminCode());
        newAccessEntity.setRiyoushaAdminName(savedAdminEntity.getRiyoushaAdminName());

        setTableDataHistoryUtil.practiceInsert(userDto, newAccessEntity);
        return riyoushaAdminAccessRepository.save(newAccessEntity).getRiyoushaAdminAccessId();

    }

    private Integer saveAddress(final RiyoushaAdminDto adminDto, final UserPersonLeastDto userDto,
            final RiyoushaAdminEntity savedAdminEntity) {

        // 3) RiyoushaAdminAddressEntity の保存
        // addressIdが0でない場合は、既存のエンティティを履歴化する
        if (adminDto.getAddressId() != 0) {
            riyoushaAdminAddressRepository.findById(adminDto.getAddressId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaAdminAddressRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成して保存
        RiyoushaAdminAddressEntity newAddressEntity = new RiyoushaAdminAddressEntity();
        // DTOからEntityへのフィールドマッピングを実装
        BeanUtils.copyProperties(adminDto.getInputAddressDto(), newAddressEntity);
        newAddressEntity.setPostal1(adminDto.getInputAddressDto().getPostalcode1());
        newAddressEntity.setPostal2(adminDto.getInputAddressDto().getPostalcode2());
        newAddressEntity.setRiyoushaAdminId(savedAdminEntity.getRiyoushaAdminId());
        newAddressEntity.setRiyoushaAdminCode(savedAdminEntity.getRiyoushaAdminCode());
        newAddressEntity.setRiyoushaAdminName(savedAdminEntity.getRiyoushaAdminName());
        setTableDataHistoryUtil.practiceInsert(userDto, newAddressEntity);

        return riyoushaAdminAddressRepository.save(newAddressEntity).getRiyoushaAdminAddressId();

    }

    private Integer saveName(final RiyoushaAdminDto adminDto, final UserPersonLeastDto userDto,
            final RiyoushaAdminEntity savedAdminEntity) {

        // 4) RiyoushaAdminNameEntity の保存
        // nameIdが0でない場合は、既存のエンティティを履歴化する
        if (adminDto.getNameId() != 0) {
            riyoushaAdminNameRepository.findById(adminDto.getNameId()).ifPresent(oldEntity -> {
                setTableDataHistoryUtil.practiceDelete(userDto, oldEntity);
                riyoushaAdminNameRepository.save(oldEntity);
            });
        }
        // DTOから新しいエンティティを作成して保存
        RiyoushaAdminNameEntity newNameEntity = new RiyoushaAdminNameEntity();
        // DTOからEntityへのフィールドマッピングを実装
        if (adminDto.getIsNotOrg()) {
            BeanUtils.copyProperties(adminDto.getInputPersonNameDto(), newNameEntity);
        } else {
            BeanUtils.copyProperties(adminDto.getInputOrgNameDto(), newNameEntity);
        }
        newNameEntity.setRiyoushaAdminId(savedAdminEntity.getRiyoushaAdminId());
        newNameEntity.setRiyoushaAdminCode(savedAdminEntity.getRiyoushaAdminId());
        newNameEntity.setRiyoushaAdminName(savedAdminEntity.getRiyoushaAdminName());

        setTableDataHistoryUtil.practiceInsert(userDto, newNameEntity);
        return riyoushaAdminNameRepository.save(newNameEntity).getRiyoushaAdminNameId();

    }

}
