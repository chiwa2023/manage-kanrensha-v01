package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

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
 * PartnerPoliOrgAddMiniCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgAddMiniCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgAddMiniCsvItemReader partnerPoliOrgAddMiniCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        
        StepExecution stepExecution = this.getStepExecution();
        partnerPoliOrgAddMiniCsvItemReader.beforeStep(stepExecution);
        partnerPoliOrgAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        PartnerPoliOrgAddMiniDto dto01 = partnerPoliOrgAddMiniCsvItemReader.read();
        // "ちゃらんぽらん政治団体","和歌山県架空市実在町","代表者 太郎","05"
        assertEquals("ちゃらんぽらん政治団体", dto01.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getPoliOrgDelegate());
        assertEquals("05", dto01.getDantaiKbn());

        PartnerPoliOrgAddMiniDto dto02 = partnerPoliOrgAddMiniCsvItemReader.read();
        // "ちゃらんぽらん政治団体","和歌山県架空市実在町","代表者 太郎","05"
        assertEquals("ちゃらんぽらん政治団体", dto02.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getPoliOrgDelegate());
        assertEquals("05", dto02.getDantaiKbn());

        PartnerPoliOrgAddMiniDto dto05 = partnerPoliOrgAddMiniCsvItemReader.read();
        // "いいかげん政治団体","宮崎県架空市実在町","代表者 次郎","04"
        assertEquals("いいかげん政治団体", dto05.getPartnerName());
        assertEquals("宮崎県架空市実在町", dto05.getAllAddress());
        assertEquals("代表者　次郎", dto05.getPoliOrgDelegate());
        assertEquals("04", dto05.getDantaiKbn());

        PartnerPoliOrgAddMiniDto dto06 = partnerPoliOrgAddMiniCsvItemReader.read();
        // "政治団体A","山形県架空市実在町","代表者 三郎","03"
        assertEquals("政治団体A", dto06.getPartnerName());
        assertEquals("山形県架空市実在町", dto06.getAllAddress());
        assertEquals("代表者　三郎", dto06.getPoliOrgDelegate());
        assertEquals("03", dto06.getDantaiKbn());

        PartnerPoliOrgAddMiniDto dto07 = partnerPoliOrgAddMiniCsvItemReader.read();
        // "政治団体B","山梨県架空市実在町","代表者 四郎","03"
        assertEquals("政治団体B", dto07.getPartnerName());
        assertEquals("山梨県架空市実在町", dto07.getAllAddress());
        assertEquals("代表者　四郎", dto07.getPoliOrgDelegate());
        assertEquals("03", dto07.getDantaiKbn());

        assertNull(partnerPoliOrgAddMiniCsvItemReader.read());
    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_poli_org",
                "関連者政治団体最小登録.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
