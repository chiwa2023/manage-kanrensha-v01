package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;

/**
 * ConvertKanrenshaCorpDtoToMasterCorporationEntityLogicのテスト
 */
@SpringBootTest
class ConvertKanrenshaCorpDtoToMasterCorporationEntityLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationEntityLogic logic;

    @Test
    void testPractice() {
        // Arrange
        KanrenshaCorpDto dto = new KanrenshaCorpDto();

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll("東京都テスト区1-1");
        dto.setInputAddressDto(addressDto);

        InputOrgNameDto nameDto = new InputOrgNameDto();
        nameDto.setOrgName("テスト株式会社");
        dto.setInputOrgNameDto(nameDto);

        dto.setCorpKanrenshaCode("C00000001");

        // Act
        MasterCorporationEntity result = logic.practice(dto);

        // Assert
        assertNotNull(result);
        assertEquals("東京都テスト区1-1", result.getAllAddress());
        assertEquals("テスト株式会社", result.getPartnerName());
        assertEquals("C00000001", result.getCorpKanrenshaCode());
        // formatNaturalSearchTextUtilによって値が設定されていることを確認
        assertEquals("テストカブシキガイシャ", result.getCompareNameText());
    }
}
