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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;

/**
 * CallForEditMasterPoliOrgPropertyEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPoliOrgPropertyEntityLogicTest.sql")
class CallForEditMasterPoliOrgPropertyEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPoliOrgPropertyEntityLogic callForEditMasterPoliOrgPropertyEntityLogic;

    /**
     * DTOとDBの値が一致する場合、nullが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setPropertyId(901);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト政治団体1");
        dto.setInputOrgNameDto(nameDto);

        InputKanrenshaPersonLeastDto accountMgrDto = new InputKanrenshaPersonLeastDto();
        accountMgrDto.setPersonKanrenshaCode("P000000001");
        accountMgrDto.setPersonName("会計管理者1");
        dto.setAccounrMgrLeastDto(accountMgrDto);

        MasterPoliticalOrganizationPropertyEntity result = callForEditMasterPoliOrgPropertyEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    /**
     * DTOとDBの値が一致しない場合、DBのEntityが返却されることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 902;
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setPropertyId(targetId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト政治団体2");
        dto.setInputOrgNameDto(nameDto);

        InputKanrenshaPersonLeastDto accountMgrDto = new InputKanrenshaPersonLeastDto();
        accountMgrDto.setPersonKanrenshaCode("P000000004"); // Mismatch
        accountMgrDto.setPersonName("会計管理者2");
        dto.setAccounrMgrLeastDto(accountMgrDto);

        MasterPoliticalOrganizationPropertyEntity result = callForEditMasterPoliOrgPropertyEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterPoliticalOrganizationPropertyId(), "IDが一致すること");
        assertEquals("P000000003", result.getAccountMgrCode(), "DBの値が変更されていないこと");
    }

    /**
     * 対象のIDが存在しない場合、EmptyResultDataAccessExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setPropertyId(999); // 存在しないID
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setAccounrMgrLeastDto(new InputKanrenshaPersonLeastDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterPoliOrgPropertyEntityLogic.practice(dto);
        }, "対象が存在しない場合はEmptyResultDataAccessExceptionがスローされること");
    }

    /**
     * 対象が最新でない場合、ConcurrencyFailureExceptionがスローされることを確認するテスト
     */
    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setPropertyId(903); // is_latest = false のレコード
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setAccounrMgrLeastDto(new InputKanrenshaPersonLeastDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterPoliOrgPropertyEntityLogic.practice(dto);
        }, "対象が最新でない場合はConcurrencyFailureExceptionがスローされること");

    }
}
