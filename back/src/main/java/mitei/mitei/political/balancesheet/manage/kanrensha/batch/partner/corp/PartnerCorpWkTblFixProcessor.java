package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpHistoryRepository;

/**
 * 関連者企業・団体判定から編集用のワークテーブルを設定する
 */
@Component
public class PartnerCorpWkTblFixProcessor
        implements ItemProcessor<WkTblPartnerCorpJudgeEntity, WkTblPartnerCorpHistoryEntity> {

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblPartnerCorpHistoryRepository wkTblPartnerCorpHistoryRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerCorpHistoryEntity process(final WkTblPartnerCorpJudgeEntity item) throws Exception {

        WkTblPartnerCorpHistoryEntity entity = new WkTblPartnerCorpHistoryEntity();

        Optional<WkTblPartnerCorpHistoryEntity> optional = wkTblPartnerCorpHistoryRepository
                .findById(item.getWkPartnerCorpHistoryId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsAffected(item.getIsAffected());
            entity.setJudgeReason(item.getJudgeReason());
        }

        return entity;
    }

}
