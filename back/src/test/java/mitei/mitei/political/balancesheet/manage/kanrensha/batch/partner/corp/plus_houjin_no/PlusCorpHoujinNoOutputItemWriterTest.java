package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PlusCorpHoujinNoOutputItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
class PlusCorpHoujinNoOutputItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PlusCorpHoujinNoOutputItemWriter plusCorpHoujinNoOutputItemWriter;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    @Test
    void test() throws Exception {

        ParterCorpPlusHoujinNoDto dto = new ParterCorpPlusHoujinNoDto();
        dto.setPartnerName("ふんだくり企業");
        dto.setAllAddress("宮崎県架空市実在町");
        dto.setCorpDelegate("代表者　次郎");
        dto.setHoujinNo("1234567890222");
        dto.setIsForeignCorpText("該当");

        List<ParterCorpPlusHoujinNoDto> list = new ArrayList<>();
        list.add(dto);

        // Chunkを作成してセット
        Chunk<? extends ParterCorpPlusHoujinNoDto> items = new Chunk<>(list);

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        Path path = Paths.get(storageFolder, String.valueOf(userDto.getUserPersonCode()), "test",
                "partner_corp_plus_houjin_no.csv");

        StepExecution execution = getStepExecution(path);

        plusCorpHoujinNoOutputItemWriter.beforeStep(execution);
        plusCorpHoujinNoOutputItemWriter.open(execution.getExecutionContext());
        plusCorpHoujinNoOutputItemWriter.write(items);

        List<String> listAns = Files.readAllLines(path);
        
        assertEquals("\"名称\",\"全住所\",\"代表者名\",\"法人番号\",\"外国籍企業該否\"", listAns.get(0));
        assertEquals("\"ふんだくり企業\",\"宮崎県架空市実在町\",\"代表者　次郎\",\"1234567890222\",\"該当\"", listAns.get(1));

    }

    private StepExecution getStepExecution(final Path path) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("writeFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
