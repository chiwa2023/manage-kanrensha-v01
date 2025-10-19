package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgRepository;

/**
 * SuspendDuplicateWkTblCombineOrgTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SuspendDuplicateWkTblCombineOrgTaskletTest {
    // CHECKTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SuspendDuplicateWkTblCombineOrgTasklet suspendDuplicateWkTblCombineOrgTasklet;

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblPartnerCombineOrgRepository wkTblPartnerCombineOrgRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("duplicate_sample_wk_tbl_partner_combine_org.sql")
    void test() throws Exception {
        // CHECKSTYLE:OFF

        final String reason = "アップロードファイル内で重複しているデータです";

        suspendDuplicateWkTblCombineOrgTasklet.beforeStep(this.getStepExecution());
        suspendDuplicateWkTblCombineOrgTasklet.execute(null, null);

        WkTblPartnerCombineOrgEntity entity501 = wkTblPartnerCombineOrgRepository.findById(501).get();
        assertEquals(false, entity501.getIsLatest());
        assertEquals(true, entity501.getIsFinish());
        assertEquals(reason, entity501.getJudgeReason());
        WkTblPartnerCombineOrgEntity entity502 = wkTblPartnerCombineOrgRepository.findById(502).get();
        assertEquals(false, entity502.getIsLatest());
        assertEquals(true, entity502.getIsFinish());
        assertEquals(reason, entity502.getJudgeReason());
        WkTblPartnerCombineOrgEntity entity601 = wkTblPartnerCombineOrgRepository.findById(601).get();
        assertEquals(false, entity601.getIsLatest());
        assertEquals(true, entity601.getIsFinish());
        assertEquals(reason, entity601.getJudgeReason());
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
