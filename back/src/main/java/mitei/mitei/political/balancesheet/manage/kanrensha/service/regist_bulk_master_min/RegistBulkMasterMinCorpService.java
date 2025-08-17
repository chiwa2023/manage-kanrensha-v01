package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体最小編集Service
 */
@Service
public class RegistBulkMasterMinCorpService {

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblPartnerCorpAddMinRepository wkTblPartnerCorpAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerCorpAddMiniCsvProcessor partnerCorpAddMiniCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblPartnerCorpAddMinEntity practice(final UpdateWkTblMinCorpCapsuleDto capsuleDto) {

        WkTblPartnerCorpAddMinEntity entityInput = capsuleDto.getWkTblPartnerCorpAddMinEntity();

        Optional<WkTblPartnerCorpAddMinEntity> optional = wkTblPartnerCorpAddMinRepository
                .findById(entityInput.getWkTblPartnerCorpAddMinId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblPartnerCorpAddMinEntity();
        }

        entityInput = partnerCorpAddMiniCsvProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerCorpAddMinEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerCorpAddMinRepository.save(entitySrc);

        entityInput.setWkTblPartnerCorpAddMinId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerCorpAddMinRepository.save(entityInput);
    }

}
