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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;

/**
 * SuspendDuplicateWkTblMasterPoliOrgAddStdTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SuspendDuplicateWkTblMasterPoliOrgAddStdTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SuspendDuplicateWkTblMasterPoliOrgAddStdTasklet suspendDuplicateWkTblMasterPoliOrgAddStdTasklet;

    /** 関連者個人マスタ標準ワークテーブル */
    @Autowired
    private WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("duplicate_sample_wk_tbl_master_poli_org.sql")
    void test() throws Exception {

        final String reason = "アップロードファイル内で重複しているデータです";

        suspendDuplicateWkTblMasterPoliOrgAddStdTasklet.beforeStep(this.getStepExecution());
        suspendDuplicateWkTblMasterPoliOrgAddStdTasklet.execute(null, null);

        WkTblMasterPoliOrgEntity entity501 = wkTblMasterPoliOrgRepository.findById(501).get();
        assertEquals(false, entity501.getIsLatest());
        assertEquals(true, entity501.getIsFinish());
        assertEquals(reason, entity501.getJudgeReason());
        WkTblMasterPoliOrgEntity entity502 = wkTblMasterPoliOrgRepository.findById(502).get();
        assertEquals(false, entity502.getIsLatest());
        assertEquals(true, entity502.getIsFinish());
        assertEquals(reason, entity502.getJudgeReason());
        WkTblMasterPoliOrgEntity entity601 = wkTblMasterPoliOrgRepository.findById(601).get();
        assertEquals(false, entity601.getIsLatest());
        assertEquals(true, entity601.getIsFinish());
        assertEquals(reason, entity601.getJudgeReason());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", Long.parseLong(userId.toString()))
                .addLong("userCode", Long.parseLong(userCode.toString())).addString("userName", userName)
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
