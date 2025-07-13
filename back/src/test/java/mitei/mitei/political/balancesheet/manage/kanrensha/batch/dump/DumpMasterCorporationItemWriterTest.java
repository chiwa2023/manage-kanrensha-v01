package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
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

/**
 * DumpMasterCorporationItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
class DumpMasterCorporationItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private DumpMasterCorporationItemWriter dumpMasterCorporationItemWriter;

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
    @Tag("TableTruncate")
    void test() throws Exception {

        String output = "/output_test/master_corp.csv";
        Path path = Paths.get(storageFolder, output);

        StepExecution stepExecution = this.getStepExecution(path.toString());

        dumpMasterCorporationItemWriter.beforeStep(stepExecution);

        MasterCorporationDto dto = new MasterCorporationDto();
        dto.setCorpKanrenshaCode("123-4567");
        dto.setHoujinNo("987-6543");
        dto.setPartnerName("超元素製造組合");
        dto.setAllAddress("和歌山県架空市山麓町");
        dto.setCorpDelegate("組合長　花子");
        dto.setInsertTimestamp(LocalDateTime.of(2022,12,5,1,2,3));

        List<MasterCorporationDto> list = new ArrayList<>();
        list.add(dto);

        // Chunkを作成してセット
        Chunk<? extends MasterCorporationDto> items = new Chunk<>(list);
        dumpMasterCorporationItemWriter.open(stepExecution.getExecutionContext());
        dumpMasterCorporationItemWriter.write(items);

        List<String> listAns = Files.readAllLines(path);

        assertEquals("\"123-4567\",\"987-6543\",\"超元素製造組合\",\"和歌山県架空市山麓町\",\"組合長　花子\",\"2022-12-05T01:02:03\"",
                listAns.get(1));
    }

    private StepExecution getStepExecution(final String output) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("writeFilePath", output).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
