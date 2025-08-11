package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlJudgeEntity;

/**
 * 関連者個人最小登録BatchConfig
 */
@Configuration
public class AddXmlMinMasterBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "addXmlMinMaster";

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

    /** XMLファイルマスタマスタ登録ItemReader */
    @Autowired
    private PartnerByXmlMinRecordItemReader partnerByXmlMinRecordItemReader;

    /** XMLファイルマスタマスタ登録ItemWriter */
    @Autowired
    private PartnerByXmlMinRecordItemWriter partnerByXmlMinRecordItemWriter;

    /** XMLファイルマスタ処理結果判定ItemReader */
    @Autowired
    private PartnerByXmlMinWkTblFixItemReader partnerByXmlMinWkTblFixItemReader;

    /** XMLファイルマスタ処理結果判定ItemProcessor */
    @Autowired
    private PartnerByXmlMinWkTblFixProcessor partnerByXmlMinWkTblFixProcessor;

    /** XMLファイルマスタ処理結果判定ItemWriter */
    @Autowired
    private PartnerByXmlMinWkTblFixItemWriter partnerByXmlMinWkTblFixItemWriter;

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
                .<WkTblMasterAllByXmlEntity, WkTblMasterAllByXmlEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerByXmlMinRecordItemReader).writer(partnerByXmlMinRecordItemWriter).build();
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
                .<WkTblMasterAllByXmlJudgeEntity, WkTblMasterAllByXmlEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerByXmlMinWkTblFixItemReader).processor(partnerByXmlMinWkTblFixProcessor)
                .writer(partnerByXmlMinWkTblFixItemWriter).build();
    }

}
