package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;

/**
 * EraseWkTblMasterPoliticalOrganizationAddStdTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblMasterPoliticalOrganizationAddStdTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblMasterPoliticalOrganizationAddStdTasklet eraseWkTblMasterPoliticalOrganizationAddStdTasklet;

    /** 関連者個人マスタワークテーブル判定Repository */
    @Autowired
    private WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository;

    /** 関連者個人マスタワークテーブル判定Repository */
    @Autowired
    private WkTblMasterPoliOrgJudgeRepository wkTblMasterPoliOrgJudgeRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_master_poli_org_judge.sql", "sample_wk_tbl_master_poli_org.sql" })
    void test() throws Exception {

        assertEquals(4, wkTblMasterPoliOrgRepository.count());
        assertEquals(2, wkTblMasterPoliOrgJudgeRepository.count());

        eraseWkTblMasterPoliticalOrganizationAddStdTasklet.beforeStep(this.getStepExecution());
        eraseWkTblMasterPoliticalOrganizationAddStdTasklet.execute(null, null);

        assertEquals(1, wkTblMasterPoliOrgRepository.count());
        assertEquals(1, wkTblMasterPoliOrgJudgeRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
