package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpJudgeEntity;

/**
 * 関連者企業・団体標準登録BatchConfig
 */
@Configuration
public class RetryStdKanrenshaCorporationMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "retryStdKanrenshaCorporationMaster";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(マスタ登録) */
    public static final String STEP_RECORD = FUNCTION_NAME + "Record" + STEP;

    /** Step名(処理結果を反映) */
    public static final String STEP_FIX = FUNCTION_NAME + "Fix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** 関連者企業・団体マスタ登録ItemReader */
    @Autowired
    private MasterCorporationAddStdRecordItemReader masterCorporationAddStdRecordItemReader;

    /** 関連者企業・団体マスタ登録ItemWriter */
    @Autowired
    private MasterCorporationAddStdRecordItemWriter masterCorporationAddStdRecordItemWriter;

    /** 関連者企業・団体処理結果判定ItemReader */
    @Autowired
    private MasterCorporationAddStdWkTblFixItemReader masterCorporationAddStdWkTblFixItemReader;

    /** 関連者企業・団体処理結果判定ItemProcessor */
    @Autowired
    private MasterCorporationAddStdWkTblFixProcessor masterCorporationAddStdWkTblFixProcessor;

    /** 関連者企業・団体処理結果判定ItemWriter */
    @Autowired
    private MasterCorporationAddStdWkTblFixItemWriter masterCorporationAddStdWkTblFixItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_RECORD) final Step stepRecord,
            @Qualifier(STEP_FIX) final Step stepFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepRecord)
                .next(stepFix).end().build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RECORD)
    protected Step getStepRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RECORD, jobRepository)
                .<WkTblMasterCorpEntity, WkTblMasterCorpEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterCorporationAddStdRecordItemReader).writer(masterCorporationAddStdRecordItemWriter)
                .build();
    }

    /**
     * StepFixを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_FIX)
    protected Step getStepFix(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_FIX, jobRepository)
                .<WkTblMasterCorpJudgeEntity, WkTblMasterCorpEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(masterCorporationAddStdWkTblFixItemReader).processor(masterCorporationAddStdWkTblFixProcessor)
                .writer(masterCorporationAddStdWkTblFixItemWriter).build();
    }

}
