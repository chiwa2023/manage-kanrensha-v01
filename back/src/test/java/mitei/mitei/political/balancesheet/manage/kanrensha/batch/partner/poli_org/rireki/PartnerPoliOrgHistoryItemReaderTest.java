package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

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
 * PartnerPoliOrgHistoryItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgHistoryItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgHistoryItemReader partnerPoliOrgHistoryItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();
        partnerPoliOrgHistoryItemReader.beforeStep(stepExecution);
        partnerPoliOrgHistoryItemReader.open(stepExecution.getExecutionContext());

        PartnerPoliOrgHistoryDto dto01 = partnerPoliOrgHistoryItemReader.read();
        // "ちゃらんぽらん団体","和歌山県架空市実在町","代表者 太郎","12-345-ABCCDEF"
        assertEquals("ちゃらんぽらん団体", dto01.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getPoliOrgDelegate());
        assertEquals("12-345-ABCCDEF", dto01.getPoliOrgKanrenshaCode());

        PartnerPoliOrgHistoryDto dto02 = partnerPoliOrgHistoryItemReader.read();
        // "ちゃらんぽらん団体","和歌山県架空市実在町","代表者 太郎","12-345-ABCCDEF"
        assertEquals("ちゃらんぽらん団体", dto02.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getPoliOrgDelegate());
        assertEquals("12-345-ABCCDEF", dto02.getPoliOrgKanrenshaCode());

        PartnerPoliOrgHistoryDto dto03 = partnerPoliOrgHistoryItemReader.read();
        // "いいかげん政治団体","宮崎県架空市実在町","代表者 次郎","23-45657-QWERTY"
        assertEquals("いいかげん政治団体", dto03.getPartnerName());
        assertEquals("宮崎県架空市実在町", dto03.getAllAddress());
        assertEquals("代表者　次郎", dto03.getPoliOrgDelegate());
        assertEquals("23-45657-QWERTY", dto03.getPoliOrgKanrenshaCode());

        PartnerPoliOrgHistoryDto dto04 = partnerPoliOrgHistoryItemReader.read();
        // "政治団体A","山形県架空市実在町","代表者 三郎","12-33-44-55"
        assertEquals("政治団体A", dto04.getPartnerName());
        assertEquals("山形県架空市実在町", dto04.getAllAddress());
        assertEquals("代表者　三郎", dto04.getPoliOrgDelegate());
        assertEquals("12-33-44-55", dto04.getPoliOrgKanrenshaCode());

        PartnerPoliOrgHistoryDto dto05 = partnerPoliOrgHistoryItemReader.read();
        // "政治団体B","山梨県架空市実在町","代表者 四郎","99-88-77-66"
        assertEquals("政治団体B", dto05.getPartnerName());
        assertEquals("山梨県架空市実在町", dto05.getAllAddress());
        assertEquals("代表者　四郎", dto05.getPoliOrgDelegate());
        assertEquals("99-88-77-66", dto05.getPoliOrgKanrenshaCode());

        assertNull(partnerPoliOrgHistoryItemReader.read());
    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_poli_org",
                "関連者政治団体履歴.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
