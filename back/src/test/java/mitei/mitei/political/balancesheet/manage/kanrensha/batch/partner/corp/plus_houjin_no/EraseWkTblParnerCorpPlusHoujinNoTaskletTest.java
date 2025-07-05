package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpPlusHojinNoRepository;

/**
 * EraseWkTblParnerCorpPlusHoujinNoTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseWkTblParnerCorpPlusHoujinNoTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblParnerCorpPlusHoujinNoTasklet eraseWkTblParnerCorpPlusHoujinNoTasklet;
    

    /** 関連者企業・団体最小登録処理結果Repository */
    @Autowired
    private WkTblPartnerCorpPlusHojinNoRepository wkTblPartnerCorpPlusHojinNoRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql( "sample_wk_tbl_partner_corp_plus_hojin_no.sql")
    void test()throws Exception {
        
        assertEquals(4, wkTblPartnerCorpPlusHojinNoRepository.count());

        eraseWkTblParnerCorpPlusHoujinNoTasklet.beforeStep(this.getStepExecution());
        eraseWkTblParnerCorpPlusHoujinNoTasklet.execute(null, null);

        assertEquals(1, wkTblPartnerCorpPlusHojinNoRepository.count());
    }


    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
