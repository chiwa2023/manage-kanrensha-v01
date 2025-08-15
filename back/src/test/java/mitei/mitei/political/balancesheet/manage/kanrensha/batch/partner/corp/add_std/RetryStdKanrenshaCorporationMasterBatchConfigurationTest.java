package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.BackApplication;

/**
 * RetryStdKanrenshaCorporationMasterBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RetryStdKanrenshaCorporationMasterBatchConfigurationTest {

    /** 起動をするJob */
    @Qualifier(RetryStdKanrenshaCorporationMasterBatchConfiguration.JOB_NAME)
    @Autowired
    private Job retryStdKanrenshaCorporationMaster;

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(RetryStdKanrenshaCorporationMasterBatchConfiguration.JOB_NAME,
                retryStdKanrenshaCorporationMaster.getName(), "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "sample_wk_tbl_master_corp_judge.sql", "sample_wk_tbl_master_corp.sql", "delete_master_corporation.sql",
            "delete_master_corporation_base.sql", "delete_master_corporation_address.sql",
            "delete_master_corporation_access.sql", "delete_master_corporation_property.sql", "delete_history_01.sql" })
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(retryStdKanrenshaCorporationMaster);

        JobParameters jobParameters = new JobParametersBuilder(
                retryStdKanrenshaCorporationMaster.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addLong("userId", Long.parseLong(userId.toString()))
                .addLong("userCode", Long.parseLong(userCode.toString())).addString("userName", userName)
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
