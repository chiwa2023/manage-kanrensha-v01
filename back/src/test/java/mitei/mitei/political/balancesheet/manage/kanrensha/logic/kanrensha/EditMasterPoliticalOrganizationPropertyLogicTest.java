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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;

/**
 * EditMasterPoliticalOrganizationPropertyLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPoliticalOrganizationPropertyLogicTest.sql")
class EditMasterPoliticalOrganizationPropertyLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPoliticalOrganizationPropertyLogic logic;

    /** 関連者政治団体属性Repository */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository repository;

    private SaveKanrenshaPoliOrgCapsuleDto createCapsuleDto(final Integer propertyId, final String partnerName,
            final String accountMgrName, final String kanrenshaCode) {
        KanrenshaPoliOrgDto poliOrgDto = new KanrenshaPoliOrgDto();
        poliOrgDto.setPropertyId(propertyId);
        poliOrgDto.setPoliOrgKanrenshaCode(kanrenshaCode);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(partnerName);
        poliOrgDto.setInputOrgNameDto(nameDto);

        InputKanrenshaPersonLeastDto accountMgrDto = new InputKanrenshaPersonLeastDto();
        accountMgrDto.setPersonKanrenshaCode("P000000001");
        accountMgrDto.setPersonName(accountMgrName); // この項目で差分をテスト
        poliOrgDto.setAccounrMgrLeastDto(accountMgrDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(95);
        userDto.setUserPersonName("test-updater-poli-org-property");

        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = new SaveKanrenshaPoliOrgCapsuleDto();
        capsuleDto.setKanrenshaPoliOrgDto(poliOrgDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (accountMgrNameが異なる)
        final int originalId = 1401;
        final String kanrenshaCode = "PO1401";
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(originalId, "属性更新前 政治団体", "新しい会計管理者", kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterPoliticalOrganizationPropertyEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(95, oldEntity.getDeleteUserId());

        MasterPoliticalOrganizationPropertyEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("新しい会計管理者", newEntity.getAccountMgrName(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPoliOrgKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        final String kanrenshaCode = "PO1401";
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(1401, "属性更新前 政治団体", "会計管理者1", kanrenshaCode);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterPoliticalOrganizationPropertyEntity> entities = repository
                .findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationPropertyIdDesc(kanrenshaCode);
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
