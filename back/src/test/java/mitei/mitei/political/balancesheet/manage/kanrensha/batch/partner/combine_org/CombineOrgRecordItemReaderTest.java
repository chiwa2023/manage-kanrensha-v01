package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * CombineOrgRecordItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CombineOrgRecordItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CombineOrgRecordItemReader combineOrgRecordItemReader;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_wk_tbl_partner_combine_org.sql")
    void test() throws Exception {

        combineOrgRecordItemReader.beforeStep(this.getStepExecution());

        // 215:対象ユーザでない、212:最新でない、213:編集対象でない、214:処理終了のため読まれない
        assertEquals(211, combineOrgRecordItemReader.read().getWkTblPartnerCombineOrgId());
        assertEquals(216, combineOrgRecordItemReader.read().getWkTblPartnerCombineOrgId());
        assertEquals(217, combineOrgRecordItemReader.read().getWkTblPartnerCombineOrgId());
        assertNull(combineOrgRecordItemReader.read());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", Long.parseLong(userId.toString()))
                .addLong("userCode", Long.parseLong(userCode.toString())).addString("userName", userName)
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
