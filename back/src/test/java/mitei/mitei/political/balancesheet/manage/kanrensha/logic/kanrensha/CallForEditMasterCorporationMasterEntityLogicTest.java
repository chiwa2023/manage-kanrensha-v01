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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;

/**
 * CallForEditMasterCorporationMasterEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterCorporationMasterEntityLogicTest.sql")
class CallForEditMasterCorporationMasterEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterCorporationMasterEntityLogic callForEditMasterCorporationMasterEntityLogic;

    private KanrenshaCorpDto createDto(final Integer masterId, final String partnerName, final String allAddress) {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setMasterId(masterId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(partnerName);
        dto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll(allAddress);
        dto.setInputAddressDto(addressDto);

        return dto;
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaCorpDto dto = createDto(2001, "テスト法人1", "東京都千代田区");

        MasterCorporationEntity result = callForEditMasterCorporationMasterEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 2002;
        // DB(all_address='大阪府大阪市') と異なる値(all_address='京都府京都市')をセット
        KanrenshaCorpDto dto = createDto(targetId, "テスト法人2", "京都府京都市");

        MasterCorporationEntity result = callForEditMasterCorporationMasterEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterCorporationId(), "IDが一致すること");
        assertEquals("大阪府大阪市", result.getAllAddress(), "DBの値が変更されていないこと");
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setMasterId(9999); // 存在しないID
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterCorporationMasterEntityLogic.practice(dto);
        });
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setMasterId(2003); // is_latest = false のレコード
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setInputAddressDto(new InputAddressDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterCorporationMasterEntityLogic.practice(dto);
        });
    }
}
