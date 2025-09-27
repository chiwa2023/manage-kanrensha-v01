package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki.PartnerCorpJudgeProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体履歴編集Service
 */
@Service
public class RegistBulkHistoryCorpService {

    /** ワークテーブルマスタ企業／団体履歴Repository */
    @Autowired
    private WkTblPartnerCorpHistoryRepository wkTblPartnerCorpHistoryRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerCorpJudgeProcessor partnerCorpJudgeProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblPartnerCorpHistoryEntity practice(final UpdateWkTblHistoryCorpCapsuleDto capsuleDto) {

        WkTblPartnerCorpHistoryEntity entityInput = capsuleDto.getWkTblPartnerCorpHistoryEntity();

        Optional<WkTblPartnerCorpHistoryEntity> optional = wkTblPartnerCorpHistoryRepository
                .findById(entityInput.getWkPartnerCorpHistoryId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblPartnerCorpHistoryEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = partnerCorpJudgeProcessor.check(entityInput);
        }

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerCorpHistoryEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerCorpHistoryRepository.save(entitySrc);

        entityInput.setWkPartnerCorpHistoryId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerCorpHistoryRepository.save(entityInput);
    }

}
