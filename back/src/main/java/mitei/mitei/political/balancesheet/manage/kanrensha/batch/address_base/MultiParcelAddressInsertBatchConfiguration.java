package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * アドレス・ベース・レジストリ住居複数ファイル挿入処理BathConfiguration
 */
@Configuration
public class MultiParcelAddressInsertBatchConfiguration {


    /** 機能名 */
    private static final String FUNCTION_NAME = "multiParcelAddressInsert";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_INSERT_NAME = FUNCTION_NAME + "Insert" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** アドレス・ベース・レジストリ住居CsvItemReader */
    @Autowired
    private MultiParcelCsvFileReader multiParcelCsvFileReader;

    /** アドレス・ベース・レジストリ住居CsvEntityProcessor */
    @Autowired
    private ParcelAddressCsvProcessor parcelAddressCsvProcessor;

    /** アドレス・ベース・レジストリ住居ItemWriter */
    @Autowired
    private RsdtAddressItemWriter rsdtAddressItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_INSERT_NAME) final Step stepWrite) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepWrite).end()
                .build();
    }

    /**
     * StepInsertを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_INSERT_NAME)
    protected Step getStepWrite(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INSERT_NAME, jobRepository)
                .<ParcelAddressCsvDto, AddressRsdtBaseEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(multiParcelCsvFileReader).processor(parcelAddressCsvProcessor)
                .writer(rsdtAddressItemWriter).build();
    }

}
