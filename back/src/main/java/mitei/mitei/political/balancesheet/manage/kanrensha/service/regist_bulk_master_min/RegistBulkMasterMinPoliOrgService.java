package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgAddMiniCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体最小編集Service
 */
@Service
public class RegistBulkMasterMinPoliOrgService {

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinRepository wkTblPartnerPoliOrgAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerPoliOrgAddMiniCsvProcessor partnerPoliOrgAddMiniCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblPartnerPoliOrgAddMinEntity practice(final UpdateWkTblMinPoliOrgCapsuleDto capsuleDto) {

        WkTblPartnerPoliOrgAddMinEntity entityInput = capsuleDto.getWkTblPartnerPoliOrgAddMinEntity();

        Optional<WkTblPartnerPoliOrgAddMinEntity> optional = wkTblPartnerPoliOrgAddMinRepository
                .findById(entityInput.getWkTblPartnerPoliOrgAddMinId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblPartnerPoliOrgAddMinEntity();
        }

        entityInput = partnerPoliOrgAddMiniCsvProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerPoliOrgAddMinEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerPoliOrgAddMinRepository.save(entitySrc);

        entityInput.setWkTblPartnerPoliOrgAddMinId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerPoliOrgAddMinRepository.save(entityInput);
    }

}
