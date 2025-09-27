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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;

/**
 * EditKanrenshaPoliOrgServiceのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
class EditKanrenshaPoliOrgServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditKanrenshaPoliOrgService service;

    // Verification Repositories
    /** 関連者政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository organizationRepository;
    /** 関連者政治団体連絡先Repository */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository accessRepository;
    /** 関連者政治団体住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository addressRepository;
    /** 関連者政治団体基本Repository */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository baseRepository;
    /** 関連者政治団体属性Repository */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository propertyRepository;

    private SaveKanrenshaPoliOrgCapsuleDto createTestDtoWithChanges() {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        // Set IDs for all entities
        dto.setMasterId(724);
        dto.setAccessId(601);
        dto.setAddressId(701);
        dto.setBaseId(801);
        dto.setPropertyId(901);

        // Set values that differ from the SQL data
        dto.setInputOrgNameDto(new InputOrgNameDto() {
            { setOrgName("New Org Name"); } // NOPMD
        });
        dto.setInputAddressDto(new InputAddressDto() {
            { setAddressAll("New Address"); } // NOPMD
        });
        dto.setInputAccessDto(new InputAccessDto() {
            { setEmail("new.org@example.com"); } // NOPMD
        });
        // dto.getInputOrgNameDto().setOrgShortName("New Short"); // For Base
        dto.setAccounrMgrLeastDto(new InputKanrenshaPersonLeastDto() {
            { setPersonName("New Manager"); } // NOPMD
        }); // For Property
        dto.setOrgDelegateLeastDto(new InputKanrenshaPersonLeastDto());
        dto.setAccounrMgrLeastDto(new InputKanrenshaPersonLeastDto());

        UserPersonLeastDto userDto = new UserPersonLeastDto() {
            { setUserPersonId(101); } // NOPMD
        };
        SaveKanrenshaPoliOrgCapsuleDto capsule = new SaveKanrenshaPoliOrgCapsuleDto();
        capsule.setKanrenshaPoliOrgDto(dto);
        capsule.setUserPersonLeastDto(userDto);
        return capsule;
    }

    @Test
    @Transactional
    @Sql("EditKanrenshaPoliOrgServiceTest.sql")
    void testSuccessUpdate() {
        // Arrange
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createTestDtoWithChanges();
        final String kanrenshaCode = "12-345-ABCCDEF";
        capsuleDto.getKanrenshaPoliOrgDto().setPoliOrgKanrenshaCode(kanrenshaCode);

        // Act
        service.practice(capsuleDto);

        // Assert: Check that all old records are now marked as not latest
        assertFalse(organizationRepository.findById(724).get().getIsLatest());
        assertFalse(accessRepository.findById(601).get().getIsLatest());
        assertFalse(addressRepository.findById(701).get().getIsLatest());
        assertFalse(baseRepository.findById(801).get().getIsLatest());
        assertFalse(propertyRepository.findById(901).get().getIsLatest());

        // Assert: Check that new records have been created
        assertEquals(2, organizationRepository.findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationIdDesc(kanrenshaCode).size());
        assertEquals(2, accessRepository.findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationAccessIdDesc(kanrenshaCode).size());
        assertEquals(2, addressRepository.findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationAddressIdDesc(kanrenshaCode).size());
        assertEquals(2, baseRepository.findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationBaseIdDesc(kanrenshaCode).size());
        assertEquals(2, propertyRepository.findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationPropertyIdDesc(kanrenshaCode).size());
    }

    @Test
    @Sql("EditKanrenshaPoliOrgServiceTest.sql")
    void testTransactionRollback() {
        // Arrange: Create DTO that will cause an exception (e.g., non-existent ID)
        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = createTestDtoWithChanges();
        capsuleDto.getKanrenshaPoliOrgDto().setPropertyId(9999); // This ID does not exist

        // Act & Assert: Expect an exception and verify rollback
        assertThrows(Exception.class, () -> {
            service.practice(capsuleDto);
        });

        // Assert: No new records were created
        assertEquals(1, organizationRepository.count());
        assertEquals(1, accessRepository.count());
        assertEquals(1, addressRepository.count());
        assertEquals(1, baseRepository.count());
        assertEquals(1, propertyRepository.count());

        // Assert: Original records are unchanged and still latest
        assertTrue(organizationRepository.findById(724).get().getIsLatest());
        assertTrue(accessRepository.findById(601).get().getIsLatest());
        assertTrue(addressRepository.findById(701).get().getIsLatest());
        assertTrue(baseRepository.findById(801).get().getIsLatest());
        assertTrue(propertyRepository.findById(901).get().getIsLatest());
    }
}