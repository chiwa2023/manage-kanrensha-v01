package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml; // NOPMD

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

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniRecordItemReader;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniRecordItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniWkTblFixItemReader;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniWkTblFixItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniWkTblFixProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min.PartnerPersonAddMiniRecordItemReader;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min.PartnerPersonAddMiniRecordItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min.PartnerPersonAddMiniWkTblFixItemReader;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min.PartnerPersonAddMiniWkTblFixItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min.PartnerPersonAddMiniWkTblFixProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgAddMiniRecordItemReader;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgAddMiniRecordItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgAddMiniWkTblFixItemReader;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgAddMiniWkTblFixItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgAddMiniWkTblFixProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinResultEntity;

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

    /** Step名(企業マスタ登録) */
    public static final String STEP_CORP_RECORD = FUNCTION_NAME + "CorpRecord" + STEP;

    /** Step名(企業処理結果を反映) */
    public static final String STEP_CORP_FIX = FUNCTION_NAME + "CorpFix" + STEP;

    /** Step名(個人マスタ登録) */
    public static final String STEP_PERSON_RECORD = FUNCTION_NAME + "PersonRecord" + STEP;

    /** Step名(個人処理結果を反映) */
    public static final String STEP_PERSON_FIX = FUNCTION_NAME + "PersonFix" + STEP;

    /** Step名(政治団体マスタ登録) */
    public static final String STEP_POLI_ORG_RECORD = FUNCTION_NAME + "PoliOrgRecord" + STEP;

    /** Step名(政治団体処理結果を反映) */
    public static final String STEP_POLI_ORG_FIX = FUNCTION_NAME + "PoliOrgFix" + STEP;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 250;

    /** 関連者個人マスタ登録ItemReader */
    @Autowired
    private PartnerPersonAddMiniRecordItemReader partnerPersonAddMiniRecordItemReader;

    /** 関連者個人マスタ登録ItemWriter */
    @Autowired
    private PartnerPersonAddMiniRecordItemWriter partnerPersonAddMiniRecordItemWriter;

    /** 関連者個人処理結果判定ItemReader */
    @Autowired
    private PartnerPersonAddMiniWkTblFixItemReader partnerPersonAddMiniWkTblFixItemReader;

    /** 関連者個人処理結果判定ItemProcessor */
    @Autowired
    private PartnerPersonAddMiniWkTblFixProcessor partnerPersonAddMiniWkTblFixProcessor;

    /** 関連者個人処理結果判定ItemWriter */
    @Autowired
    private PartnerPersonAddMiniWkTblFixItemWriter partnerPersonAddMiniWkTblFixItemWriter;

    /** 関連者企業・団体マスタ登録ItemReader */
    @Autowired
    private PartnerCorpAddMiniRecordItemReader partnerCorpAddMiniRecordItemReader;

    /** 関連者企業・団体マスタ登録ItemWriter */
    @Autowired
    private PartnerCorpAddMiniRecordItemWriter partnerCorpAddMiniRecordItemWriter;

    /** 関連者企業・団体処理結果判定ItemReader */
    @Autowired
    private PartnerCorpAddMiniWkTblFixItemReader partnerCorpAddMiniWkTblFixItemReader;

    /** 関連者企業・団体処理結果判定ItemProcessor */
    @Autowired
    private PartnerCorpAddMiniWkTblFixProcessor partnerCorpAddMiniWkTblFixProcessor;

    /** 関連者企業・団体処理結果判定ItemWriter */
    @Autowired
    private PartnerCorpAddMiniWkTblFixItemWriter partnerCorpAddMiniWkTblFixItemWriter;

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
    protected Job getJob(final JobRepository jobRepository, // 
            @Qualifier(STEP_PERSON_RECORD) final Step stepPersonRecord,
            @Qualifier(STEP_PERSON_FIX) final Step stepPersonFix,
            @Qualifier(STEP_CORP_RECORD) final Step stepCorpRecord, // 
            @Qualifier(STEP_CORP_FIX) final Step stepCorpFix,
            @Qualifier(STEP_POLI_ORG_RECORD) final Step stepPoliOrgRecord,
            @Qualifier(STEP_POLI_ORG_FIX) final Step stepPoliOrgFix) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepPersonRecord)
                .next(stepPersonFix).next(stepCorpRecord).next(stepCorpFix).next(stepPoliOrgRecord).next(stepPoliOrgFix)
                .end().build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_PERSON_RECORD)
    protected Step getStepPersonRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_PERSON_RECORD, jobRepository)
                .<WkTblPartnerPersonAddMinEntity, WkTblPartnerPersonAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerPersonAddMiniRecordItemReader).writer(partnerPersonAddMiniRecordItemWriter).build();
    }

    /**
     * StepFixを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_PERSON_FIX)
    protected Step getStepPersonFix(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_PERSON_FIX, jobRepository)
                .<WkTblPartnerPersonAddMinResultEntity, WkTblPartnerPersonAddMinEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(partnerPersonAddMiniWkTblFixItemReader).processor(partnerPersonAddMiniWkTblFixProcessor)
                .writer(partnerPersonAddMiniWkTblFixItemWriter).build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CORP_RECORD)
    protected Step getStepCorpRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CORP_RECORD, jobRepository)
                .<WkTblPartnerCorpAddMinEntity, WkTblPartnerCorpAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerCorpAddMiniRecordItemReader).writer(partnerCorpAddMiniRecordItemWriter).build();
    }

    /**
     * StepFixを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_CORP_FIX)
    protected Step getStepCorpFix(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_CORP_FIX, jobRepository)
                .<WkTblPartnerCorpAddMinResultEntity, WkTblPartnerCorpAddMinEntity>chunk(CHUNK_SIZE, transactionManager)
                .reader(partnerCorpAddMiniWkTblFixItemReader).processor(partnerCorpAddMiniWkTblFixProcessor)
                .writer(partnerCorpAddMiniWkTblFixItemWriter).build();
    }

    /**
     * StepRecordを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_POLI_ORG_RECORD)
    protected Step getStepPoliOrgRecord(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_POLI_ORG_RECORD, jobRepository)
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
    @Bean(STEP_POLI_ORG_FIX)
    protected Step getStepPoliOrgFix(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_POLI_ORG_FIX, jobRepository)
                .<WkTblPartnerPoliOrgAddMinResultEntity, WkTblPartnerPoliOrgAddMinEntity>chunk(CHUNK_SIZE,
                        transactionManager)
                .reader(partnerPoliOrgAddMiniWkTblFixItemReader).processor(partnerPoliOrgAddMiniWkTblFixProcessor)
                .writer(partnerPoliOrgAddMiniWkTblFixItemWriter).build();
    }

}
