package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinResultEntity;

/**
 * 関連者政治団体最小登録BatchConfig
 */
@Configuration
public class RetryMinKanrenshaPoliOrgMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "retryMinKanrenshaPoliOrgMaster";

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

    /** 関連者政治団体マスタ登録ItemReader */
    @Autowired
    private PartnerPoliOrgAddMiniRecordItemReader partnerPoliOrgAddMiniRecordItemReader;

    /** 関連者政治団体マスタ登録ItemWriter */
    @Autowired
    private PartnerPoliOrgAddMiniRecordItemWriter partnerPoliOrgAddMiniRecordItemWriter;

    /** 関連者政治団体処理結果判定ItemReader */
    @Autowired
    private PartnerPoliOrgAddMiniWkTblFixItemReader partnerPoliOrgAddMiniWkTblFixItemReader;

    /** 関連者政治団体処理結果判定ItemProcessor */
    @Autowired
    private PartnerPoliOrgAddMiniWkTblFixProcessor partnerPoliOrgAddMiniWkTblFixProcessor;

    /** 関連者政治団体処理結果判定ItemWriter */
    @Autowired
    private PartnerPoliOrgAddMiniWkTblFixItemWriter partnerPoliOrgAddMiniWkTblFixItemWriter;

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
                .<WkTblPartnerPoliOrgAddMinEntity, WkTblPartnerPoliOrgAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerPoliOrgAddMiniRecordItemReader).writer(partnerPoliOrgAddMiniRecordItemWriter).build();
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
                .<WkTblPartnerPoliOrgAddMinResultEntity, WkTblPartnerPoliOrgAddMinEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(partnerPoliOrgAddMiniWkTblFixItemReader).processor(partnerPoliOrgAddMiniWkTblFixProcessor)
                .writer(partnerPoliOrgAddMiniWkTblFixItemWriter).build();
    }

}
