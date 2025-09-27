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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;

/**
 * CallForEditMasterPersonPropertyEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPersonPropertyEntityLogicTest.sql")
class CallForEditMasterPersonPropertyEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPersonPropertyEntityLogic callForEditMasterPersonPropertyEntityLogic;

    /**
     * DTOとDBの値が一致する場合、nullが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setPropertyId(201);
        dto.setIsForeign(false);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName("プロパティ 一郎");
        dto.setInputPersonNameDto(nameDto);

        MasterPersonPropertyEntity result = callForEditMasterPersonPropertyEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    /**
     * DTOとDBの値が一致しない場合、DBのEntityが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 202;
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setPropertyId(targetId);
        dto.setIsForeign(false); // DBはtrueなので不一致

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName("プロパティ 二郎");
        dto.setInputPersonNameDto(nameDto);

        MasterPersonPropertyEntity result = callForEditMasterPersonPropertyEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterPersonPropertyId(), "IDが一致すること");
        assertEquals(true, result.getIsForeign(), "DBの値が変更されていないこと");
    }

    /**
     * 対象のIDが存在しない場合、EmptyResultDataAccessExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setPropertyId(999); // 存在しないID
        dto.setInputPersonNameDto(new InputPersonNameDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterPersonPropertyEntityLogic.practice(dto);
        }, "対象が存在しない場合はEmptyResultDataAccessExceptionがスローされること");
    }

    /**
     * 対象が最新でない場合、ConcurrencyFailureExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setPropertyId(203); // is_latest = false のレコード
        dto.setInputPersonNameDto(new InputPersonNameDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterPersonPropertyEntityLogic.practice(dto);
        }, "対象が最新でない場合はConcurrencyFailureExceptionがスローされること");

    }
}
