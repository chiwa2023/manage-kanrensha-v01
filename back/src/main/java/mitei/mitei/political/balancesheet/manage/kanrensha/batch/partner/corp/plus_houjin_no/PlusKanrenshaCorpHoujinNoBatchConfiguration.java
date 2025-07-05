package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniCsvItemReader;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpPlusHojinNoEntity;

/**
 * 関連者企業・団体最小Csvに法人番号追加するBatchConfiguration
 */
@Configuration
public class PlusKanrenshaCorpHoujinNoBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "plusKanrenshaCorpHoujinNo";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** Step名(削除) */
    public static final String STEP_ERASE = FUNCTION_NAME + "EraseWkTbl" + STEP;

    /** Step名(ファイルをワークテーブルに複写) */
    public static final String STEP_INPUT = FUNCTION_NAME + "Input" + STEP;

    /** Step名(ワークテーブルをファイルに書き出し) */
    public static final String STEP_OUTPUT = FUNCTION_NAME + "Output" + STEP;

    /** ワークテーブル初期化Tasklet */
    @Autowired
    private EraseWkTblParnerCorpPlusHoujinNoTasklet eraseWkTblParnerCorpPlusHoujinNoTasklet;

    /** 関連者企業・団体Csv最小読み取りItemReader */
    @Autowired
    private PartnerCorpAddMiniCsvItemReader partnerCorpAddMiniCsvItemReader;

    /** 関連者企業・団体Csv読み取りItemProcessor */
    @Autowired
    private PlusCorpHoujinNoInputProcessor plusCorpHoujinNoInputProcessor;

    /** 関連者企業・団体Csv読み取りItemWriter */
    @Autowired
    private PlusCorpHoujinNoInputItemWriter plusCorpHoujinNoInputItemWriter;

    /** 関連者企業・団体Csv読み取りItemWriter */
    @Autowired
    private PlusCorpHoujinNoOutputItemReader plusCorpHoujinNoOutputItemReader;

    /** 関連者企業・団体Csv読み取りItemProcessor */
    @Autowired
    private PlusCorpHoujinNoOutputProcessor plusCorpHoujinNoOutputProcessor;

    /** 関連者企業・団体Csv読み取りItemWriter */
    @Autowired
    private PlusCorpHoujinNoOutputItemWriter plusCorpHoujinNoOutputItemWriter;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_ERASE) final Step stepErase,
            @Qualifier(STEP_INPUT) final Step stepInput, @Qualifier(STEP_OUTPUT) final Step stepOutput) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepErase)
                .next(stepInput).next(stepOutput).end().build();
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
                .tasklet(eraseWkTblParnerCorpPlusHoujinNoTasklet, transactionManager).build();
    }

    /**
     * StepInputを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_INPUT)
    protected Step getStepInput(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_INPUT, jobRepository)
                .<PartnerCorpAddMiniDto, WkTblPartnerCorpPlusHojinNoEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerCorpAddMiniCsvItemReader).processor(plusCorpHoujinNoInputProcessor)
                .writer(plusCorpHoujinNoInputItemWriter).build();
    }

    /**
     * StepOutputを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT)
    protected Step getStepOutput(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_OUTPUT, jobRepository)
                .<WkTblPartnerCorpPlusHojinNoEntity, ParterCorpPlusHoujinNoDto>chunk(CHUNK_SIZE, transactionManager)
                .reader(plusCorpHoujinNoOutputItemReader).processor(plusCorpHoujinNoOutputProcessor)
                .writer(plusCorpHoujinNoOutputItemWriter).build();
    }

}
