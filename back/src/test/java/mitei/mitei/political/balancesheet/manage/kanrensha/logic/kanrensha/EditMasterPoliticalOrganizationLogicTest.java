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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;

/**
 * EditMasterPoliticalOrganizationLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPoliticalOrganizationLogicTest.sql")
class EditMasterPoliticalOrganizationLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPoliticalOrganizationLogic logic;

    /** 関連者政治団体マスタrepository */
    @Autowired
    private MasterPoliticalOrganizationRepository repository;

    private SaveKanrenshaPoliOrgCapsuleDto createCapsuleDto(final Integer masterId, final String partnerName,
            final String address) {
        KanrenshaPoliOrgDto poliOrgDto = new KanrenshaPoliOrgDto();
        poliOrgDto.setMasterId(masterId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(partnerName);
        poliOrgDto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll(address);
        poliOrgDto.setInputAddressDto(addressDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(99);
        userDto.setUserPersonCode(999);
        userDto.setUserPersonName("test-updater");

        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = new SaveKanrenshaPoliOrgCapsuleDto();
        capsuleDto.setKanrenshaPoliOrgDto(poliOrgDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成
        final int originalId = 1001;
        final String kanrenshaCode = "PO1001";
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(originalId, "更新後 政治団体", "更新後住所");
        capsuleDto.getKanrenshaPoliOrgDto().setPoliOrgKanrenshaCode(kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");
        assertNotEquals(originalId, newId, "元のIDとは異なるIDが返却されること");

        // 元のレコードが更新されていることを確認
        MasterPoliticalOrganizationEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(99, oldEntity.getDeleteUserId(), "無効化したユーザーIDが設定されていること");

        // 新しいレコードが作成されていることを確認
        MasterPoliticalOrganizationEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("更新後 政治団体", newEntity.getPartnerName(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPoliOrgKanrenshaCode(), "関連者コードが引き継がれていること");
        assertEquals(99, newEntity.getInsertUserId(), "挿入したユーザーIDが設定されていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(1001, "更新前 政治団体", "更新前住所");

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        // レコードが追加されていないことを確認
        List<MasterPoliticalOrganizationEntity> entities = repository.findAll();
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
