package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;

/**
 * 関連者政治団体CsvからワークテーブルProcessor
 */
@Component
public class PartnerPoliOrgHistoryProcessor
        implements ItemProcessor<PartnerPoliOrgHistoryDto, WkTblPartnerPoliOrgHistoryEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPoliOrgHistoryEntity process(final PartnerPoliOrgHistoryDto item) throws Exception {

        WkTblPartnerPoliOrgHistoryEntity entity = new WkTblPartnerPoliOrgHistoryEntity();

        BeanUtils.copyProperties(item, entity);

        entity.setIsAffected(false);
        entity.setIsFinish(false);
        entity.setJudgeReason("");
        entity.setWkPartnerPoliOrgHistoryId(0); // auto_increment明示
        // コードはテーブルロックの都合上最後の段階で行う

        return entity;
    }

}
