package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionNumberDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodePostalResultDto;

/**
 * SearchAddressPostalService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchAddressPostalServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchAddressPostalService searchAddressPostalService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // 地方自治体住居取得
        PostalCodePostalResultDto resultDto0 = searchAddressPostalService.practice("0010015");
        assertTrue(resultDto0.getIsGyouseikuData());

        List<SelectOptionNumberDto> list0 = resultDto0.getListOptions();
        assertEquals(5, list0.size());

        // '124790', '札幌市北区北十五条西一丁目', '011029'
        SelectOptionNumberDto dto00 = list0.get(0);
        assertEquals("札幌市北区北十五条西一丁目", dto00.getText());

        // '124791', '札幌市北区北十五条西二丁目', '011029'
        SelectOptionNumberDto dto01 = list0.get(1);
        assertEquals("札幌市北区北十五条西二丁目", dto01.getText());

        // '124792', '札幌市北区北十五条西三丁目', '011029'
        SelectOptionNumberDto dto02 = list0.get(2);
        assertEquals("札幌市北区北十五条西三丁目", dto02.getText());

        // '124793', '札幌市北区北十五条西四丁目', '011029'
        SelectOptionNumberDto dto03 = list0.get(3);
        assertEquals("札幌市北区北十五条西四丁目", dto03.getText());

        // '124794', '札幌市北区北十五条西五丁目', '011029'
        SelectOptionNumberDto dto04 = list0.get(4);
        assertEquals("札幌市北区北十五条西五丁目", dto04.getText());

        PostalCodePostalResultDto resultDto1 = searchAddressPostalService.practice("1006222");
        assertFalse(resultDto1.getIsGyouseikuData());
        List<SelectOptionNumberDto> list1 = resultDto1.getListOptions();
        assertEquals(1, list1.size());

        SelectOptionNumberDto dto10 = list1.get(0);
        assertEquals("千代田区丸の内", dto10.getText());
    }

}
