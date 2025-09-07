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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;

/**
 * CallForEditMasterPersonAddressEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPersonAddressEntityLogicTest.sql")
class CallForEditMasterPersonAddressEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPersonAddressEntityLogic callForEditMasterPersonAddressEntityLogic;

    /**
     * DTOとDBの値が一致する場合、nullが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setAddressId(101);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName("テスト 一郎");
        dto.setInputPersonNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressPostal("100-0001");
        addressDto.setAddressBlock("千代田区千代田１−１");
        addressDto.setAddressBuilding("宮殿");
        addressDto.setPostal1("100");
        addressDto.setPostal2("0001");
        addressDto.setLgCode("131016");
        addressDto.setMachiazaId("0001000");
        addressDto.setBlkId("001");
        addressDto.setRsdtId("001");
        //addressDto.setRsdt2Id("002");
        addressDto.setIsPostalEdit(false);
        addressDto.setIsBlockEdit(false);
        addressDto.setIsBuildingEdit(false);
        //addressDto.setIsPostalAccept(true);
        //addressDto.setIsBlockAccept(true);
        //addressDto.setIsBuildingAccept(true);
        dto.setInputAddressDto(addressDto);

        MasterPersonAddressEntity result = callForEditMasterPersonAddressEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    /**
     * DTOとDBの値が一致しない場合、DBのEntityが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 102;
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setAddressId(targetId);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName("テスト 二郎(更新)"); // Mismatch
        dto.setInputPersonNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressPostal("163-8001");
        addressDto.setAddressBlock("新宿区西新宿２−８−１");
        addressDto.setAddressBuilding("東京都庁");
        addressDto.setPostal1("163");
        addressDto.setPostal2("8001");
        addressDto.setLgCode("131041");
        addressDto.setMachiazaId("0002000");
        addressDto.setBlkId("008000");
        addressDto.setRsdtId("001");
        //addressDto.setRsdt2Id("");
        addressDto.setIsPostalEdit(true);
        addressDto.setIsBlockEdit(true);
        addressDto.setIsBuildingEdit(false);
        //addressDto.setIsPostalAccept(false);
        //addressDto.setIsBlockAccept(false);
        //addressDto.setIsBuildingAccept(true);
        dto.setInputAddressDto(addressDto);

        MasterPersonAddressEntity result = callForEditMasterPersonAddressEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterPersonAddressId(), "IDが一致すること");
        assertEquals("テスト 二郎", result.getPartnerName(), "DBの値が変更されていないこと");
    }

    /**
     * 対象のIDが存在しない場合、EmptyResultDataAccessExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setAddressId(999); // 存在しないID
        dto.setInputPersonNameDto(new InputPersonNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterPersonAddressEntityLogic.practice(dto);
        }, "対象が存在しない場合はEmptyResultDataAccessExceptionがスローされること");
    }

    /**
     * 対象が最新でない場合、ConcurrencyFailureExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setAddressId(103); // is_latest = false のレコード
        dto.setInputPersonNameDto(new InputPersonNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterPersonAddressEntityLogic.practice(dto);
        }, "対象が最新でない場合はConcurrencyFailureExceptionがスローされること");
    }
}
