package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpRepository;

/**
 * EraseWkTblMasterCorporationAddStdTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblMasterCorporationAddStdTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblMasterCorporationAddStdTasklet eraseWkTblMasterCorporationAddStdTasklet;

    /** 関連者個人マスタワークテーブル判定Repository */
    @Autowired
    private WkTblMasterCorpRepository wkTblMasterCorpRepository;

    /** 関連者個人マスタワークテーブル判定Repository */
    @Autowired
    private WkTblMasterCorpJudgeRepository wkTblMasterCorpJudgeRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_master_corp_judge.sql", "sample_wk_tbl_master_corp.sql" })
    void test() throws Exception {

        assertEquals(4, wkTblMasterCorpRepository.count());
        assertEquals(2, wkTblMasterCorpJudgeRepository.count());

        eraseWkTblMasterCorporationAddStdTasklet.beforeStep(this.getStepExecution());
        eraseWkTblMasterCorporationAddStdTasklet.execute(null, null);

        assertEquals(1, wkTblMasterCorpRepository.count());
        assertEquals(1, wkTblMasterCorpJudgeRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
