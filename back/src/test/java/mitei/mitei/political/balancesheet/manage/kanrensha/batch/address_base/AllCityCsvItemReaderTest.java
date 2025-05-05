package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

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
 * AllCityCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AllCityCsvItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private AllCityCsvItemReader allCityCsvItemReader;
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        
        StepExecution execution = getStepExecution();
        allCityCsvItemReader.beforeStep(execution);
        allCityCsvItemReader.open(execution.getExecutionContext());

        AllCityCsvDto csvDto0 = allCityCsvItemReader.read();
        assertEquals("011002", csvDto0.getLgCode());
        assertEquals("北海道札幌市", csvDto0.getAddressName());
        assertEquals("ホッカイドウサッポロシ", csvDto0.getAddressNameKana());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto0.getEffectDate());
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {
      
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/batch/address_base/","mt_city_all.csv");
        
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
