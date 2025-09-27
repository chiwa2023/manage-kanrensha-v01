package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.BeanUtils;
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

/**
 * CombineOrgCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "master_corporation.sql", "master_person.sql", "master_political_organization.sql",
        "sample_partner_combine_org_2022.sql" })
class CombineOrgCsvProcessorTest {

    /** テスト対象 */
    @Autowired
    private CombineOrgCsvProcessor combineOrgCsvProcessor;

    /** 正常登録対象テキスト */
    private static final String RIGHT_TEXT = "正)";
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    void testCorp() throws Exception {

        combineOrgCsvProcessor.beforeStep(getStepExecution(KanrenshaKbnConstants.CORP));

        final String BLANK = "";

        // 未入力カラムがあると追加作業をしません
        WkTblPartnerCombineOrgEntity minEntity00 = combineOrgCsvProcessor.process(new PartnerCombineOrgDto());
        assertEquals(false, minEntity00.getIsAffected());
        assertEquals(
                "個人関連者コードが入力されていません;個人姓名が入力されていません;団体関連者コードが入力されていません;団体名称が入力されていません;登録開始年がシステム登録可能年の範囲でありません;登録終了年がシステム登録可能年の範囲でありません;指定された個人関連者コードが存在しません;指定された企業／団体関連者コードが存在しません;",
                minEntity00.getJudgeReason());
        assertEquals(BLANK, minEntity00.getYearArrayText());

        // 正常に読みとれます
        PartnerCombineOrgDto dto01 = new PartnerCombineOrgDto();
        dto01.setPersonKanrenshaCode("123-ABC");
        dto01.setPersonName("迂回献金　太郎");
        dto01.setOrgKanrenshaCode("ABC-456");
        dto01.setOrgName("ぼったくり企業");
        dto01.setStartYear(Short.valueOf("2023"));
        dto01.setEndYear(Short.valueOf("2024")); // NOPMD

        WkTblPartnerCombineOrgEntity entity01 = combineOrgCsvProcessor.process(dto01);
        assertEquals(true, entity01.getIsAffected());
        assertEquals(RIGHT_TEXT, entity01.getJudgeReason());
        assertEquals("2023:2024", entity01.getYearArrayText());

        // 登録年に数字以外が設定されました
        PartnerCombineOrgDto dto02 = new PartnerCombineOrgDto();
        BeanUtils.copyProperties(dto01, dto02);
        dto02.setStartYear(Short.valueOf("-1"));
        dto02.setEndYear(Short.valueOf("-1"));

        WkTblPartnerCombineOrgEntity entity02 = combineOrgCsvProcessor.process(dto02);
        assertEquals(false, entity02.getIsAffected());
        assertEquals("登録開始年が正常に指定されませんでした;登録終了年が正常に指定されませんでした;", entity02.getJudgeReason());
        assertEquals(BLANK, entity02.getYearArrayText());

        // 登録年がシステム可能年度に収まっていません
        PartnerCombineOrgDto dto03 = new PartnerCombineOrgDto();
        BeanUtils.copyProperties(dto01, dto03);
        dto03.setStartYear(Short.valueOf("1"));
        dto03.setEndYear(Short.valueOf("9999"));

        WkTblPartnerCombineOrgEntity entity03 = combineOrgCsvProcessor.process(dto03);
        assertEquals(false, entity03.getIsAffected());
        assertEquals("登録開始年がシステム登録可能年の範囲でありません;登録終了年がシステム登録可能年の範囲でありません;", entity03.getJudgeReason());
        assertEquals(BLANK, entity03.getYearArrayText());

        // 登録開始年と終了年の大小が逆です。
        PartnerCombineOrgDto dto04 = new PartnerCombineOrgDto();
        BeanUtils.copyProperties(dto01, dto04);
        dto04.setEndYear(Short.valueOf("2023"));
        dto04.setStartYear(Short.valueOf("2024"));

        WkTblPartnerCombineOrgEntity entity04 = combineOrgCsvProcessor.process(dto04);
        assertEquals(false, entity04.getIsAffected());
        assertEquals("終了年より開始年が大きい値です;", entity04.getJudgeReason());
        assertEquals(BLANK, entity04.getYearArrayText());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPoliOrg() throws Exception {

        combineOrgCsvProcessor.beforeStep(getStepExecution(KanrenshaKbnConstants.POLI_ORG));

        // 正常に読みとれます
        PartnerCombineOrgDto dto01 = new PartnerCombineOrgDto();
        dto01.setPersonKanrenshaCode("123-ABC");
        dto01.setPersonName("迂回献金　太郎");
        dto01.setOrgKanrenshaCode("UVW-987");
        dto01.setOrgName("ちゃらんぽらん政治団体");
        dto01.setStartYear(Short.valueOf("2023"));
        dto01.setEndYear(Short.valueOf("2024"));

        WkTblPartnerCombineOrgEntity entity01 = combineOrgCsvProcessor.process(dto01);
        assertEquals(true, entity01.getIsAffected());
        assertEquals(RIGHT_TEXT, entity01.getJudgeReason());
        assertEquals("2023:2024", entity01.getYearArrayText());

        // 2022年にすでに登録があります
        PartnerCombineOrgDto dto02 = new PartnerCombineOrgDto();
        dto02.setPersonKanrenshaCode("123-ABC");
        dto02.setPersonName("迂回献金　太郎");
        dto02.setOrgKanrenshaCode("UVW-987");
        dto02.setOrgName("ちゃらんぽらん政治団体");
        dto02.setStartYear(Short.valueOf("2021"));
        dto02.setEndYear(Short.valueOf("2024"));

        WkTblPartnerCombineOrgEntity entity02 = combineOrgCsvProcessor.process(dto02);
        assertEquals(false, entity02.getIsAffected());
        assertEquals("すでに登録があります(2022)年;", entity02.getJudgeReason());
        assertEquals("2021:2022:2023:2024", entity02.getYearArrayText());

    }

    private StepExecution getStepExecution(final Short kbn) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("kanrenshaKbn", String.valueOf(kbn)).addString("yearMin", "2020")
                .addString("yearMax", "2025").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
