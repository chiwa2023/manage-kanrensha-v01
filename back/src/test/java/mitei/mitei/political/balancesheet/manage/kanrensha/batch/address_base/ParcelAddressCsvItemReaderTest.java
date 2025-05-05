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
 * ParcelAddressCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ParcelAddressCsvItemReaderTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ParcelAddressCsvItemReader parcelAddressCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        parcelAddressCsvItemReader.beforeStep(execution);
        parcelAddressCsvItemReader.open(execution.getExecutionContext());

        ParcelAddressCsvDto csvDto0 = parcelAddressCsvItemReader.read();

        // 011029,0001003,000010000100000,札幌市,北区,あいの里一条,三丁目,,,1,1,,1,1,,1947-04-17,,1,,

        assertEquals("011029", csvDto0.getLgCode());
        assertEquals("0001003", csvDto0.getMachiazaId());
        assertEquals("000010000100000", csvDto0.getPrcId());
        assertEquals("札幌市", csvDto0.getCity());
        assertEquals("北区", csvDto0.getWard());
        assertEquals("あいの里一条", csvDto0.getOazaCho());
        assertEquals("三丁目", csvDto0.getChome());
        assertEquals("", csvDto0.getKoaza());
        assertEquals("", csvDto0.getMachiazaDist());
        assertEquals("1", csvDto0.getPrcNum1());
        assertEquals("1", csvDto0.getPrcNum2());
        assertEquals("", csvDto0.getPrcNum3());
        assertEquals(1, csvDto0.getRsdtAddrFlg());
        assertEquals(1, csvDto0.getPrcRecFlg());
        assertEquals(0, csvDto0.getPrcAreaCode());
        assertEquals(LocalDate.of(1947, 4, 17), csvDto0.getEffectDate());
        assertEquals(LocalDate.of(1948, 7, 29), csvDto0.getAbolitionDate());

    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/batch/address_base/parcel",
                "mt_parcel_city011029.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
