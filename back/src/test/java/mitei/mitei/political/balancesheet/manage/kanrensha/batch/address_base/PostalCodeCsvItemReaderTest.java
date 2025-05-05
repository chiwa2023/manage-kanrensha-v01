package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
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
 * PostalCodeCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeCsvItemReader postalCodeCsvItemReader;
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        StepExecution execution = getStepExecution();
        postalCodeCsvItemReader.beforeStep(execution);
        postalCodeCsvItemReader.open(execution.getExecutionContext());

        PostalCodeCsvDto csvDto00 = postalCodeCsvItemReader.read();

        assertEquals("01101", csvDto00.getLgCode());
        assertEquals("0600000", csvDto00.getPostal1());
        assertEquals("0000", csvDto00.getPostal2());
        assertEquals("以下に掲載がない場合", csvDto00.getAddressOrg());
        assertEquals("札幌市中央区", csvDto00.getAddressName());
        assertEquals(true, csvDto00.getIsGyoseikuData());
        
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {
        
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/batch/address_base/","utf_ken_all.csv");
        
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
