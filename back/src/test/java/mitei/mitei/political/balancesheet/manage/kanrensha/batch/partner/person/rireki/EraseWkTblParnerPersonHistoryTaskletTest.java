package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonJudgeRepository;

/**
 * EraseWkTblParnerPersonHistoryTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblParnerPersonHistoryTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblParnerPersonHistoryTasklet eraseWkTblParnerPersonHistoryTasklet;

    /** 関連者企業・団体履歴Repository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTblPartnerPersonHistoryRepository;

    /** 関連者企業・団体本登録判断Repository */
    @Autowired
    private WkTblPartnerPersonJudgeRepository wkTblPartnerPersonJudgeRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_partner_person_history.sql", "sample_wk_tbl_partner_person_judge.sql" })
    void test() throws Exception {

        assertEquals(4, wkTblPartnerPersonHistoryRepository.count());
        assertEquals(4, wkTblPartnerPersonJudgeRepository.count());

        eraseWkTblParnerPersonHistoryTasklet.beforeStep(this.getStepExecution());
        eraseWkTblParnerPersonHistoryTasklet.execute(null, null);

        assertEquals(1, wkTblPartnerPersonHistoryRepository.count());
        assertEquals(1, wkTblPartnerPersonJudgeRepository.count());

        assertEquals(219, wkTblPartnerPersonHistoryRepository.findAll().get(0).getInsertUserCode());
        assertEquals(219, wkTblPartnerPersonJudgeRepository.findAll().get(0).getInsertUserCode());

    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
