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

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgJudgeRepository;

/**
 * EraseWkTblParnerPoliOrgHistoryTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblParnerPoliOrgHistoryTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblParnerPoliOrgHistoryTasklet eraseWkTblParnerPoliOrgHistoryTasklet;

    /** 関連者政治団体履歴Repository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTblPartnerPoliOrgHistoryRepository;

    /** 関連者政治団体本登録判断Repository */
    @Autowired
    private WkTblPartnerPoliOrgJudgeRepository wkTblPartnerPoliOrgJudgeRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_partner_poli_org_history.sql", "sample_wk_tbl_partner_poli_org_judge.sql" })
    void test() throws Exception {

        assertEquals(4, wkTblPartnerPoliOrgHistoryRepository.count());
        assertEquals(4, wkTblPartnerPoliOrgJudgeRepository.count());

        eraseWkTblParnerPoliOrgHistoryTasklet.beforeStep(this.getStepExecution());
        eraseWkTblParnerPoliOrgHistoryTasklet.execute(null, null);

        assertEquals(1, wkTblPartnerPoliOrgHistoryRepository.count());
        assertEquals(1, wkTblPartnerPoliOrgJudgeRepository.count());

        assertEquals(219, wkTblPartnerPoliOrgHistoryRepository.findAll().get(0).getInsertUserCode());
        assertEquals(219, wkTblPartnerPoliOrgJudgeRepository.findAll().get(0).getInsertUserCode());

    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
