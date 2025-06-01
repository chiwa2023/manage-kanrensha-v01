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
 * SearchHoujinNoByNaturalSearchSearvice単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchHoujinNoByNaturalSearchSearviceTest {
    // CHECKSTYLE:OFF
    
    /** テスト対象 */
    @Autowired
    private SearchHoujinNoByNaturalSearchSearvice searchHoujinNoByNaturalSearchSearvice;
    
    @Test
    void test()throws Exception {
        
        HoujinNoCapsuleDto capsuleDto = new HoujinNoCapsuleDto();
        capsuleDto.setSearchNaturaloWords("+住友林業");
        HoujinNoResultDto resultDto = searchHoujinNoByNaturalSearchSearvice.practice(capsuleDto);

        assertEquals(14, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());
        assertEquals(30, resultDto.getLimit());
        List<HoujinNoLatestEntity> list = resultDto.getListHoujinNo();
        assertEquals(14, list.size());

        
        
        HoujinNoLatestEntity entity01 = list.get(0);
        assertEquals("1500005007419", entity01.getCorporateNumber());
        assertEquals("有限責任住友林業株式会社四国支店従業員住宅組合", entity01.getName());

        HoujinNoLatestEntity entity00 = list.get(1);
        assertEquals("8700150067942", entity00.getCorporateNumber());
        assertEquals("住友林業グループ企業年金基金", entity00.getName());

        HoujinNoLatestEntity entity29 = list.get(13);
        assertEquals("2010001089831", entity29.getCorporateNumber());
        assertEquals("住友林業クレスト株式会社", entity29.getName());

    }

}
