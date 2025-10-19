package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonJudgeEntity;

/**
 * 関連者個人履歴挿入BatchConfig
 */
@Configuration
public class RetryKanrenshaPersonHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "retryKanrenshaPersonHistory";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(履歴を判定して判定に複写) */
    public static final String STEP_JUDGE = FUNCTION_NAME + "Judge" + STEP;

    /** Step名(判定からワークテーブル修正) */
    public static final String STEP_FIX = FUNCTION_NAME + "Fix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** ワークテーブル判定ItemReader */
    @Autowired
    private PartnerPersonJudgeItemReader partnerPersonJudgeItemReader;

    /** ワークテーブル判定ItemWriter */
    @Autowired
    private PartnerPersonJudgeItemWriter partnerPersonJudgeItemWriter;

    /** ワークテーブル判定ItemProcessor */
    @Autowired
    private PartnerPersonJudgeProcessor partnerPersonJudgeProcessor;

    /** ワークテーブル修正ItemReader */
    @Autowired
    private PartnerPersonWkTblFixItemReader partnerPersonWkTblFixItemReader;

    /** ワークテーブル修正ItemWriter */
    @Autowired
    private PartnerPersonWkTblFixItemWriter partnerPersonWkTblFixItemWriter;

    /** ワークテーブル修正Processor */
    @Autowired
    private PartnerPersonWkTblFixProcessor partnerPersonWkTblFixProcessor;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_JUDGE) final Step stepJudge,
            @Qualifier(STEP_FIX) final Step stepFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepJudge).next(stepFix)
                .end().build();
    }

    /**
     * StepJudgeを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_JUDGE)
    protected Step getStepJudge(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_JUDGE, jobRepository)
                .<WkTblPartnerPersonHistoryEntity, WkTblPartnerPersonJudgeEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerPersonJudgeItemReader).processor(partnerPersonJudgeProcessor)
                .writer(partnerPersonJudgeItemWriter).build();
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
                .<WkTblPartnerPersonJudgeEntity, WkTblPartnerPersonHistoryEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerPersonWkTblFixItemReader).processor(partnerPersonWkTblFixProcessor)
                .writer(partnerPersonWkTblFixItemWriter).build();
    }

}
