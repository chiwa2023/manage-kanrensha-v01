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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;

/**
 * CallForEditMasterCorporationBaseEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterCorporationBaseEntityLogicTest.sql")
class CallForEditMasterCorporationBaseEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterCorporationBaseEntityLogic callForEditMasterCorporationBaseEntityLogic;

    private KanrenshaCorpDto createDto(final Integer baseId, final String orgName, final String orgNameKana,
            final String orgDelegateCode, final boolean isShiten) {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setBaseId(baseId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(orgName);
        nameDto.setOrgNameKana(orgNameKana);
        dto.setInputOrgNameDto(nameDto);

        InputKanrenshaPersonLeastDto delegateDto = new InputKanrenshaPersonLeastDto();
        delegateDto.setPersonKanrenshaCode(orgDelegateCode);
        dto.setOrgDelegateLeastDto(delegateDto);

        dto.setIsShiten(isShiten);

        return dto;
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaCorpDto dto = createDto(2301, "テスト法人1", "テストホウジンイチ", "P000000001", false);

        MasterCorporationBaseEntity result = callForEditMasterCorporationBaseEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 2302;
        // DB(is_shiten=false) と異なる値(is_shiten=true)をセット
        KanrenshaCorpDto dto = createDto(targetId, "テスト法人2", "テストホウジンニ", "P000000003", true);

        MasterCorporationBaseEntity result = callForEditMasterCorporationBaseEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterCorporationBaseId(), "IDが一致すること");
        assertEquals(false, result.getIsShiten(), "DBの値が変更されていないこと");
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setBaseId(9999); // 存在しないID
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setOrgDelegateLeastDto(new InputKanrenshaPersonLeastDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterCorporationBaseEntityLogic.practice(dto);
        });
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        dto.setBaseId(2303); // is_latest = false のレコード
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setOrgDelegateLeastDto(new InputKanrenshaPersonLeastDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterCorporationBaseEntityLogic.practice(dto);
        });
    }
}
