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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;

/**
 * EditMasterPersonAddressLogicのテスト
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPersonAddressLogicTest.sql")
class EditMasterPersonAddressLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPersonAddressLogic logic;

    /** 関連者個人住所Repository */
    @Autowired
    private MasterPersonAddressRepository repository;

    private SaveKanrenshaPersonCapsuleDto createCapsuleDto(final Integer addressId, final String partnerName,
            final String building,final String kanrenshaCode) {
        KanrenshaPersonDto personDto = new KanrenshaPersonDto();
        personDto.setAddressId(addressId);
        personDto.setPersonKanrenshaCode(kanrenshaCode);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName(partnerName);
        personDto.setInputPersonNameDto(nameDto);

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
        // addressDto.setRsdt2Id("002");
        addressDto.setIsPostalEdit(false);
        addressDto.setIsBlockEdit(false);
        addressDto.setIsBuildingEdit(false);
        // addressDto.setIsPostalAccept(true);
        // addressDto.setIsBlockAccept(true);
        // addressDto.setIsBuildingAccept(true);
        personDto.setInputAddressDto(addressDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(98);
        userDto.setUserPersonName("test-updater-address");

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(personDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成 (buildingが異なる)
        final int originalId = 601;
        final String kanrenshaCode = "P0601";
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(originalId, "住所更新前 一郎", "新しい宮殿",kanrenshaCode);

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");

        MasterPersonAddressEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(98, oldEntity.getDeleteUserId());

        MasterPersonAddressEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("新しい宮殿", newEntity.getAddressBuilding(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPersonKanrenshaCode(), "関連者コードが引き継がれていること");
    }

    @Test
    void testNoUpdateOccurs() {
        
        // Arrange: DBと完全に同じ値を持つDTOを作成
        final String kanrenshaCode = "P0601";
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(601, "住所更新前 一郎", "宮殿",kanrenshaCode);

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        List<MasterPersonAddressEntity> entities = repository
                .findByPersonKanrenshaCodeOrderByMasterPersonAddressIdDesc(kanrenshaCode);
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
