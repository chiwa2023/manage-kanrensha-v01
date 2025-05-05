package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionStringDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeBuildingResultDto;

/**
 * SearchAddressFloorPostalService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchAddressFloorPostalServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchAddressFloorPostalService searchAddressFloorPostalService;
    
    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("add_131016.sql")
    void test() {
        
        PostalCodeBuildingResultDto resultDto0 = searchAddressFloorPostalService.practice("131016", "1006222");
        List<SelectOptionStringDto> list0 = resultDto0.getListOptions();
        assertEquals(3, list0.size());

        SelectOptionStringDto optionDto00 = list0.get(0);
        assertEquals("丸の内パシフィックセンチュリープレイス丸の内224号室", optionDto00.getText());

        SelectOptionStringDto optionDto01 = list0.get(1);
        assertEquals("丸の内パシフィックセンチュリープレイス丸の内225号室", optionDto01.getText());

        SelectOptionStringDto optionDto02 = list0.get(2);
        assertEquals("丸の内パシフィックセンチュリープレイス丸の内226号室", optionDto02.getText());
        
    }
}
