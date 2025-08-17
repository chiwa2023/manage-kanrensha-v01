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

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.PartnerCombineOrg2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.PartnerCombineOrg2025Repository;

/**
 * CombineOrgRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CombineOrgRecordItemWriterTest {

    /** テスト対象 */
    @Autowired
    private CombineOrgRecordItemWriter combineOrgRecordItemWriter;

    /** 個人団体紐づけRepository(2025) */
    @Autowired
    private PartnerCombineOrg2025Repository partnerCombineOrg2025Repository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_partner_combine_org_2025.sql")
    void test() throws Exception {

        WkTblPartnerCombineOrgEntity entity00 = new WkTblPartnerCombineOrgEntity();

        entity00.setKanrenshaKbn(KanrenshaKbnConstants.CORP);
        entity00.setPersonKanrenshaCode("111-222");
        entity00.setPersonName("迂回献金　太郎");
        entity00.setOrgKanrenshaCode("333-555");
        entity00.setOrgName("ちゃらんぽらん政治団体");
        entity00.setStartYear(Short.valueOf("2025"));
        entity00.setEndYear(Short.valueOf("2025"));
        entity00.setYearArrayText("2025");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        List<WkTblPartnerCombineOrgEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerCombineOrgEntity> items = new Chunk<>(listLoad);

        combineOrgRecordItemWriter.beforeStep(this.getStepExecution());
        combineOrgRecordItemWriter.write(items);

        List<PartnerCombineOrg2025Entity> listAns = partnerCombineOrg2025Repository.findAll();
        assertEquals(1, listAns.size());

        PartnerCombineOrg2025Entity entityAns = listAns.get(0);

        assertEquals(entity00.getKanrenshaKbn(), entityAns.getKanrenshaKbn());
        assertEquals(entity00.getPersonKanrenshaCode(), entityAns.getPersonKanrenshaCode());
        assertEquals(entity00.getPersonName(), entityAns.getPersonName());
        assertEquals(entity00.getOrgKanrenshaCode(), entityAns.getOrgKanrenshaCode());
        assertEquals(entity00.getOrgName(), entityAns.getOrgName());
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
