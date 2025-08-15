package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpJudgeEntity;

/**
 * 関連者企業・団体履歴挿入BatchConfig
 */
@Configuration
public class RetryKanrenshaCorpHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "retryKanrenshaCorpHistory";

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
    private PartnerCorpJudgeItemReader partnerCorpJudgeItemReader;

    /** ワークテーブル判定ItemWriter */
    @Autowired
    private PartnerCorpJudgeItemWriter partnerCorpJudgeItemWriter;

    /** ワークテーブル判定ItemProcessor */
    @Autowired
    private PartnerCorpJudgeProcessor partnerCorpJudgeProcessor;

    /** ワークテーブル修正ItemReader */
    @Autowired
    private PartnerCorpWkTblFixItemReader partnerCorpWkTblFixItemReader;

    /** ワークテーブル修正ItemWriter */
    @Autowired
    private PartnerCorpWkTblFixItemWriter partnerCorpWkTblFixItemWriter;

    /** ワークテーブル修正Processor */
    @Autowired
    private PartnerCorpWkTblFixProcessor partnerCorpWkTblFixProcessor;

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
                .<WkTblPartnerCorpHistoryEntity, WkTblPartnerCorpJudgeEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerCorpJudgeItemReader).processor(partnerCorpJudgeProcessor)
                .writer(partnerCorpJudgeItemWriter).build();
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
                .<WkTblPartnerCorpJudgeEntity, WkTblPartnerCorpHistoryEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerCorpWkTblFixItemReader).processor(partnerCorpWkTblFixProcessor)
                .writer(partnerCorpWkTblFixItemWriter).build();
    }

}
