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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;

/**
 * EditMasterCorporationLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterCorporationLogicTest.sql")
class EditMasterCorporationLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterCorporationLogic logic;

    /** 関連者企業団体マスタrepository */
    @Autowired
    private MasterCorporationRepository repository;

    private SaveKanrenshaCorpCapsuleDto createCapsuleDto(final Integer masterId, final String partnerName,
            final String address) {
        KanrenshaCorpDto corpDto = new KanrenshaCorpDto();
        corpDto.setMasterId(masterId);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(partnerName);
        corpDto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll(address);
        corpDto.setInputAddressDto(addressDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(99);
        userDto.setUserPersonName("test-updater");

        SaveKanrenshaCorpCapsuleDto capsuleDto = new SaveKanrenshaCorpCapsuleDto();
        capsuleDto.setKanrenshaCorpDto(corpDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成
        final int originalId = 2501;
        final String kanrenshaCode = "C2501";
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(originalId, "更新後 法人", "更新後住所");
        capsuleDto.getKanrenshaCorpDto().setCorpKanrenshaCode(kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterCorporationEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(99, oldEntity.getDeleteUserId());

        MasterCorporationEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("更新後 法人", newEntity.getPartnerName(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getCorpKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(2501, "更新前 法人", "更新前住所");

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterCorporationEntity> entities = repository.findAll();
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
