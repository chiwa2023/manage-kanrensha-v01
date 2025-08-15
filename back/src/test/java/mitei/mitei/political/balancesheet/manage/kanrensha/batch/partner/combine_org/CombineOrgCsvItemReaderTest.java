package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;
import java.nio.file.Paths;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * CombineOrgCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CombineOrgCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private CombineOrgCsvItemReader combineOrgCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();
        combineOrgCsvItemReader.beforeStep(stepExecution);
        combineOrgCsvItemReader.open(stepExecution.getExecutionContext());

        PartnerCombineOrgDto dto01 = combineOrgCsvItemReader.read();
        // "12-34567-8901-2345-67890","迂回献金
        // 太郎","1-ABCD-67-890123-4567890","ふんだくり企業","2021","abcde"
        assertEquals("12-34567-8901-2345-67890", dto01.getPersonKanrenshaCode());
        assertEquals("迂回献金　太郎", dto01.getPersonName());
        assertEquals("1-ABCD-67-890123-4567890", dto01.getOrgKanrenshaCode());
        assertEquals("ふんだくり企業", dto01.getOrgName());
        assertEquals(Short.valueOf("2021"), dto01.getStartyear());
        assertEquals(Short.valueOf("-1"), dto01.getEndyear()); // 数字変換できないときは-1

        PartnerCombineOrgDto dto02 = combineOrgCsvItemReader.read();
        // "12-34567-8901-2345-67891","迂回献金
        // 花子","1-ABCD-67-890123-4567890","ふんだくり企業","999999999","2024"
        assertEquals("12-34567-8901-2345-67891", dto02.getPersonKanrenshaCode());
        assertEquals("迂回献金　花子", dto02.getPersonName());
        assertEquals("1-ABCD-67-890123-4567890", dto02.getOrgKanrenshaCode());
        assertEquals("ふんだくり企業", dto02.getOrgName());
        assertEquals(Short.valueOf("-1"), dto02.getStartyear());
        assertEquals(Short.valueOf("2024"), dto02.getEndyear());

        assertNull(combineOrgCsvItemReader.read());
    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/combine_org",
                "sample_combine_corp.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
