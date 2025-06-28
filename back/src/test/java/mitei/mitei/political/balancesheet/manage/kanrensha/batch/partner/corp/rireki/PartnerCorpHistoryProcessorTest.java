package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;

/**
 * PartnerCorpHistoryProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpHistoryProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerCorpHistoryProcessor partnerCorpHistoryProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerCorpHistoryDto item = new PartnerCorpHistoryDto();
        item.setPartnerName("ちゃらんぽらん政治団体");
        item.setAllAddress("山形県架空市湖畔町");
        item.setCorpDelegate("代表者　太郎");
        item.setCorpKanrenshaCode("1234-567e");

        WkTblPartnerCorpHistoryEntity entity = partnerCorpHistoryProcessor.process(item);

        assertEquals(item.getPartnerName(), entity.getPartnerName());
        assertEquals(item.getAllAddress(), entity.getAllAddress());
        assertEquals(item.getCorpDelegate(), entity.getCorpDelegate());
        assertEquals(item.getCorpKanrenshaCode(), entity.getCorpKanrenshaCode());

        assertEquals(false, entity.getIsAffected());
        assertEquals(false, entity.getIsFinish());
        assertEquals("", entity.getJudgeReason());
    }

}
