package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerNameRepository;

/**
 * GetRiyoushaManagerDtoService単体テスト
 */
@ExtendWith(MockitoExtension.class)
class GetRiyoushaManagerDtoServiceTest {

    /** テスト対象 */
    @InjectMocks
    private GetRiyoushaManagerDtoService service;

    /** 運営者連絡先Repository */
    @Mock
    private RiyoushaManagerAccessRepository riyoushaManagerAccessRepository;

    /** 運営者住所Repository */
    @Mock
    private RiyoushaManagerAddressRepository riyoushaManagerAddressRepository;

    /** 運営者名称Repository */
    @Mock
    private RiyoushaManagerNameRepository riyoushaManagerNameRepository;

    @Test
    void testPractice_Person() {
        // Setup
        RiyoushaManagerEntity entity = new RiyoushaManagerEntity();
        entity.setRiyoushaManagerId(1);
        entity.setIsNotOrg(true);
        entity.setRiyoushaManagerName("manager-user");

        RiyoushaManagerAccessEntity accessEntity = new RiyoushaManagerAccessEntity();
        accessEntity.setPhon1("111");

        RiyoushaManagerAddressEntity addressEntity = new RiyoushaManagerAddressEntity();
        addressEntity.setPostal1("101");
        addressEntity.setPostal2("0002");
        addressEntity.setAddressPostal("東京都新宿区");
        addressEntity.setAddressBlock("西新宿２－８－１");
        addressEntity.setAddressBuilding("東京都庁");

        RiyoushaManagerNameEntity nameEntity = new RiyoushaManagerNameEntity();
        nameEntity.setLastName("鈴木");
        nameEntity.setFirstName("一郎");
        nameEntity.setMiddleName("");
        nameEntity.setLastNameKana("スズキ");
        nameEntity.setFirstNameKana("イチロウ");
        nameEntity.setMiddleNameKana("");

        when(riyoushaManagerAccessRepository.findByRiyoushaManagerIdAndIsLatest(1, true))
                .thenReturn(Collections.singletonList(accessEntity));
        when(riyoushaManagerAddressRepository.findByRiyoushaManagerIdAndIsLatest(1, true))
                .thenReturn(Collections.singletonList(addressEntity));
        when(riyoushaManagerNameRepository.findByRiyoushaManagerIdAndIsLatest(1, true))
                .thenReturn(Collections.singletonList(nameEntity));

        // Execute
        RiyoushaManagerDto result = service.practice(entity);

        // Verify
        assertNotNull(result);
        assertEquals("manager-user", result.getRiyoushaManagerName());
        assertTrue(result.getIsNotOrg());

        assertNotNull(result.getInputAccessDto());
        assertEquals("111", result.getInputAccessDto().getPhon1());

        assertNotNull(result.getInputAddressDto());
        assertEquals("101", result.getInputAddressDto().getPostalcode1());
        assertEquals("0002", result.getInputAddressDto().getPostalcode2());
        assertEquals("東京都新宿区西新宿２－８－１　東京都庁", result.getInputAddressDto().getAddressAll());

        assertNotNull(result.getInputPersonNameDto());
        assertEquals("鈴木　一郎", result.getInputPersonNameDto().getAllName());
        assertEquals("スズキ　イチロウ", result.getInputPersonNameDto().getAllNameKana());

    }

    @Test
    void testPractice_Org() {
        // Setup
        RiyoushaManagerEntity entity = new RiyoushaManagerEntity();
        entity.setRiyoushaManagerId(2);
        entity.setIsNotOrg(false);
        entity.setRiyoushaManagerName("manager-org");

        RiyoushaManagerAccessEntity accessEntity = new RiyoushaManagerAccessEntity();
        RiyoushaManagerNameEntity nameEntity = new RiyoushaManagerNameEntity();
        nameEntity.setOrgName("マネージャー株式会社");
        nameEntity.setOrgNameKana("マネージャーカブシキガイシャ");

        when(riyoushaManagerAccessRepository.findByRiyoushaManagerIdAndIsLatest(2, true))
                .thenReturn(Collections.singletonList(accessEntity));
        RiyoushaManagerAddressEntity addressEntity = new RiyoushaManagerAddressEntity();
        when(riyoushaManagerAddressRepository.findByRiyoushaManagerIdAndIsLatest(2, true))
                .thenReturn(Collections.singletonList(addressEntity));
        when(riyoushaManagerNameRepository.findByRiyoushaManagerIdAndIsLatest(2, true))
                .thenReturn(Collections.singletonList(nameEntity));

        // Execute
        RiyoushaManagerDto result = service.practice(entity);

        // Verify
        assertNotNull(result);
        assertEquals("manager-org", result.getRiyoushaManagerName());
        assertFalse(result.getIsNotOrg());

        assertNotNull(result.getInputOrgNameDto());
        assertEquals("マネージャー株式会社", result.getInputOrgNameDto().getOrgName());
        assertEquals("マネージャーカブシキガイシャ", result.getInputOrgNameDto().getOrgNameKana());
    }

    @Test
    void testPractice_AccessNotFound() {
        // Setup
        RiyoushaManagerEntity entity = new RiyoushaManagerEntity();
        entity.setRiyoushaManagerId(1);

        when(riyoushaManagerAccessRepository.findByRiyoushaManagerIdAndIsLatest(1, true))
                .thenReturn(Collections.emptyList());

        // Execute & Verify
        assertThrows(EmptyResultDataAccessException.class, () -> {
            service.practice(entity);
        });
    }

    @Test
    void testPractice_MultipleAccessFound() {
        // Setup
        RiyoushaManagerEntity entity = new RiyoushaManagerEntity();
        entity.setRiyoushaManagerId(1);

        when(riyoushaManagerAccessRepository.findByRiyoushaManagerIdAndIsLatest(1, true))
                .thenReturn(List.of(new RiyoushaManagerAccessEntity(), new RiyoushaManagerAccessEntity()));

        // Execute & Verify
        assertThrows(ConcurrencyFailureException.class, () -> {
            service.practice(entity);
        });
    }

}
