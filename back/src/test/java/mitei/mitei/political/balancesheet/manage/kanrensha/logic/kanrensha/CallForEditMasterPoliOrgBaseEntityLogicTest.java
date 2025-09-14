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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;

/**
 * CallForEditMasterPoliOrgBaseEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPoliOrgBaseEntityLogicTest.sql")
class CallForEditMasterPoliOrgBaseEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPoliOrgBaseEntityLogic callForEditMasterPoliOrgBaseEntityLogic;

    private KanrenshaPoliOrgDto createDto(final Integer baseId, final String orgName, final String orgNameKana,
            final String orgDelegateCode, final String keirishiCode, final String keirishiName) {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setBaseId(baseId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(orgName);
        nameDto.setOrgNameKana(orgNameKana);
        dto.setInputOrgNameDto(nameDto);

        InputKanrenshaPersonLeastDto delegateDto = new InputKanrenshaPersonLeastDto();
        delegateDto.setPersonKanrenshaCode(orgDelegateCode);
        dto.setOrgDelegateLeastDto(delegateDto);

        InputKanrenshaPersonLeastDto keirishiDto = new InputKanrenshaPersonLeastDto();
        keirishiDto.setPersonKanrenshaCode(keirishiCode);
        keirishiDto.setPersonName(keirishiName);
        dto.setAccounrMgrLeastDto(keirishiDto);

        return dto;
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaPoliOrgDto dto = createDto(801, "テスト政治団体1", "テストセイジダンタイ1", "P000000001", "P000000002", "経理担当1");

        MasterPoliticalOrganizationBaseEntity result = callForEditMasterPoliOrgBaseEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 802;
        KanrenshaPoliOrgDto dto = createDto(targetId, "テスト政治団体2(更新)", "テストセイジダンタイ2", "P000000003", "P000000004",
                "経理担当2");

        MasterPoliticalOrganizationBaseEntity result = callForEditMasterPoliOrgBaseEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterPoliticalOrganizationBaseId(), "IDが一致すること");
        assertEquals("テスト政治団体2", result.getPartnerName(), "DBの値が変更されていないこと");
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setBaseId(999); // 存在しないID
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setOrgDelegateLeastDto(new InputKanrenshaPersonLeastDto());
        dto.setAccounrMgrLeastDto(new InputKanrenshaPersonLeastDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterPoliOrgBaseEntityLogic.practice(dto);
        });
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        dto.setBaseId(803); // is_latest = false のレコード
        dto.setInputOrgNameDto(new InputOrgNameDto());
        dto.setOrgDelegateLeastDto(new InputKanrenshaPersonLeastDto());
        dto.setAccounrMgrLeastDto(new InputKanrenshaPersonLeastDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterPoliOrgBaseEntityLogic.practice(dto);
        });
    }
}
