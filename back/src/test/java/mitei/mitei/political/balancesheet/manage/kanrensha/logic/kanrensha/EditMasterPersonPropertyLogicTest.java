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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;

/**
 * EditMasterPersonPropertyLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPersonPropertyLogicTest.sql")
class EditMasterPersonPropertyLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPersonPropertyLogic logic;

    /** 関連者個人属性Repository */
    @Autowired
    private MasterPersonPropertyRepository repository;

    private SaveKanrenshaPersonCapsuleDto createCapsuleDto(final Integer propertyId, final String partnerName,
            final boolean isForeign) {
        KanrenshaPersonDto personDto = new KanrenshaPersonDto();
        personDto.setPropertyId(propertyId);
        personDto.setIsForeign(isForeign);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName(partnerName);
        personDto.setInputPersonNameDto(nameDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(96);
        userDto.setUserPersonName("test-updater-property");

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(personDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (isForeignが異なる)
        final int originalId = 801;
        final String kanrenshaCode = "P0801";
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(originalId, "属性更新前 一郎", true);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterPersonPropertyEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(96, oldEntity.getDeleteUserId());

        MasterPersonPropertyEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertTrue(newEntity.getIsForeign(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPersonKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(801, "属性更新前 一郎", false);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterPersonPropertyEntity> entities = repository
                .findByPersonKanrenshaCodeOrderByMasterPersonPropertyIdDesc("P0801");
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
