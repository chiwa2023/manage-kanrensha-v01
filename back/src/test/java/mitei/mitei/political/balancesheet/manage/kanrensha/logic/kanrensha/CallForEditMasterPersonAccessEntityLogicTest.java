package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;

/**
 * CallForEditMasterPersonAccessEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_master_person_access.sql")
class CallForEditMasterPersonAccessEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPersonAccessEntityLogic callForEditMasterPersonAccessEntityLogic;

    @Test
    @Tag("TableTruncate")
    void testEmpty() throws Exception {

        // テーブルIdで編集対象が呼び出せなかったらSE呼び出し案件
        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setAccessId(12967);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditMasterPersonAccessEntityLogic.practice(kanrenshaPersonDto));
    }

    @Test
    @Tag("TableTruncate")
    void testNull() throws Exception {

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setAccessId(265);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金　太郎");
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("012");
        inputAccessDto.setPhon2("345");
        inputAccessDto.setPhon3("6789");
        inputAccessDto.setEmail("taro@jakushou-sns.net");
        inputAccessDto.setMyPortalUrl("https://myblog.com/userid=11");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho.net/index.html");
        inputAccessDto.setSnsAccount("@taro9999");
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);

        // 修正箇所がない場合はそのまま
        assertNull(callForEditMasterPersonAccessEntityLogic.practice(kanrenshaPersonDto));
    }

    @Test
    @Tag("TableTruncate")
    void testEditSrc() throws Exception {

        final Integer callId = 416;
        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setAccessId(callId);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金　太郎");
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("012");
        inputAccessDto.setPhon2("345");
        inputAccessDto.setPhon3("6789");
        inputAccessDto.setEmail("taro@jakushou-sns.net");
        inputAccessDto.setMyPortalUrl("https://myblog.com/userid=11");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho.net/index.html");
        inputAccessDto.setSnsAccount("@taro2999"); // 画面から修正
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);

        // 画面から修正された場合元データを呼び出す
        MasterPersonAccessEntity accessEntity = callForEditMasterPersonAccessEntityLogic.practice(kanrenshaPersonDto);
        assertEquals(callId, accessEntity.getMasterPersonAccessId()); // 呼び出したIdがそのまま
        assertNotEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount()); // 編集内容が上書きされていない
    }

}
