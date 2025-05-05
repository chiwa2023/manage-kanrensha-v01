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


/**
 * アドレス・ベース・レジストリ住居テーブル複写BatchConfiguration
 */
@Configuration
public class CloneRsdtTableBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "cloneRsdtTable";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名 */
    public static final String STEP_CREATE_NAME = FUNCTION_NAME + "Create" + STEP;

    /** 住居テーブル複製Tasklet */
    @Autowired
    private CloneRsdtTableTasklet cloneRsdtTableTasklet;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_CREATE_NAME) final Step stepWrite) {

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
    @Bean(STEP_CREATE_NAME)
    protected Step getStepWrite(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CREATE_NAME, jobRepository).tasklet(cloneRsdtTableTasklet, transactionManager)
                .build();
    }

}
