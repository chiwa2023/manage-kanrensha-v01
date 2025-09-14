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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;

/**
 * CallForEditMasterCorporationAddressEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterCorporationAddressEntityLogicTest.sql")
class CallForEditMasterCorporationAddressEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterCorporationAddressEntityLogic callForEditMasterCorporationAddressEntityLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setAddressId(2201);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト法人1");
        dto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressPostal("100-0001");
        addressDto.setAddressBlock("千代田区千代田１−１");
        addressDto.setAddressBuilding("テストビル1");
        addressDto.setPostalcode1("100");
        addressDto.setPostalcode2("0001");
        addressDto.setLgCode("131016");
        addressDto.setMachiazaId("0001000");
        addressDto.setBlkId("001");
        addressDto.setRsdtId("001");
        addressDto.setIsPostalEdit(false);
        addressDto.setIsBlockEdit(false);
        addressDto.setIsBuildingEdit(false);
        dto.setInputAddressDto(addressDto);

        MasterCorporationAddressEntity result = callForEditMasterCorporationAddressEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 2202;
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setAddressId(targetId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト法人2(更新)"); // Mismatch
        dto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressPostal("163-8001");
        addressDto.setAddressBlock("新宿区西新宿２−８−１");
        addressDto.setAddressBuilding("東京都庁");
        addressDto.setPostalcode1("163");
        addressDto.setPostalcode2("8001");
        addressDto.setLgCode("131041");
        addressDto.setMachiazaId("0002000");
        addressDto.setBlkId("008");
        addressDto.setRsdtId("001");
        addressDto.setIsPostalEdit(true);
        addressDto.setIsBlockEdit(true);
        addressDto.setIsBuildingEdit(false);
        dto.setInputAddressDto(addressDto);

        MasterCorporationAddressEntity result = callForEditMasterCorporationAddressEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterCorporationAddressId(), "IDが一致すること");
        assertEquals("テスト法人2", result.getPartnerName(), "DBの値が変更されていないこと");
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setAddressId(9999); // 存在しないID
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterCorporationAddressEntityLogic.practice(dto);
        });
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setAddressId(2203); // is_latest = false のレコード
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterCorporationAddressEntityLogic.practice(dto);
        });
    }
}
