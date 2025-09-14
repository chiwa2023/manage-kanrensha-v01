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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;

/**
 * CallForEditMasterCorporationPropertyEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterCorporationPropertyEntityLogicTest.sql")
class CallForEditMasterCorporationPropertyEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterCorporationPropertyEntityLogic callForEditMasterCorporationPropertyEntityLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setPropertyId(2401);
        dto.setHoujinSbts("101");

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト法人1");
        dto.setInputOrgNameDto(nameDto);

        MasterCorporationPropertyEntity result = callForEditMasterCorporationPropertyEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 2402;
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setPropertyId(targetId);
        dto.setHoujinSbts("401"); // is_foreignがtrueになる法人種別

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト法人2");
        dto.setInputOrgNameDto(nameDto);

        MasterCorporationPropertyEntity result = callForEditMasterCorporationPropertyEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterCorporationPropertyId(), "IDが一致すること");
        assertEquals(false, result.getIsForeign(), "DBの値が変更されていないこと");
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setPropertyId(9999); // 存在しないID
        dto.setInputOrgNameDto(new InputOrgNameDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterCorporationPropertyEntityLogic.practice(dto);
        });
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setPropertyId(2403); // is_latest = false のレコード
        dto.setInputOrgNameDto(new InputOrgNameDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterCorporationPropertyEntityLogic.practice(dto);
        });
    }
}
