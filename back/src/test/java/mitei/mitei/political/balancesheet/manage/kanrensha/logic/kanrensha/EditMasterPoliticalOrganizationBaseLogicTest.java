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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;

/**
 * EditMasterPoliticalOrganizationBaseLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPoliticalOrganizationBaseLogicTest.sql")
class EditMasterPoliticalOrganizationBaseLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPoliticalOrganizationBaseLogic logic;

    /** 関連者政治団体基本Repository */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository repository;

    private SaveKanrenshaPoliOrgCapsuleDto createCapsuleDto(final Integer baseId, final String kanrenshaCode) {
        KanrenshaPoliOrgDto poliOrgDto = new KanrenshaPoliOrgDto();
        poliOrgDto.setBaseId(baseId);
        poliOrgDto.setPoliOrgKanrenshaCode(kanrenshaCode);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("基本更新前 政治団体");
        nameDto.setOrgNameKana("キホンコウシンマエセイジダンタイ");
        // nameDto.setOrgShortName(orgShortName); // この項目で差分をテスト
        poliOrgDto.setInputOrgNameDto(nameDto);

        InputKanrenshaPersonLeastDto delegateDto = new InputKanrenshaPersonLeastDto();
        delegateDto.setPersonKanrenshaCode("P000000001");
        poliOrgDto.setOrgDelegateLeastDto(delegateDto);

        InputKanrenshaPersonLeastDto keirishiDto = new InputKanrenshaPersonLeastDto();
        keirishiDto.setPersonKanrenshaCode("P000000002");
        keirishiDto.setPersonName("経理担当");
        poliOrgDto.setAccounrMgrLeastDto(keirishiDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(96);
        userDto.setUserPersonName("test-updater-poli-org-base");

        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = new SaveKanrenshaPoliOrgCapsuleDto();
        capsuleDto.setKanrenshaPoliOrgDto(poliOrgDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (orgShortNameが異なる)
        final int originalId = 1301;
        final String kanrenshaCode = "PO1301";
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(originalId, kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterPoliticalOrganizationBaseEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(96, oldEntity.getDeleteUserId());

        MasterPoliticalOrganizationBaseEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        // assertEquals("更新後略称", newEntity.getOrgShortName(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPoliOrgKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        final String kanrenshaCode = "PO1301";
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(1301, kanrenshaCode);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterPoliticalOrganizationBaseEntity> entities = repository
                .findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationBaseIdDesc(kanrenshaCode);
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
