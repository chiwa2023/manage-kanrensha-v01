package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;

/**
 * ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationEntityLogicのテスト
 */
@SpringBootTest
class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationEntityLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationEntityLogic logic;

    @Test
    void testPractice() {
        // Arrange
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll("東京都テスト区1-1");
        dto.setInputAddressDto(addressDto);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト政治団体");
        dto.setInputOrgNameDto(nameDto);

        dto.setPoliOrgKanrenshaCode("PO00000001");

        // Act
        MasterPoliticalOrganizationEntity result = logic.practice(dto);

        // Assert
        assertNotNull(result);
        assertEquals("東京都テスト区1-1", result.getAllAddress());
        assertEquals("テスト政治団体", result.getPartnerName());
        assertEquals("PO00000001", result.getPoliOrgKanrenshaCode());
        assertEquals("テスト政治団体", result.getCompareNameText());
    }
}
