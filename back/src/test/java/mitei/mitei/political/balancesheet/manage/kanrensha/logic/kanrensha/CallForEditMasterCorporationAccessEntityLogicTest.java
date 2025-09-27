package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;

/**
 * CallForEditMasterCorporationAccessEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterCorporationAccessEntityLogicTest.sql")
class CallForEditMasterCorporationAccessEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterCorporationAccessEntityLogic callForEditMasterCorporationAccessEntityLogic;

    @Test
    @Tag("TableTruncate")
    void testEmpty() throws Exception {
        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setAccessId(9999);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditMasterCorporationAccessEntityLogic.practice(kanrenshaCorpDto));
    }

    @Test
    @Tag("TableTruncate")
    void testNull() throws Exception {
        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setAccessId(2101);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト法人1");
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("012");
        inputAccessDto.setPhon2("345");
        inputAccessDto.setPhon3("6789");
        inputAccessDto.setEmail("corp1@example.com");
        inputAccessDto.setMyPortalUrl("https://example.com/corp1");
        inputAccessDto.setSnsServiceName("テストSNS");
        inputAccessDto.setSnsPortalUrl("https://sns.example.com");
        inputAccessDto.setSnsAccount("@corp1");
        kanrenshaCorpDto.setInputAccessDto(inputAccessDto);

        assertNull(callForEditMasterCorporationAccessEntityLogic.practice(kanrenshaCorpDto));
    }

    @Test
    @Tag("TableTruncate")
    void testEditSrc() throws Exception {
        final Integer callId = 2102;
        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setAccessId(callId);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト法人2");
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("098"); // 画面から修正
        inputAccessDto.setPhon2("765");
        inputAccessDto.setPhon3("4321");
        inputAccessDto.setEmail("corp2@example.com");
        inputAccessDto.setMyPortalUrl("https://example.com/corp2");
        inputAccessDto.setSnsServiceName("テストSNS");
        inputAccessDto.setSnsPortalUrl("https://sns.example.com");
        inputAccessDto.setSnsAccount("@corp2");
        kanrenshaCorpDto.setInputAccessDto(inputAccessDto);

        MasterCorporationAccessEntity accessEntity = callForEditMasterCorporationAccessEntityLogic.practice(kanrenshaCorpDto);
        assertEquals(callId, accessEntity.getMasterCorporationAccessId());
        assertNotEquals(inputAccessDto.getPhon1(), accessEntity.getPhon1());
    }

}
