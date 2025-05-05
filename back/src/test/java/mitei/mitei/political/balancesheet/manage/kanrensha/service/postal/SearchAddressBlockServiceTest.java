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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionStringDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;

/**
 * SearchAddressBlockService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchAddressBlockServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchAddressBlockService searchAddressBlockService;

    @Test
    @Tag("TableTruncate")
    void test() {

        PostalCodeBlockResultDto resultDto0 = searchAddressBlockService.practice(124790, true);
        assertEquals("011029", resultDto0.getLgCode());
        List<SelectOptionStringDto> list0 = resultDto0.getListOptions();
        assertEquals(44, list0.size());
        SelectOptionStringDto dto00 = list0.get(0);
        assertEquals("札幌市北区北十五条西一丁目1番地1号", dto00.getValue());
        assertEquals("1番地1号", dto00.getText());

        PostalCodeBlockResultDto resultDto1 = searchAddressBlockService.practice(1254, false);

        // assertFalse(resultDto1.getIsGyouseikuData());
        List<SelectOptionStringDto> list1 = resultDto1.getListOptions();
        assertEquals(1, list1.size());
        SelectOptionStringDto dto10 = list1.get(0);
        assertEquals("千代田区丸の内一丁目11番1号", dto10.getValue());
        assertEquals("一丁目11番1号", dto10.getText());

    }

}
