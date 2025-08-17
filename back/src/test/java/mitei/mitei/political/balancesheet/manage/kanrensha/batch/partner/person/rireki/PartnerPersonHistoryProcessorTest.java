package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;

/**
 * PartnerPersonHistoryProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonHistoryProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerPersonHistoryProcessor partnerPersonHistoryProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerPersonHistoryDto item = new PartnerPersonHistoryDto();
        item.setPartnerName("迂回献金　太郎");
        item.setAllAddress("山形県架空市湖畔町");
        item.setPersonShokugyou("経営者");
        item.setPersonKanrenshaCode("1234-567e");

        WkTblPartnerPersonHistoryEntity entity = partnerPersonHistoryProcessor.process(item);

        assertEquals(item.getPartnerName(), entity.getPartnerName());
        assertEquals(item.getAllAddress(), entity.getAllAddress());
        assertEquals(item.getPersonShokugyou(), entity.getPersonShokugyou());
        assertEquals(item.getPersonKanrenshaCode(), entity.getPersonKanrenshaCode());

        assertEquals(false, entity.getIsAffected());
        assertEquals(false, entity.getIsFinish());
        assertEquals(" ", entity.getJudgeReason());

    }

}
