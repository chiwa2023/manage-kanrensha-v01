package mitei.mitei.political.balancesheet.manage.kanrensha.controller.z_force;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.GetCurrentResourcePath;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants.SabunMasterMin;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * ForceDumpStdMasterSabunController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
@Sql("master_corporation.sql")
@Sql("master_person.sql")
@Sql("master_political_organization.sql")
class ForceDumpStdMasterSabunControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

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
    @Tag("TableTruncate")
    void testCreate() throws Exception {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();

        // 配下を全削除
        Path pathRoot = Paths.get(pathSaved, frontDumpFolder, MasterCsvFileNameConstants.FOLDER_MASTER_SABUN);
        if (Files.exists(pathRoot)) {
            Files.walk(pathRoot).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(java.io.File::delete);
        }

        // ファイル否存在確認
        Path pathCorp = Paths.get(pathRoot.toString(), SabunMasterMin.SABUN_MIN_CORP);
        Path pathPerson = Paths.get(pathRoot.toString(), SabunMasterMin.SABUN_MIN_PERSON);
        Path pathPoliOrg = Paths.get(pathRoot.toString(), SabunMasterMin.SABUN_MIN_POLI_ORG);
        assertFalse(Files.exists(pathCorp));
        assertFalse(Files.exists(pathPerson));
        assertFalse(Files.exists(pathPoliOrg));

        ForceDumpCapsuleDto capsuleDto = new ForceDumpCapsuleDto();
        capsuleDto.setDateStart(LocalDate.of(2024, 1, 1));
        capsuleDto.setDateEnd(LocalDate.of(2024, 12, 31));
        capsuleDto.setIsExecuteCorp(true);
        capsuleDto.setIsExecutePerson(true);
        capsuleDto.setIsExecutePoliOrg(true);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/dump-master-std-sabun/execute";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

        // このメソッドのみを単独で動かした場合のみファイルの存在を目視で確認(非同期動作なので、この段階で作業完了しているとは限らない)
    }

    @Test
    @Tag("TableTruncate")
    void testNotExecute() throws Exception {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();

        // 配下を全削除
        Path pathRoot = Paths.get(pathSaved, frontDumpFolder, MasterCsvFileNameConstants.FOLDER_MASTER_SABUN);
        if (Files.exists(pathRoot)) {
            Files.walk(pathRoot).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(java.io.File::delete);
        }

        // ファイル否存在確認
        Path pathCorp = Paths.get(pathRoot.toString(), SabunMasterMin.SABUN_MIN_CORP);
        Path pathPerson = Paths.get(pathRoot.toString(), SabunMasterMin.SABUN_MIN_PERSON);
        Path pathPoliOrg = Paths.get(pathRoot.toString(), SabunMasterMin.SABUN_MIN_POLI_ORG);
        assertFalse(Files.exists(pathCorp));
        assertFalse(Files.exists(pathPerson));
        assertFalse(Files.exists(pathPoliOrg));

        ForceDumpCapsuleDto capsuleDto = new ForceDumpCapsuleDto();
        capsuleDto.setDateStart(LocalDate.of(2024, 1, 1));
        capsuleDto.setDateEnd(LocalDate.of(2024, 12, 31));
        capsuleDto.setIsExecuteCorp(false);
        capsuleDto.setIsExecutePerson(false);
        capsuleDto.setIsExecutePoliOrg(false);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/dump-master-std-sabun/execute";

        // 作業実施しない(204)
        assertEquals(HttpStatus.NO_CONTENT.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isNoContent()).andReturn().getResponse().getStatus());
    }

}
