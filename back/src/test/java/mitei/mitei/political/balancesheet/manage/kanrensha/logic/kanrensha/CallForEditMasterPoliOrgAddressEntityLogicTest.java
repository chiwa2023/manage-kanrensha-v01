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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;

/**
 * CallForEditMasterPoliOrgAddressEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPoliOrgAddressEntityLogicTest.sql")
class CallForEditMasterPoliOrgAddressEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPoliOrgAddressEntityLogic callForEditMasterPoliOrgAddressEntityLogic;

    /**
     * DTOとDBの値が一致する場合、nullが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setAddressId(701);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト政治団体1");
        dto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressPostal("100-0001");
        addressDto.setAddressBlock("千代田区千代田１−１");
        addressDto.setAddressBuilding("宮殿");
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

        MasterPoliticalOrganizationAddressEntity result = callForEditMasterPoliOrgAddressEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    /**
     * DTOとDBの値が一致しない場合、DBのEntityが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 702;
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setAddressId(targetId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト政治団体2(更新)"); // Mismatch
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

        MasterPoliticalOrganizationAddressEntity result = callForEditMasterPoliOrgAddressEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterPoliticalOrganizationAddressId(), "IDが一致すること");
        assertEquals("テスト政治団体2", result.getPartnerName(), "DBの値が変更されていないこと");
    }

    /**
     * 対象のIDが存在しない場合、EmptyResultDataAccessExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setAddressId(999); // 存在しないID
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterPoliOrgAddressEntityLogic.practice(dto);
        }, "対象が存在しない場合はEmptyResultDataAccessExceptionがスローされること");
    }

    /**
     * 対象が最新でない場合、ConcurrencyFailureExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setAddressId(703); // is_latest = false のレコード
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterPoliOrgAddressEntityLogic.practice(dto);
        }, "対象が最新でない場合はConcurrencyFailureExceptionがスローされること");
    }
}
