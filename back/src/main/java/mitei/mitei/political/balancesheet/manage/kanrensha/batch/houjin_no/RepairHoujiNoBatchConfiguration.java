package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;

/**
 * 法人番号修正BatchConfiguration
 */
@Configuration
public class RepairHoujiNoBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "repairHoujiNo";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(Insert) */
    public static final String STEP_INSERT = FUNCTION_NAME + "Insert" + STEP;

    /** Step名(Remove) */
    public static final String STEP_REMOVE = FUNCTION_NAME + "Remove" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /* その他は住居に対応データが存在するのを確認して有効にする */

    /** 法人番号CsvItemReader */
    @Autowired
    private HoujinNoCsvItemReader houjinNoCsvItemReader;

    /** 法人番号CsvテーブルProcessor */
    @Autowired
    private HoujinNoCsvEntityProcessor houjinNoCsvEntityProcessor;

    /** 法人番号テーブルItemWriter */
    @Autowired
    private HoujinNoTableCheckLatestItemWriter houjinNoTableCheckLatestItemWriter;


    /** 法人番号重複削除Tasklet */
    @Autowired
    private RemoveDuplicateLatestTasklet removeDuplicateLatestTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_INSERT) final Step stepInsert,
            @Qualifier(STEP_REMOVE) final Step stepRemove) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepInsert)
                .next(stepRemove).end().build();
    }

    /**
     * StepInsertを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_INSERT)
    protected Step getStepInsert(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INSERT, jobRepository)
                .<HoujinNoCsvDto, HoujinNoLatestEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(houjinNoCsvItemReader).processor(houjinNoCsvEntityProcessor)
                .writer(houjinNoTableCheckLatestItemWriter).build();
    }


    /**
     * StepRemoveを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_REMOVE)
    protected Step getStepRemove(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_REMOVE, jobRepository).tasklet(removeDuplicateLatestTasklet, transactionManager)
                .build();
    }
}
