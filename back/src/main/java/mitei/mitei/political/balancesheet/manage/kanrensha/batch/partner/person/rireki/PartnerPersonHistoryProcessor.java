package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;

/**
 * 関連者個人CsvからワークテーブルProcessor
 */
@Component
public class PartnerPersonHistoryProcessor
        implements ItemProcessor<PartnerPersonHistoryDto, WkTblPartnerPersonHistoryEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPersonHistoryEntity process(final PartnerPersonHistoryDto item) throws Exception {

        WkTblPartnerPersonHistoryEntity entity = new WkTblPartnerPersonHistoryEntity();

        BeanUtils.copyProperties(item, entity);

        entity.setIsAffected(false);
        entity.setIsFinish(false);
        entity.setJudgeReason("");
        entity.setWkPartnerPersonHistoryId(0); // auto_increment明示
        // コードはテーブルロックの都合上最後の段階で行う

        return entity;
    }

}
