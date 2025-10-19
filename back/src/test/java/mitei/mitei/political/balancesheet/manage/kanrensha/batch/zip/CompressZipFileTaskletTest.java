package mitei.mitei.political.balancesheet.manage.kanrensha.batch.zip;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
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
 * CompressZipFileTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
class CompressZipFileTaskletTest {

    /** テスト対象 */
    @Autowired
    private CompressZipFileTasklet compressZipFileTasklet;

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

        final String folder = "corp_history";
        Path srcPath = Paths.get(storageFolder, "/" + folder);
        Path zipPath = Paths.get(storageFolder, folder + ".zip");

        // 処理前にファイル削除
        if (Files.exists(zipPath)) {
            Files.delete(zipPath);
        }

        compressZipFileTasklet.beforeStep(this.getStepExecution(srcPath));
        compressZipFileTasklet.execute(null, null);

        assertTrue(Files.exists(zipPath));
    }

    private StepExecution getStepExecution(final Path srcPath) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("srcPath", srcPath.toString()) //
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
