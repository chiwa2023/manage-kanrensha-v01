package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpPlusHojinNoEntity;

/**
 * PlusCorpHoujinNoInputProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PlusCorpHoujinNoInputProcessorTest {

    /** テスト対象 */
    @Autowired
    private PlusCorpHoujinNoInputProcessor plusCorpHoujinNoInputProcessor;

    @Test
    void test() throws Exception {
        
        // 1件外国籍
        PartnerCorpAddMiniDto item1 = new PartnerCorpAddMiniDto();
        item1.setPartnerName("ウェルズ・ファーゴ銀行");
        item1.setAllAddress("東京都千代田区丸の内1丁目");
        item1.setCorpDelegate("頭取　太郎");

        WkTblPartnerCorpPlusHojinNoEntity entity1 = plusCorpHoujinNoInputProcessor.process(item1);

        assertEquals(item1.getPartnerName(), entity1.getPartnerName());
        assertEquals(item1.getAllAddress(), entity1.getAllAddress());
        assertEquals("1700150000166", entity1.getHoujinNo());
        assertEquals(true, entity1.getIsForeignCorp());

        // 該当複数
        PartnerCorpAddMiniDto item2 = new PartnerCorpAddMiniDto();
        item2.setPartnerName("日野神社");
        item2.setAllAddress("兵庫県宍粟市");
        item2.setCorpDelegate("神主　太郎");
        
        WkTblPartnerCorpPlusHojinNoEntity entity2 = plusCorpHoujinNoInputProcessor.process(item2);

        assertEquals(item2.getPartnerName(), entity2.getPartnerName());
        assertEquals(item2.getAllAddress(), entity2.getAllAddress());
        assertEquals("4140005016098+9140005007134", entity2.getHoujinNo());
        assertEquals(false, entity2.getIsForeignCorp());

    }

}
