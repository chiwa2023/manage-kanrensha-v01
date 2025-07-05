package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpPlusHojinNoEntity;

/**
 * PlusCorpHoujinNoOutputProcessor単体テスト
 */
class PlusCorpHoujinNoOutputProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblPartnerCorpPlusHojinNoEntity entity = new WkTblPartnerCorpPlusHojinNoEntity();
        entity.setPartnerName("団体名");
        entity.setAllAddress("全住所");
        entity.setCorpDelegate("代表者名");
        entity.setHoujinNo("123456+987654");
        entity.setIsForeignCorp(true);

        PlusCorpHoujinNoOutputProcessor processor = new PlusCorpHoujinNoOutputProcessor();

        ParterCorpPlusHoujinNoDto dto = processor.process(entity);

        assertEquals(entity.getPartnerName(), dto.getPartnerName());
        assertEquals(entity.getAllAddress(), dto.getAllAddress());
        assertEquals(entity.getCorpDelegate(), dto.getCorpDelegate());
        assertEquals(entity.getHoujinNo(), dto.getHoujinNo());
        assertEquals("該当", dto.getIsForeignCorpText());

    }

}
