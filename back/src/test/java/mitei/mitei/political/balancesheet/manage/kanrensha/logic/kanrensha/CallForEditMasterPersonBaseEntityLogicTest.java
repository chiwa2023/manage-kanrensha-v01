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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * CallForEditMasterPersonBaseEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("CallForEditMasterPersonBaseEntityLogicTest.sql")
class CallForEditMasterPersonBaseEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallForEditMasterPersonBaseEntityLogic callForEditMasterPersonBaseEntityLogic;

    private KanrenshaPersonDto createDto(final Integer baseId, final String partnerName, final String lastName,
            final String firstName, final String lastNameKana, final String firstNameKana, final String gyoushu,
            final String yakushoku, final String shokugyouUserWrite, final String corpNo, final String corpAddress,
            final String corpName) {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setBaseId(baseId);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName(partnerName);
        nameDto.setLastName(lastName);
        nameDto.setFirstName(firstName);
        nameDto.setMiddleName("");
        nameDto.setLastNameKana(lastNameKana);
        nameDto.setFirstNameKana(firstNameKana);
        nameDto.setMiddleNameKana("");
        dto.setInputPersonNameDto(nameDto);

        InputShokugyouDto shokugyouDto = new InputShokugyouDto();
        shokugyouDto.setGyoushu(gyoushu);
        shokugyouDto.setYakushoku(yakushoku);
        shokugyouDto.setShokugyouUserWrite(shokugyouUserWrite);
        shokugyouDto.setCorpNo(corpNo);
        shokugyouDto.setCorpAddress(corpAddress);
        shokugyouDto.setCorpName(corpName);
        // shokugyouDto.setIsShokyouEdit(isShokyouEdit);
        // shokugyouDto.setIsShokyouAccept(isShokyouAccept);
        dto.setInputShokugyouDto(shokugyouDto);

        return dto;
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testReturnsNullWhenNoChanges() {
        KanrenshaPersonDto dto = createDto(301, "ベース 一郎", "ベース", "一郎", // NOPMD
                "ベース", "イチロウ", "IT", "エンジニア", "自営業",
                "1234567890123", "東京都千代田区", "ベース株式会社");

        MasterPersonBaseEntity result = callForEditMasterPersonBaseEntityLogic.practice(dto);

        assertNull(result, "変更がない場合はnullが返却されること");
    }

    @Test
    @Tag("TableTruncate")
    void testReturnsEntityWhenChangesExist() {
        final Integer targetId = 302;
        // DB(yakushoku='工場長') と異なる値(yakushoku='部長')をセット
        KanrenshaPersonDto dto = createDto(targetId, "ベース 二郎", "ベース", "二郎", "ベース", "ジロウ", "製造業", "部長", "", "", "", "");

        MasterPersonBaseEntity result = callForEditMasterPersonBaseEntityLogic.practice(dto);

        assertNotNull(result, "変更がある場合はEntityが返却されること");
        assertEquals(targetId, result.getMasterPersonBaseId(), "IDが一致すること");
        assertEquals("工場長", result.getYakushoku(), "DBの値が変更されていないこと");
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotFound() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setBaseId(999); // 存在しないID
        dto.setInputPersonNameDto(new InputPersonNameDto());
        dto.setInputShokugyouDto(new InputShokugyouDto());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            callForEditMasterPersonBaseEntityLogic.practice(dto);
        });
    }

    @Test
    @Tag("TableTruncate")
    void testThrowsExceptionWhenNotLatest() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        dto.setBaseId(303); // is_latest = false のレコード
        dto.setInputPersonNameDto(new InputPersonNameDto());
        dto.setInputShokugyouDto(new InputShokugyouDto());

        assertThrows(ConcurrencyFailureException.class, () -> {
            callForEditMasterPersonBaseEntityLogic.practice(dto);
        });
    }
}
