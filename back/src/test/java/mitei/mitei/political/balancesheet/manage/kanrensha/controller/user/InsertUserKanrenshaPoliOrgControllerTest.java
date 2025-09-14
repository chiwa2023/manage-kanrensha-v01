package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import jakarta.transaction.Transactional;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.PoliOrgDantaiKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * InsertUserKanrenshaPoliOrgController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
class InsertUserKanrenshaPoliOrgControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../service/kanrensha/delete_master_political_organizatin.sql",
            "../../service/kanrensha/delete_master_political_organization_address.sql",
            "../../service/kanrensha/delete_master_political_organization_access.sql",
            "../../service/kanrensha/delete_master_political_organization_base.sql",
            "../../service/kanrensha/delete_master_political_organization_property.sql",
            "../../service/kanrensha/delete_history_poli_org.sql" })
    void test() throws Exception {

        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = new SaveKanrenshaPoliOrgCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        // DTOの準備
        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setDantaiKbn(PoliOrgDantaiKbnConstants.DANTAI_KBN_04);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostalcode1("880");
        inputAddressDto.setPostalcode2("8501");
        inputAddressDto.setAddressAll("宮崎県架空市橘通東２丁目１０−１");
        inputAddressDto.setAddressPostal("宮崎県架空市橘通東");
        inputAddressDto.setAddressBlock("２丁目１０−１");
        inputAddressDto.setAddressBuilding("宮崎県庁");
        inputAddressDto.setLgCode("4507011");
        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("131016");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        kanrenshaPoliOrgDto.setInputAddressDto(inputAddressDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");
        kanrenshaPoliOrgDto.setInputAccessDto(inputAccessDto);

        InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        orgDelegateLeastDto.setPersonKanrenshaCode("P00001");
        orgDelegateLeastDto.setPersonName("代表者　太郎");
        kanrenshaPoliOrgDto.setOrgDelegateLeastDto(orgDelegateLeastDto);

        InputKanrenshaPersonLeastDto accounrMgrLeastDto = new InputKanrenshaPersonLeastDto();
        accounrMgrLeastDto.setPersonKanrenshaCode("P00002");
        accounrMgrLeastDto.setPersonName("会計責任者　花子");
        kanrenshaPoliOrgDto.setAccounrMgrLeastDto(accounrMgrLeastDto);

        capsuleDto.setKanrenshaPoliOrgDto(kanrenshaPoliOrgDto);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/add-user/partner-poli-org";

        // サーバステータスがOK(200)・
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }
}
