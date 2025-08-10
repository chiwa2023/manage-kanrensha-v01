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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * ForceDumpHistorySabunController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
@Sql({ "partner_corp_history_01.sql", "partner_corp_history_02.sql", "partner_corp_history_03.sql",
        "partner_corp_history_04.sql", "partner_corp_history_05.sql", "partner_corp_history_06.sql",
        "partner_corp_history_07.sql", "partner_corp_history_08.sql", "partner_corp_history_09.sql",
        "partner_corp_history_10.sql", "partner_corp_history_11.sql", "partner_corp_history_12.sql",
        "partner_corp_history_13.sql", "partner_corp_history_14.sql", "partner_corp_history_15.sql",
        "partner_corp_history_16.sql", "partner_corp_history_16.sql", "partner_corp_history_17.sql",
        "partner_corp_history_18.sql", "partner_corp_history_19.sql", "partner_corp_history_20.sql",
        "partner_corp_history_21.sql", "partner_corp_history_22.sql", "partner_corp_history_23.sql",
        "partner_corp_history_24.sql", "partner_corp_history_25.sql", "partner_corp_history_26.sql",
        "partner_corp_history_27.sql", "partner_corp_history_28.sql", "partner_corp_history_29.sql",
        "partner_corp_history_30.sql", "partner_corp_history_31.sql", "partner_corp_history_32.sql",
        "partner_corp_history_33.sql", "partner_corp_history_34.sql", "partner_corp_history_35.sql",
        "partner_corp_history_36.sql", "partner_corp_history_37.sql", "partner_corp_history_38.sql",
        "partner_corp_history_39.sql", "partner_corp_history_40.sql", "partner_corp_history_41.sql",
        "partner_corp_history_42.sql", "partner_corp_history_43.sql", "partner_corp_history_44.sql",
        "partner_corp_history_45.sql", "partner_corp_history_46.sql", "partner_corp_history_47.sql",
        "partner_corp_history_99.sql", "partner_person_history_01.sql", "partner_person_history_02.sql",
        "partner_person_history_03.sql", "partner_person_history_04.sql", "partner_person_history_05.sql",
        "partner_person_history_06.sql", "partner_person_history_07.sql", "partner_person_history_08.sql",
        "partner_person_history_09.sql", "partner_person_history_10.sql", "partner_person_history_11.sql",
        "partner_person_history_12.sql", "partner_person_history_13.sql", "partner_person_history_14.sql",
        "partner_person_history_15.sql", "partner_person_history_16.sql", "partner_person_history_16.sql",
        "partner_person_history_17.sql", "partner_person_history_18.sql", "partner_person_history_19.sql",
        "partner_person_history_20.sql", "partner_person_history_21.sql", "partner_person_history_22.sql",
        "partner_person_history_23.sql", "partner_person_history_24.sql", "partner_person_history_25.sql",
        "partner_person_history_26.sql", "partner_person_history_27.sql", "partner_person_history_28.sql",
        "partner_person_history_29.sql", "partner_person_history_30.sql", "partner_person_history_31.sql",
        "partner_person_history_32.sql", "partner_person_history_33.sql", "partner_person_history_34.sql",
        "partner_person_history_35.sql", "partner_person_history_36.sql", "partner_person_history_37.sql",
        "partner_person_history_38.sql", "partner_person_history_39.sql", "partner_person_history_40.sql",
        "partner_person_history_41.sql", "partner_person_history_42.sql", "partner_person_history_43.sql",
        "partner_person_history_44.sql", "partner_person_history_45.sql", "partner_person_history_46.sql",
        "partner_person_history_47.sql", "partner_person_history_99.sql", "partner_poli_org_history_01.sql",
        "partner_poli_org_history_02.sql", "partner_poli_org_history_03.sql", "partner_poli_org_history_04.sql",
        "partner_poli_org_history_05.sql", "partner_poli_org_history_06.sql", "partner_poli_org_history_07.sql",
        "partner_poli_org_history_08.sql", "partner_poli_org_history_09.sql", "partner_poli_org_history_10.sql",
        "partner_poli_org_history_11.sql", "partner_poli_org_history_12.sql", "partner_poli_org_history_13.sql",
        "partner_poli_org_history_14.sql", "partner_poli_org_history_15.sql", "partner_poli_org_history_16.sql",
        "partner_poli_org_history_16.sql", "partner_poli_org_history_17.sql", "partner_poli_org_history_18.sql",
        "partner_poli_org_history_19.sql", "partner_poli_org_history_20.sql", "partner_poli_org_history_21.sql",
        "partner_poli_org_history_22.sql", "partner_poli_org_history_23.sql", "partner_poli_org_history_24.sql",
        "partner_poli_org_history_25.sql", "partner_poli_org_history_26.sql", "partner_poli_org_history_27.sql",
        "partner_poli_org_history_28.sql", "partner_poli_org_history_29.sql", "partner_poli_org_history_30.sql",
        "partner_poli_org_history_31.sql", "partner_poli_org_history_32.sql", "partner_poli_org_history_33.sql",
        "partner_poli_org_history_34.sql", "partner_poli_org_history_35.sql", "partner_poli_org_history_36.sql",
        "partner_poli_org_history_37.sql", "partner_poli_org_history_38.sql", "partner_poli_org_history_39.sql",
        "partner_poli_org_history_40.sql", "partner_poli_org_history_41.sql", "partner_poli_org_history_42.sql",
        "partner_poli_org_history_43.sql", "partner_poli_org_history_44.sql", "partner_poli_org_history_45.sql",
        "partner_poli_org_history_46.sql", "partner_poli_org_history_47.sql", "partner_poli_org_history_99.sql" })
