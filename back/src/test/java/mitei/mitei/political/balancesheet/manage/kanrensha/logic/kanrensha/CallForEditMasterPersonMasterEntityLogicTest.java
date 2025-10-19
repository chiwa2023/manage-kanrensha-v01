package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * CallForEditMasterPersonMasterEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPersonMasterEntityLogicTest.sql")
class CallForEditMasterPersonMasterEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPersonMasterEntityLogic callForEditMasterPersonMasterEntityLogic;

    private KanrenshaPersonDto createDto(final Integer masterId, final String partnerName, final String allAddress,
            final String allShokugyou) {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setMasterId(masterId);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName(partnerName);
        dto.setInputPersonNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll(allAddress);
        dto.setInputAddressDto(addressDto);

        InputShokugyouDto shokugyouDto = new InputShokugyouDto();
        shokugyouDto.setAllShokugyou(allShokugyou);
        dto.setInputShokugyouDto(shokugyouDto);

        return dto;
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaPersonDto dto = createDto(401, "マスター 一郎", "東京都千代田区", "議員");

        MasterPersonEntity result = callForEditMasterPersonMasterEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 402;
        // DB(person_shokugyou='会社役員') と異なる値(person_shokugyou='自営業')をセット
        KanrenshaPersonDto dto = createDto(targetId, "マスター 二郎", "大阪府大阪市", "自営業");

        MasterPersonEntity result = callForEditMasterPersonMasterEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterPersonId(), "IDが一致すること");
        assertEquals("会社役員", result.getPersonShokugyou(), "DBの値が変更されていないこと");
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setMasterId(999); // 存在しないID
        dto.setInputPersonNameDto(new InputPersonNameDto());
        dto.setInputAddressDto(new InputAddressDto());
        dto.setInputShokugyouDto(new InputShokugyouDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterPersonMasterEntityLogic.practice(dto);
        });
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setMasterId(403); // is_latest = false のレコード
        dto.setInputPersonNameDto(new InputPersonNameDto());
        dto.setInputAddressDto(new InputAddressDto());
        dto.setInputShokugyouDto(new InputShokugyouDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterPersonMasterEntityLogic.practice(dto);
        });
    }
}
