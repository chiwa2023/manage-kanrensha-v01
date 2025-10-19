package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgRepository;

/**
 * CombineOrgWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CombineOrgWkTblFixItemWriterTest {

    /** テスト対象 */
    @Autowired
    private CombineOrgWkTblFixItemWriter combineOrgWkTblFixItemWriter;
    
    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblPartnerCombineOrgRepository wkTblPartnerCombineOrgRepository;
    

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_wk_tbl_partner_combine_org.sql")
    void test() {
        
        final int executeId = 211;
        WkTblPartnerCombineOrgEntity entityCall = wkTblPartnerCombineOrgRepository.findById(executeId).get();
        entityCall.setIsFinish(true);
        entityCall.setJudgeReason("正常終了");

        List<WkTblPartnerCombineOrgEntity> list = new ArrayList<>();
        list.add(entityCall);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerCombineOrgEntity> items = new Chunk<>(list);

        combineOrgWkTblFixItemWriter.beforeStep(this.getStepExecution());
        combineOrgWkTblFixItemWriter.write(items);

        WkTblPartnerCombineOrgEntity entityAns = wkTblPartnerCombineOrgRepository.findById(executeId).get();
        assertEquals(true, entityAns.getIsFinish());
        assertEquals("正常終了", entityAns.getJudgeReason());
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
