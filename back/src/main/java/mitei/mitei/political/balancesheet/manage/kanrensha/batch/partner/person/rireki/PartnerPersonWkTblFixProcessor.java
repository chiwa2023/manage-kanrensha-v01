package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;

/**
 * 関連者個人判定から編集用のワークテーブルを設定する
 */
@Component
public class PartnerPersonWkTblFixProcessor
        implements ItemProcessor<WkTblPartnerPersonJudgeEntity, WkTblPartnerPersonHistoryEntity> {

    /** 関連者個人ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTblPartnerPersonHistoryRepository;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPersonHistoryEntity process(final WkTblPartnerPersonJudgeEntity item) throws Exception {

        WkTblPartnerPersonHistoryEntity entity = new WkTblPartnerPersonHistoryEntity();

        Optional<WkTblPartnerPersonHistoryEntity> optional = wkTblPartnerPersonHistoryRepository
                .findById(item.getWkPartnerPersonHistoryId());
        if (!optional.isEmpty()) {
            entity = optional.get();
            entity.setIsAffected(item.getIsAffected());
            entity.setJudgeReason(item.getJudgeReason());
        }

        return entity;
    }

}
