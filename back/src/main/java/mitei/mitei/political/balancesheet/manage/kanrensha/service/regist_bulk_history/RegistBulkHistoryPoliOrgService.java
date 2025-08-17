package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki.PartnerPoliOrgJudgeProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体履歴編集Service
 */
@Service
public class RegistBulkHistoryPoliOrgService {

    /** ワークテーブルマスタ企業／団体履歴Repository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTblPartnerPoliOrgHistoryRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerPoliOrgJudgeProcessor partnerPoliOrgJudgeProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblPartnerPoliOrgHistoryEntity practice(final UpdateWkTblHistoryPoliOrgCapsuleDto capsuleDto) {

        WkTblPartnerPoliOrgHistoryEntity entityInput = capsuleDto.getWkTblPartnerPoliOrgHistoryEntity();

        Optional<WkTblPartnerPoliOrgHistoryEntity> optional = wkTblPartnerPoliOrgHistoryRepository
                .findById(entityInput.getWkPartnerPoliOrgHistoryId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblPartnerPoliOrgHistoryEntity();
        }

        entityInput = partnerPoliOrgJudgeProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerPoliOrgHistoryEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerPoliOrgHistoryRepository.save(entitySrc);

        entityInput.setWkPartnerPoliOrgHistoryId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerPoliOrgHistoryRepository.save(entityInput);
    }

}
