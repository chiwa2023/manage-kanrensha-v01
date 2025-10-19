package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

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
 * PartnerPersonHistoryItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonHistoryItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PartnerPersonHistoryItemReader partnerPersonHistoryItemReader;

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        

        StepExecution stepExecution = this.getStepExecution();
        partnerPersonHistoryItemReader.beforeStep(stepExecution);
        partnerPersonHistoryItemReader.open(stepExecution.getExecutionContext());

        PartnerPersonHistoryDto dto01 = partnerPersonHistoryItemReader.read();
        // "迂回献金　太郎","和歌山県架空市実在町","経営者","1-2345-ABCCDEF"
        assertEquals("迂回献金　太郎", dto01.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("経営者", dto01.getPersonShokugyou());
        assertEquals("1-2345-ABCCDEF", dto01.getPersonKanrenshaCode());


        PartnerPersonHistoryDto dto02 = partnerPersonHistoryItemReader.read();
        //"迂回献金　太郎","和歌山県架空市実在町","経営者","1-2345-ABCCDEF"
        assertEquals("迂回献金　太郎", dto02.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("経営者", dto02.getPersonShokugyou());
        assertEquals("1-2345-ABCCDEF", dto02.getPersonKanrenshaCode());
        
        PartnerPersonHistoryDto dto03 = partnerPersonHistoryItemReader.read();
        //"迂回献金　次郎","宮崎県架空市実在町","教師","2-345657-QWERTY"
        assertEquals("迂回献金　次郎", dto03.getPartnerName());
        assertEquals("宮崎県架空市実在町", dto03.getAllAddress());
        assertEquals("教師", dto03.getPersonShokugyou());
        assertEquals("2-345657-QWERTY", dto03.getPersonKanrenshaCode());
        
        PartnerPersonHistoryDto dto04 = partnerPersonHistoryItemReader.read();
        //"寄付上限　花子","山形県架空市実在町","医師","1-233-44-55"
        assertEquals("寄付上限　花子", dto04.getPartnerName());
        assertEquals("山形県架空市実在町", dto04.getAllAddress());
        assertEquals("医師", dto04.getPersonShokugyou());
        assertEquals("1-233-44-55", dto04.getPersonKanrenshaCode());
        
        PartnerPersonHistoryDto dto05 = partnerPersonHistoryItemReader.read();
        //"寄付上限　直子","山梨県架空市実在町","弁護士","9-988-77-66"
        assertEquals("寄付上限　直子", dto05.getPartnerName());
        assertEquals("山梨県架空市実在町", dto05.getAllAddress());
        assertEquals("弁護士", dto05.getPersonShokugyou());
        assertEquals("9-988-77-66", dto05.getPersonKanrenshaCode());

        
        assertNull(partnerPersonHistoryItemReader.read());
        
    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_person", "関連者個人履歴.csv");
        
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
