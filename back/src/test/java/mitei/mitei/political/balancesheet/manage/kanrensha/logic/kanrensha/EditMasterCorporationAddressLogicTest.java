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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;

/**
 * EditMasterCorporationAddressLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterCorporationAddressLogicTest.sql")
class EditMasterCorporationAddressLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterCorporationAddressLogic logic;

    /** 関連者企業団体住所Repository */
    @Autowired
    private MasterCorporationAddressRepository repository;

    private SaveKanrenshaCorpCapsuleDto createCapsuleDto(final Integer addressId, final String partnerName,
            final String building, final String kanrenshaCode) {
        KanrenshaCorpDto corpDto = new KanrenshaCorpDto();
        corpDto.setAddressId(addressId);
        corpDto.setCorpKanrenshaCode(kanrenshaCode);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(partnerName);
        corpDto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressBuilding(building); // この項目で差分をテスト
        corpDto.setInputAddressDto(addressDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(98);
        userDto.setUserPersonName("test-updater-corp-address");

        SaveKanrenshaCorpCapsuleDto capsuleDto = new SaveKanrenshaCorpCapsuleDto();
        capsuleDto.setKanrenshaCorpDto(corpDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (buildingが異なる)
        final int originalId = 2701;
        final String kanrenshaCode = "C2701";
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(originalId, "住所更新前 法人", "新しいビル", kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterCorporationAddressEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(98, oldEntity.getDeleteUserId());

        MasterCorporationAddressEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("新しいビル", newEntity.getAddressBuilding(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getCorpKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        final String kanrenshaCode = "C2701";
        SaveKanrenshaCorpCapsuleDto capsuleDto = createCapsuleDto(2701, "住所更新前 法人", "テストビル", kanrenshaCode);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterCorporationAddressEntity> entities = repository
                .findByCorpKanrenshaCodeOrderByMasterCorporationAddressIdDesc(kanrenshaCode);
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
