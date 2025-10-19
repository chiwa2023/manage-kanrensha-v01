package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;

/**
 * EraseWkTblMasterPersonAddStdTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblMasterPersonAddStdTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblMasterPersonAddStdTasklet eraseWkTblMasterPersonAddStdTasklet;

    /** 関連者個人マスタワークテーブルRepository */
    @Autowired
    private WkTblMasterPersonRepository wkTblMasterPersonRepository;

    /** 関連者個人マスタワークテーブル判定Repository */
    @Autowired
    private WkTblMasterPersonJudgeRepository wkTblMasterPersonJudgeRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_master_person_judge.sql", "sample_wk_tbl_master_person.sql" })
    void test() throws Exception {

        assertEquals(4, wkTblMasterPersonRepository.count());
        assertEquals(2, wkTblMasterPersonJudgeRepository.count());

        eraseWkTblMasterPersonAddStdTasklet.beforeStep(this.getStepExecution());
        eraseWkTblMasterPersonAddStdTasklet.execute(null, null);

        assertEquals(1, wkTblMasterPersonRepository.count());
        assertEquals(1, wkTblMasterPersonJudgeRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
