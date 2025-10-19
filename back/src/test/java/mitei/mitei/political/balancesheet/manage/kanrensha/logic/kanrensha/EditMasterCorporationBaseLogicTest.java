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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;

/**
 * EditMasterCorporationBaseLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterCorporationBaseLogicTest.sql")
class EditMasterCorporationBaseLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterCorporationBaseLogic logic;

    /** 関連者企業団体基本Repository */
    @Autowired
    private MasterCorporationBaseRepository repository;

    private SaveKanrenshaCorpCapsuleDto createCapsuleDto(final Integer baseId, final String kanrenshaCode) {
        KanrenshaCorpDto corpDto = new KanrenshaCorpDto();
        corpDto.setBaseId(baseId);
        corpDto.setCorpKanrenshaCode(kanrenshaCode);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        // nameDto.setOrgShortName(orgShortName); // この項目で差分をテスト
        corpDto.setInputOrgNameDto(nameDto);

        corpDto.setOrgDelegateLeastDto(new InputKanrenshaPersonLeastDto());

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(97);
        userDto.setUserPersonName("test-updater-corp-base");

        SaveKanrenshaCorpCapsuleDto capsuleDto = new SaveKanrenshaCorpCapsuleDto();
        capsuleDto.setKanrenshaCorpDto(corpDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (orgShortNameが異なる)
        final int originalId = 2801;
        final String kanrenshaCode = "C2801";
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(originalId, kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterCorporationBaseEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(97, oldEntity.getDeleteUserId());

        MasterCorporationBaseEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        // assertEquals("更新後略称", newEntity.getOrgShortName(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getCorpKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        final String kanrenshaCode = "C2801";
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(2801, kanrenshaCode);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterCorporationBaseEntity> entities = repository
                .findByCorpKanrenshaCodeOrderByMasterCorporationBaseIdDesc(kanrenshaCode);
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
