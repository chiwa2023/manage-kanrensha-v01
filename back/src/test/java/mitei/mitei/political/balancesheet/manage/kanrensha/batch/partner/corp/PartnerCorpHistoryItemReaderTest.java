package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp;

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
 * PartnerCorpHistoryItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpHistoryItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PartnerCorpHistoryItemReader partnerCorpHistoryItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();
        partnerCorpHistoryItemReader.beforeStep(stepExecution);
        partnerCorpHistoryItemReader.open(stepExecution.getExecutionContext());
        
        PartnerCorpHistoryDto dto01 = partnerCorpHistoryItemReader.read();
        //"ちゃらんぽらん団体","和歌山県架空市実在町","代表者　太郎","12-345-ABCCDEF"
        assertEquals("ちゃらんぽらん団体", dto01.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getCorpDelegate());
        assertEquals("12-345-ABCCDEF", dto01.getCorpKanrenshaCode());
        
        
        PartnerCorpHistoryDto dto02 = partnerCorpHistoryItemReader.read();
        //"ちゃらんぽらん団体","和歌山県架空市実在町","代表者　太郎","12-345-ABCCDEF"
        assertEquals("ちゃらんぽらん団体", dto02.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getCorpDelegate());
        assertEquals("12-345-ABCCDEF", dto02.getCorpKanrenshaCode());

        PartnerCorpHistoryDto dto03 = partnerCorpHistoryItemReader.read();
        //"いいかげん政治団体","宮崎県架空市実在町","代表者　次郎","23-45657-QWERTY"
        assertEquals("いいかげん政治団体", dto03.getPartnerName());
        assertEquals("宮崎県架空市実在町", dto03.getAllAddress());
        assertEquals("代表者　次郎", dto03.getCorpDelegate());
        assertEquals("23-45657-QWERTY", dto03.getCorpKanrenshaCode());

        PartnerCorpHistoryDto dto04 = partnerCorpHistoryItemReader.read();
        //"政治団体A","山形県架空市実在町","代表者　三郎","12-33-44-55"
        assertEquals("政治団体A", dto04.getPartnerName());
        assertEquals("山形県架空市実在町", dto04.getAllAddress());
        assertEquals("代表者　三郎", dto04.getCorpDelegate());
        assertEquals("12-33-44-55", dto04.getCorpKanrenshaCode());

        PartnerCorpHistoryDto dto05 = partnerCorpHistoryItemReader.read();
        //"政治団体B","山梨県架空市実在町","代表者　四郎","99-88-77-66"
        assertEquals("政治団体B", dto05.getPartnerName());
        assertEquals("山梨県架空市実在町", dto05.getAllAddress());
        assertEquals("代表者　四郎", dto05.getCorpDelegate());
        assertEquals("99-88-77-66", dto05.getCorpKanrenshaCode());

        assertNull(partnerCorpHistoryItemReader.read());

    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_corp", "関連者企業団体履歴.csv");
        
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
