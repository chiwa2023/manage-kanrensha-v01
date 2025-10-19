package mitei.mitei.political.balancesheet.manage.kanrensha.logic.works_apploval;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体住所承認作業更新Logic
 */
@Component
public class UpdateApprovalKanrenshaPoliOrgAddressLogic {

    /** 関連者個人住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param entity  関連者住所承認用Entity
     * @param userDto ユーザ最小限Dto
     * @return 更新行
     */
    public Integer practice(final MasterKanrenshaAddressBaseEntity entity, final UserPersonLeastDto userDto) {

        MasterPoliticalOrganizationAddressEntity entityLoad = masterPoliticalOrganizationAddressRepository
                .findById(entity.getKanrenshaAddressId()).get();

        if (Objects.equals(entity.getPostal1(), entityLoad.getPostal1())
                && Objects.equals(entity.getPostal2(), entityLoad.getPostal2())
                && Objects.equals(entity.getAddressPostal(), entityLoad.getAddressPostal())
                && Objects.equals(entity.getAddressBlock(), entityLoad.getAddressBlock())
                && Objects.equals(entity.getAddressBuilding(), entityLoad.getAddressBuilding())
                && Objects.equals(entity.getLgCode(), entityLoad.getLgCode())
                && Objects.equals(entity.getMachiazaId(), entityLoad.getMachiazaId())
                && Objects.equals(entity.getBlkId(), entityLoad.getBlkId())
                && Objects.equals(entity.getRsdtId(), entityLoad.getRsdtId())
                && Objects.equals(entity.getRsdt2Id(), entityLoad.getRsdt2Id())
                && Objects.equals(entity.getIsPostalEdit(), entityLoad.getIsPostalEdit())
                && Objects.equals(entity.getIsBlockEdit(), entityLoad.getIsBlockEdit())
                && Objects.equals(entity.getIsBuildingEdit(), entityLoad.getIsBuildingEdit())
                && Objects.equals(entity.getIsPostalAccept(), entityLoad.getIsPostalAccept())
                && Objects.equals(entity.getIsBlockAccept(), entityLoad.getIsBlockAccept())
                && Objects.equals(entity.getIsBuildingAccept(), entityLoad.getIsBuildingAccept())) {
            // 呼び出したentityに対して住所が変更がない場合はこのロジックでの処理を中断
            return 0;
        } else {
            setTableDataHistoryUtil.practiceDelete(userDto, entityLoad);
            masterPoliticalOrganizationAddressRepository.save(entityLoad);

        }

        // TODO マスタ本体も修正

        if (0 == masterPoliticalOrganizationAddressRepository.save(this.cloneNewEntity(entity, entityLoad, userDto))
                .getMasterPoliticalOrganizationAddressId()) {
            return 0;
        } else {

            return 1;
        }
    }

    private MasterPoliticalOrganizationAddressEntity cloneNewEntity(final MasterKanrenshaAddressBaseEntity entity,
            final MasterPoliticalOrganizationAddressEntity entityLoad, final UserPersonLeastDto userDto) {

        MasterPoliticalOrganizationAddressEntity entitySaved = new MasterPoliticalOrganizationAddressEntity();
        BeanUtils.copyProperties(entity, entitySaved);

        entitySaved.setPoliOrgKanrenshaCode(entityLoad.getPoliOrgKanrenshaCode());

        setTableDataHistoryUtil.practiceInsert(userDto, entitySaved);
        entitySaved.setMasterPoliticalOrganizationAddressId(0); // auto increment
        return entitySaved;
    }

}
