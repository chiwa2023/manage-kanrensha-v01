package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;

/**
 * EditKanrenshaCorpServiceのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
class EditKanrenshaCorpServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditKanrenshaCorpService service;

    // Verification Repositories
    /** 関連者企業団体マスタRepository */
    @Autowired
    private MasterCorporationRepository corporationRepository;
    /** 関連者企業団体連絡先Repository */
    @Autowired
    private MasterCorporationAccessRepository accessRepository;
    /** 関連者企業団体住所Repository */
    @Autowired
    private MasterCorporationAddressRepository addressRepository;
    /** 関連者企業団体基本Repository */
    @Autowired
    private MasterCorporationBaseRepository baseRepository;
    /** 関連者企業団体属性Repository */
    @Autowired
    private MasterCorporationPropertyRepository propertyRepository;

    private SaveKanrenshaCorpCapsuleDto createTestDtoWithChanges() {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        // Set IDs for all entities
        dto.setMasterId(191);
        dto.setAccessId(2601);
        dto.setAddressId(2201);
        dto.setBaseId(2301);
        dto.setPropertyId(2401);

        // Set values that differ from the SQL data
        dto.setInputOrgNameDto(new InputOrgNameDto() {
            { setOrgName("New Corp Name"); } // NOPMD
        });
        dto.setInputAddressDto(new InputAddressDto() {
            { setAddressAll("New Corp Address"); } // NOPMD
        });
        dto.setInputAccessDto(new InputAccessDto() {
            { setEmail("new.corp@example.com"); } // NOPMD
        });
        // dto.getInputOrgNameDto().setOrgShortName("New Corp Short"); // For Base
        dto.setHoujinSbts("301"); // For Property
        dto.setOrgDelegateLeastDto(new InputKanrenshaPersonLeastDto());

        UserPersonLeastDto userDto = new UserPersonLeastDto() {
            { setUserPersonId(102); } // NOPMD
        };
        SaveKanrenshaCorpCapsuleDto capsule = new SaveKanrenshaCorpCapsuleDto();
        capsule.setKanrenshaCorpDto(dto);
        capsule.setUserPersonLeastDto(userDto);
        return capsule;
    }

    @Test
    @Transactional
    @Sql("EditKanrenshaCorpServiceTest.sql")
    void testSuccessUpdate() {
        // Arrange
        SaveKanrenshaCorpCapsuleDto capsuleDto = createTestDtoWithChanges();
        final String kanrenshaCode = "111-222-3333";
        capsuleDto.getKanrenshaCorpDto().setCorpKanrenshaCode(kanrenshaCode);

        // Act
        service.practice(capsuleDto);

        // Assert: Check that all old records are now marked as not latest
        assertFalse(corporationRepository.findById(191).get().getIsLatest());
        assertFalse(accessRepository.findById(2601).get().getIsLatest());
        assertFalse(addressRepository.findById(2201).get().getIsLatest());
        assertFalse(baseRepository.findById(2301).get().getIsLatest());
        assertFalse(propertyRepository.findById(2401).get().getIsLatest());

        // Assert: Check that new records have been created
        assertEquals(2, corporationRepository.findByCorpKanrenshaCodeOrderByMasterCorporationIdDesc(kanrenshaCode).size());
        assertEquals(2, accessRepository.findByCorpKanrenshaCodeOrderByMasterCorporationAccessIdDesc(kanrenshaCode).size());
        assertEquals(2, addressRepository.findByCorpKanrenshaCodeOrderByMasterCorporationAddressIdDesc(kanrenshaCode).size());
        assertEquals(2, baseRepository.findByCorpKanrenshaCodeOrderByMasterCorporationBaseIdDesc(kanrenshaCode).size());
        assertEquals(2, propertyRepository.findByCorpKanrenshaCodeOrderByMasterCorporationPropertyIdDesc(kanrenshaCode).size());
    }

    @Test
    @Sql("EditKanrenshaCorpServiceTest.sql")
    void testTransactionRollback() {
        // Arrange: Create DTO that will cause an exception (e.g., non-existent ID)
        SaveKanrenshaCorpCapsuleDto capsuleDto = createTestDtoWithChanges();
        capsuleDto.getKanrenshaCorpDto().setPropertyId(9999); // This ID does not exist

        // Act & Assert: Expect an exception and verify rollback
        assertThrows(Exception.class, () -> {
            service.practice(capsuleDto);
        });

        // Assert: No new records were created
        assertEquals(1, corporationRepository.count());
        assertEquals(1, accessRepository.count());
        assertEquals(1, addressRepository.count());
        assertEquals(1, baseRepository.count());
        assertEquals(1, propertyRepository.count());

        // Assert: Original records are unchanged and still latest
        assertTrue(corporationRepository.findById(191).get().getIsLatest());
        assertTrue(accessRepository.findById(2601).get().getIsLatest());
        assertTrue(addressRepository.findById(2201).get().getIsLatest());
        assertTrue(baseRepository.findById(2301).get().getIsLatest());
        assertTrue(propertyRepository.findById(2401).get().getIsLatest());
    }
}