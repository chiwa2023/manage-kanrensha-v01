package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaAdminDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminNameRepository;

/**
 * GetRiyoushaAdminDtoService単体テスト
 */
@ExtendWith(MockitoExtension.class)
class GetRiyoushaAdminDtoServiceTest {

    /** テスト対象 */
    @InjectMocks
    private GetRiyoushaAdminDtoService service;

    /** 利用者管理者連絡先Repository */
    @Mock
    private RiyoushaAdminAccessRepository riyoushaAdminAccessRepository;

    /** 利用者管理者住所Repository */
    @Mock
    private RiyoushaAdminAddressRepository riyoushaAdminAddressRepository;

    /** 利用者管理者名称Repository */
    @Mock
    private RiyoushaAdminNameRepository riyoushaAdminNameRepository;

    @Test
    @Tag("TableTruncate")
    void testPractice_Person() {
        // Setup
        RiyoushaAdminEntity entity = new RiyoushaAdminEntity();
        entity.setRiyoushaAdminId(1);
        entity.setIsNotOrg(true);
        entity.setRiyoushaAdminName("test-user");

        RiyoushaAdminAccessEntity accessEntity = new RiyoushaAdminAccessEntity();
        accessEntity.setPhon1("123");

        RiyoushaAdminAddressEntity addressEntity = new RiyoushaAdminAddressEntity();
        addressEntity.setPostal1("100");
        addressEntity.setPostal2("0001");
        addressEntity.setAddressPostal("東京都千代田区");
        addressEntity.setAddressBlock("千代田１－１");
        addressEntity.setAddressBuilding("テストビル１Ｆ");

        RiyoushaAdminNameEntity nameEntity = new RiyoushaAdminNameEntity();
        nameEntity.setLastName("山田");
        nameEntity.setFirstName("太郎");
        nameEntity.setMiddleName("");
        nameEntity.setLastNameKana("ヤマダ");
        nameEntity.setFirstNameKana("タロウ");
        nameEntity.setMiddleNameKana("");

        when(riyoushaAdminAccessRepository.findByRiyoushaAdminIdAndIsLatest(1, true))
                .thenReturn(Collections.singletonList(accessEntity));
        when(riyoushaAdminAddressRepository.findByRiyoushaAdminIdAndIsLatest(1, true))
                .thenReturn(Collections.singletonList(addressEntity));
        when(riyoushaAdminNameRepository.findByRiyoushaAdminIdAndIsLatest(1, true))
                .thenReturn(Collections.singletonList(nameEntity));

        // Execute
        RiyoushaAdminDto result = service.practice(entity);

        // Verify
        assertNotNull(result);
        assertEquals("test-user", result.getRiyoushaAdminName());
        assertTrue(result.getIsNotOrg());

        assertNotNull(result.getInputAccessDto());
        assertEquals("123", result.getInputAccessDto().getPhon1());

        assertNotNull(result.getInputAddressDto());
        assertEquals("100", result.getInputAddressDto().getPostalcode1());
        assertEquals("0001", result.getInputAddressDto().getPostalcode2());
        assertEquals("東京都千代田区千代田１－１　テストビル１Ｆ", result.getInputAddressDto().getAddressAll());

        assertNotNull(result.getInputPersonNameDto());
        assertEquals("山田　太郎", result.getInputPersonNameDto().getAllName());
        assertEquals("ヤマダ　タロウ", result.getInputPersonNameDto().getAllNameKana());

    }

    @Test
    void testPractice_Org() {
        // Setup
        RiyoushaAdminEntity entity = new RiyoushaAdminEntity();
        entity.setRiyoushaAdminId(2);
        entity.setIsNotOrg(false);
        entity.setRiyoushaAdminName("test-org");

        RiyoushaAdminAccessEntity accessEntity = new RiyoushaAdminAccessEntity();
        RiyoushaAdminNameEntity nameEntity = new RiyoushaAdminNameEntity();
        nameEntity.setOrgName("テスト株式会社");
        nameEntity.setOrgNameKana("テストカブシキガイシャ");

        when(riyoushaAdminAccessRepository.findByRiyoushaAdminIdAndIsLatest(2, true))
                .thenReturn(Collections.singletonList(accessEntity));
        RiyoushaAdminAddressEntity addressEntity = new RiyoushaAdminAddressEntity();
        when(riyoushaAdminAddressRepository.findByRiyoushaAdminIdAndIsLatest(2, true))
                .thenReturn(Collections.singletonList(addressEntity));
        when(riyoushaAdminNameRepository.findByRiyoushaAdminIdAndIsLatest(2, true))
                .thenReturn(Collections.singletonList(nameEntity));

        // Execute
        RiyoushaAdminDto result = service.practice(entity);

        // Verify
        assertNotNull(result);
        assertEquals("test-org", result.getRiyoushaAdminName());
        assertFalse(result.getIsNotOrg());

        assertNotNull(result.getInputOrgNameDto());
        assertEquals("テスト株式会社", result.getInputOrgNameDto().getOrgName());
        assertEquals("テストカブシキガイシャ", result.getInputOrgNameDto().getOrgNameKana());
    }

    @Test
    void testPractice_AccessNotFound() {
        // Setup
        RiyoushaAdminEntity entity = new RiyoushaAdminEntity();
        entity.setRiyoushaAdminId(1);

        when(riyoushaAdminAccessRepository.findByRiyoushaAdminIdAndIsLatest(1, true))
                .thenReturn(Collections.emptyList());

        // Execute & Verify
        assertThrows(EmptyResultDataAccessException.class, () -> {
            service.practice(entity);
        });
    }

    @Test
    void testPractice_MultipleAccessFound() {
        // Setup
        RiyoushaAdminEntity entity = new RiyoushaAdminEntity();
        entity.setRiyoushaAdminId(1);

        when(riyoushaAdminAccessRepository.findByRiyoushaAdminIdAndIsLatest(1, true))
                .thenReturn(List.of(new RiyoushaAdminAccessEntity(), new RiyoushaAdminAccessEntity()));

        // Execute & Verify
        assertThrows(ConcurrencyFailureException.class, () -> {
            service.practice(entity);
        });
    }

}
