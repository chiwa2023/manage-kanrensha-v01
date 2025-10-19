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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;

/**
 * EditMasterCorporationPropertyLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterCorporationPropertyLogicTest.sql")
class EditMasterCorporationPropertyLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterCorporationPropertyLogic logic;

    /** 関連者企業団体属性Repository */
    @Autowired
    private MasterCorporationPropertyRepository repository;

    private SaveKanrenshaCorpCapsuleDto createCapsuleDto(final Integer propertyId, final String houjinSbts, final String kanrenshaCode) {
        KanrenshaCorpDto corpDto = new KanrenshaCorpDto();
        corpDto.setPropertyId(propertyId);
        corpDto.setCorpKanrenshaCode(kanrenshaCode);
        corpDto.setHoujinSbts(houjinSbts); // この項目で差分をテスト

        corpDto.setInputOrgNameDto(new InputOrgNameDto());

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(96);
        userDto.setUserPersonName("test-updater-corp-property");

        SaveKanrenshaCorpCapsuleDto capsuleDto = new SaveKanrenshaCorpCapsuleDto();
        capsuleDto.setKanrenshaCorpDto(corpDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (houjinSbtsが異なる)
        final int originalId = 2901;
        final String kanrenshaCode = "C2901";
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(originalId, "201", kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterCorporationPropertyEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(96, oldEntity.getDeleteUserId());

        MasterCorporationPropertyEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("201", newEntity.getHoujinSbts(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getCorpKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        final String kanrenshaCode = "C2901";
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(2901, "101", kanrenshaCode);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterCorporationPropertyEntity> entities = repository
                .findByCorpKanrenshaCodeOrderByMasterCorporationPropertyIdDesc(kanrenshaCode);
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
