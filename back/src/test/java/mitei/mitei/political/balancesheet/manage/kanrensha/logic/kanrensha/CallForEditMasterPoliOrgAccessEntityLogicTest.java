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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;

/**
 * CallForEditMasterPoliOrgAccessEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPoliOrgAccessEntityLogicTest.sql")
class CallForEditMasterPoliOrgAccessEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPoliOrgAccessEntityLogic callForEditMasterPoliOrgAccessEntityLogic;

    @Test
    @Tag("TableTruncate")
    void testEmpty() throws Exception {

        // テーブルIdで編集対象が呼び出せなかったらSE呼び出し案件
        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setAccessId(9999);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditMasterPoliOrgAccessEntityLogic.practice(kanrenshaPoliOrgDto));
    }

    @Test
    @Tag("TableTruncate")
    void testNull() throws Exception {

        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setAccessId(601);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト政治団体1");
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("012");
        inputAccessDto.setPhon2("345");
        inputAccessDto.setPhon3("6789");
        inputAccessDto.setEmail("org1@example.com");
        inputAccessDto.setMyPortalUrl("https://example.com/org1");
        inputAccessDto.setSnsServiceName("テストSNS");
        inputAccessDto.setSnsPortalUrl("https://sns.example.com");
        inputAccessDto.setSnsAccount("@org1");
        kanrenshaPoliOrgDto.setInputAccessDto(inputAccessDto);

        // 修正箇所がない場合はそのまま
        assertNull(callForEditMasterPoliOrgAccessEntityLogic.practice(kanrenshaPoliOrgDto));
    }

    @Test
    @Tag("TableTruncate")
    void testEditSrc() throws Exception {

        final Integer callId = 602;
        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setAccessId(callId);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト政治団体2");
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("098"); // 画面から修正
        inputAccessDto.setPhon2("765");
        inputAccessDto.setPhon3("4321");
        inputAccessDto.setEmail("org2@example.com");
        inputAccessDto.setMyPortalUrl("https://example.com/org2");
        inputAccessDto.setSnsServiceName("テストSNS");
        inputAccessDto.setSnsPortalUrl("https://sns.example.com");
        inputAccessDto.setSnsAccount("@org2");
        kanrenshaPoliOrgDto.setInputAccessDto(inputAccessDto);

        // 画面から修正された場合元データを呼び出す
        MasterPoliticalOrganizationAccessEntity accessEntity = callForEditMasterPoliOrgAccessEntityLogic.practice(kanrenshaPoliOrgDto);
        assertEquals(callId, accessEntity.getMasterPoliticalOrganizationAccessId()); // 呼び出したIdがそのまま
        assertNotEquals(inputAccessDto.getPhon1(), accessEntity.getPhon1()); // 編集内容が上書きされていない
    }

}
