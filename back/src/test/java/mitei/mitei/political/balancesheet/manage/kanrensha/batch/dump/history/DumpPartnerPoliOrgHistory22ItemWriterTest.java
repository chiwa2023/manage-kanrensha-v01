package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory22Entity;

/**
 * DumpPartnerPoliOrgHistory22ItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
class DumpPartnerPoliOrgHistory22ItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private DumpPartnerPoliOrgHistory22ItemWriter dumpPartnerPoliOrgHistory22ItemWriter;

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

        String output = "/output_test/poli__org22.csv";
        Path path = Paths.get(storageFolder, output);
        StepExecution stepExecution = this.getStepExecution(path.toString());
        dumpPartnerPoliOrgHistory22ItemWriter.beforeStep(stepExecution);

        PartnerPoliOrgHistory22Entity entity00 = new PartnerPoliOrgHistory22Entity();
        entity00.setPartnerName("団体名");
        entity00.setAllAddress("全住所");
        entity00.setPoliOrgDelegate("代表者");
        entity00.setPoliOrgKanrenshaCode("123-4556");
        entity00.setOrgDelegateCode("98-7654");
        entity00.setInsertTimestamp(LocalDateTime.of(2022, 12, 5, 1, 2, 3));

        List<PartnerPoliOrgHistory22Entity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends PartnerPoliOrgHistory22Entity> items = new Chunk<>(list);
        dumpPartnerPoliOrgHistory22ItemWriter.open(stepExecution.getExecutionContext());
        dumpPartnerPoliOrgHistory22ItemWriter.write(items);

        List<String> listAns = Files.readAllLines(path);

        assertEquals("\"123-4556\",\"団体名\",\"全住所\",\"代表者\",\"98-7654\",\"2022-12-05T01:02:03\"", listAns.get(1));
    }

    private StepExecution getStepExecution(final String output) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("writeFilePath22", output).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
