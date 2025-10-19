package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileParseException;
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
 * PartnerCorpAddMiniCsvItemReaderFormat単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpAddMiniCsvItemReaderFormatTest {

    /** テスト対象 */
    @Autowired
    private PartnerCorpAddMiniCsvItemReader partnerCorpAddMiniCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void testFormat() throws Exception {

        StepExecution stepExecution = this.getStepExecutionFormat();
        partnerCorpAddMiniCsvItemReader.beforeStep(stepExecution);
        partnerCorpAddMiniCsvItemReader.open(stepExecution.getExecutionContext());

        PartnerCorpAddMiniDto dto01 = partnerCorpAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto01.getPartnerName()); // NOPMD
        assertEquals("和歌山県架空市実在町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getCorpDelegate()); // NOPMD
        assertEquals("1233444", dto01.getHoujinNo()); // NOPMD

        PartnerCorpAddMiniDto dto02 = partnerCorpAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto02.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto02.getAllAddress());
        assertEquals("代表者　太郎", dto02.getCorpDelegate());
        assertEquals("1233444", dto02.getHoujinNo());

        // 改行対応
        PartnerCorpAddMiniDto dto03 = partnerCorpAddMiniCsvItemReader.read();
        // "ぼったくり企業","和歌山県架空
        // 市実在町","代表者 太郎","1233444"
        assertEquals("ぼったくり企業", dto03.getPartnerName());
        assertEquals("和歌山県架\n空市実在町", dto03.getAllAddress());
        assertEquals("代表者　太郎", dto03.getCorpDelegate());
        assertEquals("1233444", dto03.getHoujinNo());

        // "がなくても大丈夫
        PartnerCorpAddMiniDto dto04 = partnerCorpAddMiniCsvItemReader.read();
        // ぼったくり企業,"和歌山県\"架空市実在町",代表者 太郎,1233444
        assertEquals("ぼったくり企業", dto04.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto04.getAllAddress());
        assertEquals("代表者　太郎", dto04.getCorpDelegate());
        assertEquals("1233444", dto04.getHoujinNo());

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

        // 行読み取り時にエスケープ考慮がない模様のため、引用文字はデータに使えなくなると思われる
        assertThrows(FlatFileParseException.class,() -> partnerCorpAddMiniCsvItemReader.read());
    }

    private StepExecution getStepExecutionFormat() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_corp",
                "関連者企業団体形式テスト.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
