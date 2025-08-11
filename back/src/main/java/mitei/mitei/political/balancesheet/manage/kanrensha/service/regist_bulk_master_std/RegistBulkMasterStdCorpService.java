package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_std;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std.PartnerCorpAddStdCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体標準編集Service
 */
@Service
public class RegistBulkMasterStdCorpService {

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterCorpRepository wkTblMasterCorpRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerCorpAddStdCsvProcessor partnerCorpAddStdCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public Integer practice(final UpdateWkTblStdCorpCapsuleDto capsuleDto) {

        WkTblMasterCorpEntity entityInput = capsuleDto.getWkTblMasterCorpEntity();

        Optional<WkTblMasterCorpEntity> optional = wkTblMasterCorpRepository
                .findById(entityInput.getWkTblMasterCorpId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return 0;
        }

        entityInput = partnerCorpAddStdCsvProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblMasterCorpEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblMasterCorpRepository.save(entitySrc);

        entityInput.setWkTblMasterCorpId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblMasterCorpRepository.save(entityInput).getWkTblMasterCorpId();
    }

}
