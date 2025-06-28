package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;

/**
 * SuspendDuplicateWkTblParnerPoliOrgHistoryTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SuspendDuplicateWkTblParnerPoliOrgHistoryTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SuspendDuplicateWkTblParnerPoliOrgHistoryTasklet SuspendDuplicateWkTblParnerPoliOrgHistoryTasklet;

    /** 関連者企業・団体履歴Repository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTblPartnerPoliOrgHistoryRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("duplicatee_wk_tbl_partner_poli_org_history.sql")
    void test() throws Exception {

        final String reason = "アップロードファイル内で重複しているデータです";

        SuspendDuplicateWkTblParnerPoliOrgHistoryTasklet.beforeStep(this.getStepExecution());
        SuspendDuplicateWkTblParnerPoliOrgHistoryTasklet.execute(null, null);

        WkTblPartnerPoliOrgHistoryEntity entity501 = wkTblPartnerPoliOrgHistoryRepository.findById(501).get();
        assertEquals(false, entity501.getIsLatest());
        assertEquals(true, entity501.getIsFinish());
        assertEquals(reason, entity501.getJudgeReason());
        WkTblPartnerPoliOrgHistoryEntity entity502 = wkTblPartnerPoliOrgHistoryRepository.findById(502).get();
        assertEquals(false, entity502.getIsLatest());
        assertEquals(true, entity502.getIsFinish());
        assertEquals(reason, entity502.getJudgeReason());
        WkTblPartnerPoliOrgHistoryEntity entity601 = wkTblPartnerPoliOrgHistoryRepository.findById(601).get();
        assertEquals(false, entity601.getIsLatest());
        assertEquals(true, entity601.getIsFinish());
        assertEquals(reason, entity601.getJudgeReason());

        assertEquals(true, wkTblPartnerPoliOrgHistoryRepository.findById(314).get().getIsLatest());
        assertEquals(true, wkTblPartnerPoliOrgHistoryRepository.findById(315).get().getIsLatest());
        assertEquals(true, wkTblPartnerPoliOrgHistoryRepository.findById(316).get().getIsLatest());

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
