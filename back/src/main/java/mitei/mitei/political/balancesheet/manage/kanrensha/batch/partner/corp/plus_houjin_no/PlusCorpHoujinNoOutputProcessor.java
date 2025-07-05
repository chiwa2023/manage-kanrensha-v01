package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpPlusHojinNoEntity;

/**
 * 企業・団体法人番号追加ワークテーブルCsvDto変換Processor
 */
@Component
public class PlusCorpHoujinNoOutputProcessor
        implements ItemProcessor<WkTblPartnerCorpPlusHojinNoEntity, ParterCorpPlusHoujinNoDto> {

    /**
     * 変換処理を実行する
     */
    @Override
    public ParterCorpPlusHoujinNoDto process(final WkTblPartnerCorpPlusHojinNoEntity item) throws Exception {

        ParterCorpPlusHoujinNoDto dto = new ParterCorpPlusHoujinNoDto();
        BeanUtils.copyProperties(item, dto);

        if (item.getIsForeignCorp()) {
            dto.setIsForeignCorpText("該当");
        } else {
            dto.setIsForeignCorpText("非該当");
        }

        return dto;
    }

}
