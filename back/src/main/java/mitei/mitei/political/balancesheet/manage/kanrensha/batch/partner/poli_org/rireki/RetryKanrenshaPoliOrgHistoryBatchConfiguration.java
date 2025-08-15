package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgJudgeEntity;

/**
 * 関連者政治団体履歴挿入BatchConfig
 */
@Configuration
public class RetryKanrenshaPoliOrgHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "retryKanrenshaPoliOrgHistory";

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
    private PartnerPoliOrgJudgeItemReader partnerPoliOrgJudgeItemReader;

    /** ワークテーブル判定ItemWriter */
    @Autowired
    private PartnerPoliOrgJudgeItemWriter partnerPoliOrgJudgeItemWriter;

    /** ワークテーブル判定ItemProcessor */
    @Autowired
    private PartnerPoliOrgJudgeProcessor partnerPoliOrgJudgeProcessor;

    /** ワークテーブル修正ItemReader */
    @Autowired
    private PartnerPoliOrgWkTblFixItemReader partnerPoliOrgWkTblFixItemReader;

    /** ワークテーブル修正ItemWriter */
    @Autowired
    private PartnerPoliOrgWkTblFixItemWriter partnerPoliOrgWkTblFixItemWriter;

    /** ワークテーブル修正Processor */
    @Autowired
    private PartnerPoliOrgWkTblFixProcessor partnerPoliOrgWkTblFixProcessor;

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
                .<WkTblPartnerPoliOrgHistoryEntity, WkTblPartnerPoliOrgJudgeEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerPoliOrgJudgeItemReader).processor(partnerPoliOrgJudgeProcessor)
                .writer(partnerPoliOrgJudgeItemWriter).build();
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
                .<WkTblPartnerPoliOrgJudgeEntity, WkTblPartnerPoliOrgHistoryEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerPoliOrgWkTblFixItemReader).processor(partnerPoliOrgWkTblFixProcessor)
                .writer(partnerPoliOrgWkTblFixItemWriter).build();
    }

}
