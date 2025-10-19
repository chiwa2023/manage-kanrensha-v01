package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinResultRepository;

/**
 * EraseWkTblParnerPoliOrgAddMinTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblParnerPoliOrgAddMinTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblParnerPoliOrgAddMinTasklet eraseWkTblParnerPoliOrgAddMinTasklet;

    /** 関連者企業・団体登録最小限Repository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinRepository wkTblPartnerPoliOrgAddMinRepository;

    /** 関連者企業・団体最小登録処理結果Repository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinResultRepository wkTblPartnerPoliOrgAddMinResultRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_partner_poli_org_add_min.sql", "sample_wk_tbl_partner_poli_org_add_min_result.sql" })
    void test() throws Exception {

        assertEquals(6, wkTblPartnerPoliOrgAddMinRepository.count());
        assertEquals(4, wkTblPartnerPoliOrgAddMinResultRepository.count());

        eraseWkTblParnerPoliOrgAddMinTasklet.beforeStep(this.getStepExecution());
        eraseWkTblParnerPoliOrgAddMinTasklet.execute(null, null);

        assertEquals(1, wkTblPartnerPoliOrgAddMinRepository.count());
        assertEquals(1, wkTblPartnerPoliOrgAddMinResultRepository.count());

    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
