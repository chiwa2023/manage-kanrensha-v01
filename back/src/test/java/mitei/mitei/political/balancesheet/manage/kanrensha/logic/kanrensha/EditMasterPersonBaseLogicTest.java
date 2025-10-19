package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;

/**
 * EditMasterPersonBaseLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPersonBaseLogicTest.sql")
class EditMasterPersonBaseLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPersonBaseLogic logic;

    /** 関連者個人基本Repository */
    @Autowired
    private MasterPersonBaseRepository repository;

    private SaveKanrenshaPersonCapsuleDto createCapsuleDto(final Integer baseId, final String yakushoku) {
        KanrenshaPersonDto personDto = new KanrenshaPersonDto();
        personDto.setBaseId(baseId);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName("基本更新前 一郎");
        nameDto.setLastName("基本姓");
        nameDto.setFirstName("基本名");
        nameDto.setMiddleName("");
        nameDto.setLastNameKana("キホンセイ");
        nameDto.setFirstNameKana("キホンメイ");
        nameDto.setMiddleNameKana("");
        personDto.setInputPersonNameDto(nameDto);

        InputShokugyouDto shokugyouDto = new InputShokugyouDto();
        shokugyouDto.setGyoushu("IT");
        shokugyouDto.setYakushoku(yakushoku); // この項目で差分をテスト
        shokugyouDto.setShokugyouUserWrite("自営業");
        shokugyouDto.setCorpNo("1234567890123");
        shokugyouDto.setCorpAddress("東京都千代田区");
        shokugyouDto.setCorpName("ベース株式会社");
        // shokugyouDto.setIsShokyouEdit(false);
        // shokugyouDto.setIsShokyouAccept(true);
        personDto.setInputShokugyouDto(shokugyouDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(97);
        userDto.setUserPersonName("test-updater-base");

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(personDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (yakushokuが異なる)
        final int originalId = 701;
        final String kanrenshaCode = "P0701";
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(originalId, "シニアエンジニア");

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterPersonBaseEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(97, oldEntity.getDeleteUserId());

        MasterPersonBaseEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("シニアエンジニア", newEntity.getYakushoku(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPersonKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(701, "エンジニア");

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterPersonBaseEntity> entities = repository
                .findByPersonKanrenshaCodeOrderByMasterPersonBaseIdDesc("P0701");
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
