package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.BackApplication;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * MultiParcelAddressInsertBatchConfiguration単体テスト(北海道・自開発環境で約13分)
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MultiParcelAddressInsertBatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(MultiParcelAddressInsertBatchConfiguration.JOB_NAME)
    @Autowired
    private Job multiParcelAddressInsert;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(MultiParcelAddressInsertBatchConfiguration.JOB_NAME, multiParcelAddressInsert.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    @Transactional // 注意：トランザクションを外すときは慎重に!
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(multiParcelAddressInsert);

        // 実データ(test_sourceでない)
        Path path = Paths.get(GetCurrentResourcePath.getBackSrcPath("/main/resources/adderss/parcel/13/"));

        // テスト用パス TODO テスト用データを用意する
        // Path path =
        // Paths.get(GetCurrentResourcePath.getBackSrcPath("/main/resources/adderss/dammy未設定"));

        JobParameters jobParameters = new JobParametersBuilder(
                multiParcelAddressInsert.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readDirectory", path.toString())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
