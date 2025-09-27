package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki.PartnerPersonJudgeProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体履歴編集Service
 */
@Service
public class RegistBulkHistoryPersonService {

    /** ワークテーブルマスタ企業／団体履歴Repository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTblPartnerPersonHistoryRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerPersonJudgeProcessor partnerPersonJudgeProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblPartnerPersonHistoryEntity practice(final UpdateWkTblHistoryPersonCapsuleDto capsuleDto) {

        WkTblPartnerPersonHistoryEntity entityInput = capsuleDto.getWkTblPartnerPersonHistoryEntity();

        Optional<WkTblPartnerPersonHistoryEntity> optional = wkTblPartnerPersonHistoryRepository
                .findById(entityInput.getWkPartnerPersonHistoryId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblPartnerPersonHistoryEntity();
        }

        // ユーザさんが変更しないと決断したらデータ整合チェックはしないで意図をそのまま通す
        final String notUseText = "使用しないに変更;";
        if (!notUseText.equals(entityInput.getJudgeReason())) {
            entityInput = partnerPersonJudgeProcessor.check(entityInput);
        }

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerPersonHistoryEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerPersonHistoryRepository.save(entitySrc);

        entityInput.setWkPartnerPersonHistoryId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerPersonHistoryRepository.save(entityInput);
    }

}