class ForceDumpHistorySabunControllerTest {
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
    void test() throws Exception {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();

        // 配下を全削除
        Path pathRootCorp = Paths.get(pathSaved, frontDumpFolder, "/dump_hisotry_sabun_corp");
        if (Files.exists(pathRootCorp)) {
            Files.walk(pathRootCorp).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(java.io.File::delete);
        }
        Path pathRootPerson = Paths.get(pathSaved, frontDumpFolder, "/dump_hisotry_sabun_person");
        if (Files.exists(pathRootPerson)) {
            Files.walk(pathRootPerson).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .forEach(java.io.File::delete);
        }
        Path pathRootPoliOrg = Paths.get(pathSaved, frontDumpFolder, "/dump_hisotry_sabun_poli_org");
        if (Files.exists(pathRootPoliOrg)) {
            Files.walk(pathRootPoliOrg).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .forEach(java.io.File::delete);
        }

        Path pathRootCorpZip = Paths.get(pathSaved, frontDumpFolder, "dump_hisotry_sabun_corp.zip");
        Files.deleteIfExists(pathRootCorpZip);
        Path pathRootPersonZip = Paths.get(pathSaved, frontDumpFolder, "dump_hisotry_sabun_person.zip");
        Files.deleteIfExists(pathRootPersonZip);
        Path pathRootPoliOrgZip = Paths.get(pathSaved, frontDumpFolder, "dump_hisotry_sabun_poli_org.zip");
        Files.deleteIfExists(pathRootPoliOrgZip);

        // ファイル否存在確認
        assertFalse(Files.exists(pathRootCorp));
        assertFalse(Files.exists(pathRootPerson));
        assertFalse(Files.exists(pathRootPoliOrg));
        assertFalse(Files.exists(pathRootCorpZip));
        assertFalse(Files.exists(pathRootPersonZip));
        assertFalse(Files.exists(pathRootPoliOrgZip));

        ForceDumpCapsuleDto capsuleDto00 = new ForceDumpCapsuleDto();
        capsuleDto00.setDateStart(LocalDate.of(2024, 1, 1));
        capsuleDto00.setDateEnd(LocalDate.of(2024, 12, 31));
        capsuleDto00.setIsExecuteCorp(false);
        capsuleDto00.setIsExecutePerson(false);
        capsuleDto00.setIsExecutePoliOrg(false);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/dump-history-sabun/execute";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.NO_CONTENT.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto00)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isNoContent()).andReturn().getResponse().getStatus());

        /* 処理実施 */
        ForceDumpCapsuleDto capsuleDto01 = new ForceDumpCapsuleDto();
        capsuleDto01.setDateStart(LocalDate.of(2024, 1, 1));
        capsuleDto01.setDateEnd(LocalDate.of(2024, 12, 31));
        capsuleDto01.setIsExecuteCorp(true);
        capsuleDto01.setIsExecutePerson(true);
        capsuleDto01.setIsExecutePoliOrg(true);

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto01)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());

        // ファイルの存在を目視で確認(この段階では作業は終了していない)
    }

}
