package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * 郵便番号不規則修正BachConfig
 */
@Configuration
public class RepairPostalCodeIrregularBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "repairPostalCodeIrregular";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** Step名(Other) */
    public static final String STEP_OTHER = FUNCTION_NAME + "Other" + STEP;

    /** Step名(Range) */
    public static final String STEP_RANGE = FUNCTION_NAME + "Range" + STEP;

    /** Step名(Single) */
    public static final String STEP_SINGLE = FUNCTION_NAME + "Single" + STEP;

    /** Step名(UpdateIrregular) */
    public static final String STEP_UPDATE_IRREGULAR = FUNCTION_NAME + "UpdateIrregular" + STEP;

    /** Step名(Clean) */
    public static final String STEP_CLEAN = FUNCTION_NAME + "Clean" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /* その他は住居に対応データが存在するのを確認して有効にする */

    /** 郵便番号不規則その他CsvItemReader */
    @Autowired
    private SelectPostalCodeOtherItemReader selectPostalCodeOtherItemReader;

    /** 郵便番号CsvEntityProcessor */
    @Autowired
    private AddressPostalIrregularWorksProcessor addressPostalIrregularWorksProcessor;

    /** 郵便番号不規則その他ItemWriter */
    @Autowired
    private SelectPostalCodeOtherItemWriter selectPostalCodeOtherItemWriter;

    /* 単純な範囲データは郵便番号正規データを複製する */

    /** 郵便番号CsvItemReader */
    @Autowired
    private ChoicePostalCodeIrregularItemReader choicePostalCodeIrregularItemReader;

    /** 郵便番号ItemWriter */
    @Autowired
    private ChoicePostalCodeIrregularItemWriter choicePostalCodeIrregularItemWriter;

    /* 一地域のみデータは呼び出し住所を更新する */
    /** 郵便番号CsvItemReader */
    @Autowired
    private SelectPostalCodeSingleAddressItemReader selectPostalCodeSingleAddressItemReader;

    /** 郵便番号ItemWriter */
    @Autowired
    private SelectPostalCodeSingleAddressItemWriter selectPostalCodeSingleAddressItemWriter;

    /* 作業にためた内容を不規則に反映(最後から2番目) */

    /** 郵便番号作業ItemReader */
    @Autowired
    private WorksPostalItemReader worksPostalItemReader;

    /** 郵便番号作業不規則EntityProcessor */
    @Autowired
    private AddressPostalWorksIrregularProcessor addressPostalWorksIrregularProcessor;

    /** 郵便番号不規則ItemWriter */
    @Autowired
    private IrregularPostalItemWriter irregularPostalItemWriter;

    /* 作業を全Clean(最終ステップ) */
    /** 郵便番号作業全削除Tasklet */
    @Autowired
    private CleanWorksAddressPostalTasklet cleanWorksAddressPostalTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_OTHER) final Step stepOther,
            @Qualifier(STEP_RANGE) final Step stepRange, @Qualifier(STEP_SINGLE) final Step stepSingle,
            @Qualifier(STEP_UPDATE_IRREGULAR) final Step stepUpdateIrregular,
            @Qualifier(STEP_CLEAN) final Step stepClean) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepOther)
                .next(stepRange).next(stepSingle).next(stepUpdateIrregular).next(stepClean).end().build();
    }

    /**
     * StepOtherを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OTHER)
    protected Step getStepOther(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_OTHER, jobRepository)
                .<AddressPostalIrregularEntity, AddressPostalWorksEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(selectPostalCodeOtherItemReader).processor(addressPostalIrregularWorksProcessor)
                .writer(selectPostalCodeOtherItemWriter).build();
    }

    /**
     * StepRangeを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_RANGE)
    protected Step getStepRange(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_RANGE, jobRepository)
                .<AddressPostalIrregularEntity, AddressPostalWorksEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(choicePostalCodeIrregularItemReader).processor(addressPostalIrregularWorksProcessor)
                .writer(choicePostalCodeIrregularItemWriter).build();
    }

    /**
     * StepSingleを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_SINGLE)
    protected Step getStepSingle(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_SINGLE, jobRepository)
                .<AddressPostalIrregularEntity, AddressPostalWorksEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(selectPostalCodeSingleAddressItemReader).processor(addressPostalIrregularWorksProcessor)
                .writer(selectPostalCodeSingleAddressItemWriter).build();
    }

    /**
     * StepUpdateIrregularを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_UPDATE_IRREGULAR)
    protected Step getStepUpdateIrregular(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_UPDATE_IRREGULAR, jobRepository)
                .<AddressPostalWorksEntity, AddressPostalIrregularEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(worksPostalItemReader).processor(addressPostalWorksIrregularProcessor)
                .writer(irregularPostalItemWriter).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CLEAN)
    protected Step getStepClean(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CLEAN, jobRepository).tasklet(cleanWorksAddressPostalTasklet, transactionManager)
                .build();
    }

}
