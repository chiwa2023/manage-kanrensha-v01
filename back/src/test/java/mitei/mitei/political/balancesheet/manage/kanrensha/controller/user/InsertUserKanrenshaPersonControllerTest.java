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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * InsertUserKanrenshaPersonController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
class InsertUserKanrenshaPersonControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../../service/kanrensha/delete_master_person.sql",
            "../../service/kanrensha/delete_master_person_address.sql",
            "../../service/kanrensha/delete_master_person_access.sql",
            "../../service/kanrensha/delete_master_person_address.sql",
            "../../service/kanrensha/delete_master_person_base.sql",
            "../../service/kanrensha/delete_master_person_property.sql",
            "../../service/kanrensha/delete_hsitory_person.sql" })
    void test() throws Exception {

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostal1("100");
        inputAddressDto.setPostal2("0001");
        inputAddressDto.setAddressAll("宮崎県実在市山麓町");
        inputAddressDto.setOrginAddressAll("宮崎県実在市");
        inputAddressDto.setAddressPostal("宮崎県実在市山麓町");
        inputAddressDto.setAddressBlock("1丁目75番地");
        inputAddressDto.setAddressBuilding("四角ビル3F");

        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("131016");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("03");
        inputAccessDto.setPhon2("1234");
        inputAccessDto.setPhon3("5678");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        // 基本
        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");

        InputShokugyouDto inputShokugyouDto = new InputShokugyouDto();
        inputShokugyouDto.setAllShokugyou("水産業団体役員");
        inputShokugyouDto.setGyoushu("水産業");
        inputShokugyouDto.setYakushoku("役職者");
        inputShokugyouDto.setShokugyouUserWrite("水産業団体役員");
        inputShokugyouDto.setCorpNo("9876543210987");
        inputShokugyouDto.setCorpName("大漁水産");
        inputShokugyouDto.setCorpAddress("山形県架空市海辺町");

        // 属性
        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setInputAddressDto(inputAddressDto);
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);
        kanrenshaPersonDto.setInputShokugyouDto(inputShokugyouDto);
        kanrenshaPersonDto.setIsForeign(true);

        capsuleDto.setKanrenshaPersonDto(kanrenshaPersonDto);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/add-user/partner-person";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
