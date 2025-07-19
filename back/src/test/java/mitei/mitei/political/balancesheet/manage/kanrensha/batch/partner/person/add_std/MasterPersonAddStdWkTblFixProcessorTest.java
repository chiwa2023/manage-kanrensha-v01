package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonJudgeEntity;

/**
 * MasterPersonAddStdWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterPersonAddStdWkTblFixProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdWkTblFixProcessor masterPersonAddStdWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_wk_tbl_master_person.sql")
    void test() throws Exception {

        WkTblMasterPersonJudgeEntity judgeEntity = new WkTblMasterPersonJudgeEntity();
        judgeEntity.setWkTblMasterPersonId(297);

        WkTblMasterPersonEntity entity = masterPersonAddStdWkTblFixProcessor.process(judgeEntity);

        assertEquals(true, entity.getIsFinish());
        assertEquals("正常終了", entity.getJudgeReason());

    }

}
