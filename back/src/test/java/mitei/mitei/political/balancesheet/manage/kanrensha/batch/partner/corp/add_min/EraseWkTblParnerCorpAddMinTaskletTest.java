package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinResultRepository;

/**
 * EraseWkTblParnerCorpAddMinTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblParnerCorpAddMinTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblParnerCorpAddMinTasklet eraseWkTblParnerCorpAddMinTasklet;

    /** 関連者企業・団体登録最小限Repository */
    @Autowired
    private WkTblPartnerCorpAddMinRepository wkTblPartnerCorpAddMinRepository;

    /** 関連者企業・団体最小登録処理結果Repository */
    @Autowired
    private WkTblPartnerCorpAddMinResultRepository wkTblPartnerCorpAddMinResultRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_partner_corp_add_min.sql", "sample_wk_tbl_partner_corp_add_min_result.sql" })
    void test() throws Exception {

        assertEquals(6, wkTblPartnerCorpAddMinRepository.count());
        assertEquals(5, wkTblPartnerCorpAddMinResultRepository.count());

        eraseWkTblParnerCorpAddMinTasklet.beforeStep(this.getStepExecution());
        eraseWkTblParnerCorpAddMinTasklet.execute(null, null);

        assertEquals(1, wkTblPartnerCorpAddMinRepository.count());
        assertEquals(1, wkTblPartnerCorpAddMinResultRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
