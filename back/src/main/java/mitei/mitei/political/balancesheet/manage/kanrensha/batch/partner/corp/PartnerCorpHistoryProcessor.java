package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;

/**
 * 関連者企業・団体CsvからワークテーブルProcessor
 */
@Component
public class PartnerCorpHistoryProcessor
        implements ItemProcessor<PartnerCorpHistoryDto, WkTblPartnerCorpHistoryEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerCorpHistoryEntity process(final PartnerCorpHistoryDto item) throws Exception {

        WkTblPartnerCorpHistoryEntity entity = new WkTblPartnerCorpHistoryEntity();

        BeanUtils.copyProperties(item, entity);

        entity.setIsAffected(false);
        entity.setIsFinish(false);
        entity.setJudgeReason("");
        entity.setWkPartnerCorpHistoryId(0); // auto_increment明示
        // コードはテーブルロックの都合上最後の段階で行う

        return entity;
    }

}
