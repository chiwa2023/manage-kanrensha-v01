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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;

/**
 * EditMasterPoliticalOrganizationAddressLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPoliticalOrganizationAddressLogicTest.sql")
class EditMasterPoliticalOrganizationAddressLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPoliticalOrganizationAddressLogic logic;

    /** 関連者政治団体住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository repository;

    private SaveKanrenshaPoliOrgCapsuleDto createCapsuleDto(final Integer addressId, final String partnerName,
            final String building, final String kanrenshaCode) {
        KanrenshaPoliOrgDto poliOrgDto = new KanrenshaPoliOrgDto();
        poliOrgDto.setAddressId(addressId);
        poliOrgDto.setPoliOrgKanrenshaCode(kanrenshaCode);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName(partnerName);
        poliOrgDto.setInputOrgNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressPostal("100-0001");
        addressDto.setAddressBlock("千代田区千代田１−１");
        addressDto.setAddressBuilding(building); // この項目で差分をテスト
        addressDto.setPostalcode1("100");
        addressDto.setPostalcode2("0001");
        addressDto.setLgCode("131016");
        addressDto.setMachiazaId("0001000");
        addressDto.setBlkId("001");
        addressDto.setRsdtId("001");
        addressDto.setIsPostalEdit(false);
        addressDto.setIsBlockEdit(false);
        addressDto.setIsBuildingEdit(false);
        poliOrgDto.setInputAddressDto(addressDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(97);
        userDto.setUserPersonName("test-updater-poli-org-address");

        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = new SaveKanrenshaPoliOrgCapsuleDto();
        capsuleDto.setKanrenshaPoliOrgDto(poliOrgDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (buildingが異なる)
        final int originalId = 1201;
        final String kanrenshaCode = "PO1201";
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(originalId, "住所更新前 政治団体", "新しいビル", kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterPoliticalOrganizationAddressEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(97, oldEntity.getDeleteUserId());

        MasterPoliticalOrganizationAddressEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("新しいビル", newEntity.getAddressBuilding(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPoliOrgKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        final String kanrenshaCode = "PO1201";
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createCapsuleDto(1201, "住所更新前 政治団体", "テストビル", kanrenshaCode);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterPoliticalOrganizationAddressEntity> entities = repository
                .findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationAddressIdDesc(kanrenshaCode);
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
