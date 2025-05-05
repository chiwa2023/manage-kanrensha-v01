package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
 * SearchAddressBuildingService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchAddressBuildingServiceTest {

    /** テスト対象 */
    @Autowired
    private SearchAddressBuildingService searchAddressBuildingService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("add_011209.sql")
    void test() {

        // 建物欄に設定がない場合は空リストを返す
        PostalCodeBuildingResultDto resultDto0 = searchAddressBuildingService.practice("011029", "札幌市北区北十五条西一丁目1番地1号");
        assertTrue(resultDto0.getListOptions().isEmpty());

        // 建物欄に設定がある場合
        PostalCodeBuildingResultDto resultDto1 = searchAddressBuildingService.practice("011029", "札幌市北区北十五条西一丁目1番地2号");
        List<SelectOptionStringDto> list1 = resultDto1.getListOptions();
        assertEquals(2, list1.size());
        SelectOptionStringDto optionDto10 = list1.get(0);
        assertEquals("AAマンション101号室", optionDto10.getText());
        SelectOptionStringDto optionDto11 = list1.get(1);
        assertEquals("AAマンション102号室", optionDto11.getText());
    }

}
