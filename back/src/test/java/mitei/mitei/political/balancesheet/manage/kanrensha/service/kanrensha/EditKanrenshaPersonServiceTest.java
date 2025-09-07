package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;

/**
 * EditKanrenshaPersonServiceのテスト
 */
@SpringBootTest
@Transactional
@Sql("EditKanrenshaPersonServiceTest.sql")
class EditKanrenshaPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditKanrenshaPersonService service;

    // Dependencies for verification
    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository personRepository;
    /** 関連者個人連絡先Repository */
    @Autowired
    private MasterPersonAccessRepository accessRepository;
    /** 関連者個人住所Repository */
    @Autowired
    private MasterPersonAddressRepository addressRepository;
    /** 関連者個人基本Repository */
    @Autowired
    private MasterPersonBaseRepository baseRepository;
    /** 関連者個人属性Repository */
    @Autowired
    private MasterPersonPropertyRepository propertyRepository;

    private SaveKanrenshaPersonCapsuleDto createTestDtoWithChanges() {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        // Set IDs for all entities
        dto.setMasterId(901);
        dto.setAccessId(902);
        dto.setAddressId(903);
        dto.setBaseId(904);
        dto.setPropertyId(905);

        // Set values that differ from the SQL data to trigger updates
        dto.setInputPersonNameDto(new InputPersonNameDto() {
            { // NOPMD
                setAllName("New Name");
            }
        });
        dto.setInputAccessDto(new InputAccessDto() {
            { // NOPMD
                setEmail("new.email@example.com");
            }
        });
        dto.setInputAddressDto(new InputAddressDto() {
            { // NOPMD
                setAddressBuilding("New Building");
            }
        });
        dto.getInputPersonNameDto().setLastName("New Last Name"); // For Base Logic
        dto.setInputShokugyouDto(new InputShokugyouDto());
        dto.setIsForeign(true); // For Property Logic

        UserPersonLeastDto userDto = new UserPersonLeastDto() {
            { // NOPMD
                setUserPersonId(100);
            }
        };
        SaveKanrenshaPersonCapsuleDto capsule = new SaveKanrenshaPersonCapsuleDto();
        capsule.setKanrenshaPersonDto(dto);
        capsule.setUserPersonLeastDto(userDto);
        return capsule;
    }

    @Test
    void testSuccessUpdate() {
        // Arrange
        SaveKanrenshaPersonCapsuleDto capsuleDto = createTestDtoWithChanges();

        // Act
        service.practice(capsuleDto);

        // Assert: Check that all old records are now marked as not latest
        assertFalse(personRepository.findById(901).get().getIsLatest());
        assertFalse(accessRepository.findById(902).get().getIsLatest());
        assertFalse(addressRepository.findById(903).get().getIsLatest());
        assertFalse(baseRepository.findById(904).get().getIsLatest());
        assertFalse(propertyRepository.findById(905).get().getIsLatest());

        final String kanrensahCode =  "P0900";
        
        // Assert: Check that new records have been created
        assertEquals(2, personRepository.findByPersonKanrenshaCodeOrderByMasterPersonIdDesc(kanrensahCode).size());
        assertEquals(2, accessRepository.findByPersonKanrenshaCodeOrderByMasterPersonAccessIdDesc(kanrensahCode).size());
        assertEquals(2, addressRepository.findByPersonKanrenshaCodeOrderByMasterPersonAddressIdDesc(kanrensahCode).size());
        assertEquals(2, baseRepository.findByPersonKanrenshaCodeOrderByMasterPersonBaseIdDesc(kanrensahCode).size());
        assertEquals(2, propertyRepository.findByPersonKanrenshaCodeOrderByMasterPersonPropertyIdDesc(kanrensahCode).size());
    }

    @Test
    void testTransactionRollback() {

        SaveKanrenshaPersonCapsuleDto capsuleDto = createTestDtoWithChanges();
        capsuleDto.getKanrenshaPersonDto().setPropertyId(1298); // 属性が呼び出しできない

        // 実際にはEmptyResultDataAccessExceptionが発生している(がその他もろもろの例外でもよい)
        assertThrows(RuntimeException.class, () -> {
            service.practice(capsuleDto);
        });

        // すべてにのテーブルで履歴の積み上がりはない
        assertEquals(1, personRepository.count());
        assertEquals(1, accessRepository.count());
        assertEquals(1, addressRepository.count());
        assertEquals(1, baseRepository.count());
        assertEquals(1, propertyRepository.count());

        // 元データは最新のまま
        assertTrue(personRepository.findById(901).get().getIsLatest(), "person should be unchanged.");
        assertTrue(accessRepository.findById(902).get().getIsLatest(), "access should be unchanged.");
        assertTrue(addressRepository.findById(903).get().getIsLatest(), "address should be unchanged.");
        assertTrue(baseRepository.findById(904).get().getIsLatest(), "base should be unchanged.");
        assertTrue(propertyRepository.findById(906).get().getIsLatest(), "property should be unchanged.");

    }
}