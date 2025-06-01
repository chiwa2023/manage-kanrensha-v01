package mitei.mitei.political.balancesheet.manage.kanrensha.service.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;

/**
 * SearchHoujinNoByNoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchHoujinNoByNoServiceTest {

    /** テスト対象 */
    @Autowired
    private SearchHoujinNoByNoService searchHoujinNoByNoService;

    @Test
    void test() throws Exception {
        // CHECKSTYLE:OFF

        HoujinNoCapsuleDto capsuleDto = new HoujinNoCapsuleDto();
        capsuleDto.setSearchNoWords("123");
        HoujinNoResultDto resultDto = searchHoujinNoByNoService.practice(capsuleDto);

        assertEquals(3985, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());
        assertEquals(30, resultDto.getLimit());
        List<HoujinNoLatestEntity> list = resultDto.getListHoujinNo();
        assertEquals(30, list.size());

        HoujinNoLatestEntity entity00 = list.get(0);
        assertEquals("1230001000017", entity00.getCorporateNumber());
        assertEquals("株式会社アキオ", entity00.getName());

        HoujinNoLatestEntity entity01 = list.get(1);
        assertEquals("1230001000025", entity01.getCorporateNumber());
        assertEquals("青山工業株式会社", entity01.getName());

        HoujinNoLatestEntity entity29 = list.get(29);
        assertEquals("1230001000388", entity29.getCorporateNumber());
        assertEquals("株式会社エスケー産業", entity29.getName());

    }

}
