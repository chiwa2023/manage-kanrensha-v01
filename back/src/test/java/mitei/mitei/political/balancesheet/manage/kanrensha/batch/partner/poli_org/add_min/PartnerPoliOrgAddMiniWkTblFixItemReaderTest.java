package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

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
 * PartnerPoliOrgAddMiniWkTblFixItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgAddMiniWkTblFixItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgAddMiniWkTblFixItemReader partnerPoliOrgAddMiniWkTblFixItemReader;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_wk_tbl_partner_poli_org_add_min_result.sql")
    void test() throws Exception {
        // CHECKSTYLE:OFF

        partnerPoliOrgAddMiniWkTblFixItemReader.beforeStep(this.getStepExecution());

        assertEquals(385, partnerPoliOrgAddMiniWkTblFixItemReader.read().getWkTblPartnerPoliOrgAddMinResultId());
        assertEquals(386, partnerPoliOrgAddMiniWkTblFixItemReader.read().getWkTblPartnerPoliOrgAddMinResultId());
        assertEquals(387, partnerPoliOrgAddMiniWkTblFixItemReader.read().getWkTblPartnerPoliOrgAddMinResultId());
        assertNull(partnerPoliOrgAddMiniWkTblFixItemReader.read());
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
