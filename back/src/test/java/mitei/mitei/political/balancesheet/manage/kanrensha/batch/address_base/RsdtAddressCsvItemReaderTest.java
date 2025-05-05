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
 * RsdtAddressCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RsdtAddressCsvItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RsdtAddressCsvItemReader rsdtAddressCsvItemReader;
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        StepExecution execution = getStepExecution();
        rsdtAddressCsvItemReader.beforeStep(execution);
        rsdtAddressCsvItemReader.open(execution.getExecutionContext());

        RsdtAddressCsvDto csvDto0 = rsdtAddressCsvItemReader.read();

        // 011011,0001001,001,001,,札幌市,中央区,旭ケ丘,一丁目,,,1,1,,0,1,1,0,1947-04-17,,0,

        assertEquals("011011", csvDto0.getLgCode());
        assertEquals("0001001", csvDto0.getMachiazaId());
        assertEquals("001", csvDto0.getBlkId());
        assertEquals("001", csvDto0.getRsdtId());
        assertEquals("", csvDto0.getRsdt2Id());
        assertEquals("札幌市", csvDto0.getCity());
        assertEquals("中央区", csvDto0.getWard());
        assertEquals("旭ケ丘", csvDto0.getOazaCho());
        assertEquals("一丁目", csvDto0.getChome());
        assertEquals("", csvDto0.getKoaza());
        assertEquals("", csvDto0.getMachiazaDist());
        assertEquals("1", csvDto0.getBlkNum());
        assertEquals("1", csvDto0.getRsdtNum());
        assertEquals("", csvDto0.getRsdtNum2());
        assertEquals(0, csvDto0.getBasicRsdtDiv());
        assertEquals(1, csvDto0.getRsdtAddrFlg());
        assertEquals(0, csvDto0.getStatusFlg());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto0.getEffectDate());
        assertEquals(LocalDate.of(1948, 7, 29), csvDto0.getAbolitionDate());
        
    }

    
    private StepExecution getStepExecution() throws URISyntaxException, IOException {
        
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/batch/address_base/rsdt","mt_rsdtdsp_rsdt_pref01.csv");
        
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
