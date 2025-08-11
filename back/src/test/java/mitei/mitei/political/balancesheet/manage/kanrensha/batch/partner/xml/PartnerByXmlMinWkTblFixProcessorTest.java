package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlJudgeEntity;

/**
 * PartnerByXmlMinWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerByXmlMinWkTblFixProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerByXmlMinWkTblFixProcessor partnerByXmlMinWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_master_all_by_xml.sql", "sample_wk_tbl_master_all_by_xml_judge.sql" })
    void test() throws Exception {
        WkTblMasterAllByXmlJudgeEntity item = new WkTblMasterAllByXmlJudgeEntity();
        item.setWkTblMasterAllByXmlId(344);

        WkTblMasterAllByXmlEntity entity = partnerByXmlMinWkTblFixProcessor.process(item);

        assertEquals(item.getWkTblMasterAllByXmlId(), entity.getWkTblMasterAllByXmlId());
        assertEquals(true, entity.getIsFinish());
    }

}
