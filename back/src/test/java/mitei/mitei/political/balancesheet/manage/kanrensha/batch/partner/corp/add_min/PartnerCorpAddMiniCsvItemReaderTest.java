package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

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
 * PartnerCorpAddMiniCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpAddMiniCsvItemReaderTest {
    
    /** テスト対象 */
    @Autowired
    private PartnerCorpAddMiniCsvItemReader partnerCorpAddMiniCsvItemReader;
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();
        partnerCorpAddMiniCsvItemReader.beforeStep(stepExecution);
        partnerCorpAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        PartnerCorpAddMiniDto dto01 = partnerCorpAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto01.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getCorpDelegate());
        assertEquals("1233444", dto01.getHoujinNo());

        PartnerCorpAddMiniDto dto02 = partnerCorpAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto02.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getCorpDelegate());
        assertEquals("1233444", dto02.getHoujinNo());

        PartnerCorpAddMiniDto dto05 = partnerCorpAddMiniCsvItemReader.read();
        // "ふんだくり企業","宮崎県架空市実在町","代表者 次郎","23445677"
        assertEquals("ふんだくり企業", dto05.getPartnerName());
        assertEquals("宮崎県架空市実在町", dto05.getAllAddress());
        assertEquals("代表者　次郎", dto05.getCorpDelegate());
        assertEquals("23445677", dto05.getHoujinNo());

        PartnerCorpAddMiniDto dto06 = partnerCorpAddMiniCsvItemReader.read();
        // "職業組合A","山形県架空市実在町","代表者 三郎","3989865"
        assertEquals("職業組合A", dto06.getPartnerName());
        assertEquals("山形県架空市実在町", dto06.getAllAddress());
        assertEquals("代表者　三郎", dto06.getCorpDelegate());
        assertEquals("3989865", dto06.getHoujinNo());

        PartnerCorpAddMiniDto dto07 = partnerCorpAddMiniCsvItemReader.read();
        // "職業組合B","山梨県架空市実在町","代表者 四郎","4878754"
        assertEquals("職業組合B", dto07.getPartnerName());
        assertEquals("山梨県架空市実在町", dto07.getAllAddress());
        assertEquals("代表者　四郎", dto07.getCorpDelegate());
        assertEquals("4878754", dto07.getHoujinNo());

        assertNull(partnerCorpAddMiniCsvItemReader.read());
    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_corp",
                "関連者企業団体最小登録.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
