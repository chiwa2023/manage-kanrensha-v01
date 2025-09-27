package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonEntityLogicのテスト
 */
@SpringBootTest
class ConvertKanrenshaPersonDtoToMasterPersonEntityLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonEntityLogic logic;

    @Test
    void testPractice() {
        // Arrange
        KanrenshaPersonDto dto = new KanrenshaPersonDto();

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll("東京都テスト区1-1");
        dto.setInputAddressDto(addressDto);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName("テスト　太郎");
        dto.setInputPersonNameDto(nameDto);

        InputShokugyouDto shokugyouDto = new InputShokugyouDto();
        shokugyouDto.setAllShokugyou("テスト職業");
        dto.setInputShokugyouDto(shokugyouDto);

        // Act
        MasterPersonEntity result = logic.practice(dto);

        // Assert
        assertNotNull(result);
        assertEquals("東京都テスト区1-1", result.getAllAddress());
        assertEquals("テスト　太郎", result.getPartnerName());
        assertEquals("テスト職業", result.getPersonShokugyou());
        // formatNaturalSearchTextUtilによって値が設定されていることを確認
        assertEquals("テストタロウ", result.getCompareNameText());
    }
}
