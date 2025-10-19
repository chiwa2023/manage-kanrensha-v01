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
public class InsertKanrenshaPoliOrgHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "insertKanrenshaPoliOrgHistory";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(削除) */
    public static final String STEP_ERASE = FUNCTION_NAME + "EraseWkTbl" + STEP;

    /** Step名(ファイルを履歴に複写) */
    public static final String STEP_HISTORY = FUNCTION_NAME + "History" + STEP;

    /** Step名(ファイル内重複データ中断) */
    public static final String STEP_SUSPEND = FUNCTION_NAME + "SuspendDuplicate" + STEP;

    /** Step名(履歴を判定して判定に複写) */
    public static final String STEP_JUDGE = FUNCTION_NAME + "Judge" + STEP;

    /** Step名(判定からワークテーブル修正) */
    public static final String STEP_FIX = FUNCTION_NAME + "Fix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** ワークテーブル消去Tasklet */
    @Autowired
    private EraseWkTblParnerPoliOrgHistoryTasklet eraseWkTblParnerPoliOrgHistoryTasklet;

    /** ワークテーブル履歴ItemReader */
    @Autowired
    private PartnerPoliOrgHistoryItemReader partnerPoliOrgHistoryItemReader;

    /** ワークテーブル履歴ItemWriter */
    @Autowired
    private PartnerPoliOrgHistoryItemWriter partnerPoliOrgHistoryItemWriter;

    /** ワークテーブル履歴Processor */
    @Autowired
    private PartnerPoliOrgHistoryProcessor partnerPoliOrgHistoryProcessor;

    /** ファイル内重複データ中断Tasklet */
    @Autowired
    private SuspendDuplicateWkTblParnerPoliOrgHistoryTasklet suspendDuplicateWkTblParnerPoliOrgHistoryTasklet;

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
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_ERASE) final Step stepErase,
            @Qualifier(STEP_HISTORY) final Step stepHistory, @Qualifier(STEP_SUSPEND) final Step stepSuspend,
            @Qualifier(STEP_JUDGE) final Step stepJudge, @Qualifier(STEP_FIX) final Step stepFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepErase)
                .next(stepHistory).next(stepSuspend).next(stepJudge).next(stepFix).end().build();
    }

    /**
     * StepEraseを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_ERASE)
    protected Step getStepErase(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_ERASE, jobRepository)
                .tasklet(eraseWkTblParnerPoliOrgHistoryTasklet, transactionManager).build();
    }

    /**
     * StepHistoryを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_HISTORY)
    protected Step getStepHistory(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_HISTORY, jobRepository)
                .<PartnerPoliOrgHistoryDto, WkTblPartnerPoliOrgHistoryEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerPoliOrgHistoryItemReader).processor(partnerPoliOrgHistoryProcessor)
                .writer(partnerPoliOrgHistoryItemWriter).build();
    }

    /**
     * StepSuspendを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_SUSPEND)
    protected Step getStepSuspend(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_SUSPEND, jobRepository)
                .tasklet(suspendDuplicateWkTblParnerPoliOrgHistoryTasklet, transactionManager).build();
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
