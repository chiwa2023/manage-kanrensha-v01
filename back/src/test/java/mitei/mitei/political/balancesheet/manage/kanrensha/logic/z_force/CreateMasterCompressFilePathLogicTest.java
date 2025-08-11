package mitei.mitei.political.balancesheet.manage.kanrensha.logic.z_force;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.GetCurrentResourcePath;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants.MasterMin;

/**
 * CreateMasterCompressFilePathLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
class CreateMasterCompressFilePathLogicTest {

    /** テスト対象 */
    @Autowired
    private CreateMasterCompressFilePathLogic createMasterCompressFilePathLogic;

    /** propertiesからインジェクションされたフロントの共通ダンプCSV保存先 */
    private String frontDumpFolder;

    /**
     * フロントの共通ダンプCSV保存先を取得する
     *
     * @return フロントの共通ダンプCSV保存先
     */
    public String getFrontDumpFolder() {
        return frontDumpFolder;
    }

    /**
     * フロントの共通ダンプCSV保存先を設定する
     *
     * @param frontDumpFolder フロントの共通ダンプCSV保存先
     */
    public void setFrontDumpFolder(final String frontDumpFolder) {
        this.frontDumpFolder = frontDumpFolder;
    }

    @Test
    void testZip() {

        // 比較対象
        String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent().toString();
        Path pathCompare = Paths.get(pathSaved, frontDumpFolder, MasterMin.MASTER_MIN_ZIP);

        Path path = createMasterCompressFilePathLogic.practiceZipFile(MasterMin.MASTER_MIN_ZIP);
        assertEquals(pathCompare.toAbsolutePath().toString(), path.toAbsolutePath().toString());
    }

    @Test
    void testList() {

        String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent().toString();

        List<Path> list = createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER,
                MasterMin.MASTER_MIN_CORP, MasterMin.MASTER_MIN_PERSON, MasterMin.MASTER_MIN_POLI_ORG);

        assertTrue(list.contains(Paths.get(pathSaved, frontDumpFolder, MasterCsvFileNameConstants.FOLDER_MASTER,
                MasterMin.MASTER_MIN_CORP)));
        assertTrue(list.contains(Paths.get(pathSaved, frontDumpFolder, MasterCsvFileNameConstants.FOLDER_MASTER,
                MasterMin.MASTER_MIN_PERSON)));
        assertTrue(list.contains(Paths.get(pathSaved, frontDumpFolder, MasterCsvFileNameConstants.FOLDER_MASTER,
                MasterMin.MASTER_MIN_POLI_ORG)));
    }

}
