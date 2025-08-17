package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_std;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std.PartnerPoliOrgAddStdCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体標準編集Service
 */
@Service
public class RegistBulkMasterStdPoliOrgService {

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerPoliOrgAddStdCsvProcessor partnerPoliOrgAddStdCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblMasterPoliOrgEntity practice(final UpdateWkTblStdPoliOrgCapsuleDto capsuleDto) {

        WkTblMasterPoliOrgEntity entityInput = capsuleDto.getWkTblMasterPoliOrgEntity();

        Optional<WkTblMasterPoliOrgEntity> optional = wkTblMasterPoliOrgRepository
                .findById(entityInput.getWkTblMasterPoliOrgId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblMasterPoliOrgEntity();
        }

        entityInput = partnerPoliOrgAddStdCsvProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblMasterPoliOrgEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblMasterPoliOrgRepository.save(entitySrc);

        entityInput.setWkTblMasterPoliOrgId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblMasterPoliOrgRepository.save(entityInput);
    }

}
