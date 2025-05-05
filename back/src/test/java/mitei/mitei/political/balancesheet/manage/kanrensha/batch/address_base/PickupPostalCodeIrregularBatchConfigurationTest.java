package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

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
 * PickupPostalCodeIrregularBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupPostalCodeIrregularBatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(PickupPostalCodeIrregularBatchConfiguration.JOB_NAME)
    @Autowired
    private Job pickupPostalCodeIrregular;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(PickupPostalCodeIrregularBatchConfiguration.JOB_NAME, pickupPostalCodeIrregular.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    @Sql("delete_address_postal_irregular.sql")
    //@Sql({ "address_postal.sql", "delete_address_postal_irregular.sql" })
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(pickupPostalCodeIrregular);

        JobParameters jobParameters = new JobParametersBuilder(
                pickupPostalCodeIrregular.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
