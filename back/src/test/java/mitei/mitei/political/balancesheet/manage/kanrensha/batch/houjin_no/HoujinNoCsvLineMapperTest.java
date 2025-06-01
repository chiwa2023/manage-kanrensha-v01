package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * HoujinNoCsvLineMapper単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class HoujinNoCsvLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        HoujinNoCsvLineMapper lineMapper = new HoujinNoCsvLineMapper();

        String line = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30";

        HoujinNoCsvDto csvDto = lineMapper.mapLine(line, 0);

        assertEquals("1", csvDto.getSequenceNumber());
        assertEquals("2", csvDto.getCorporateNumber());
        assertEquals("3", csvDto.getProcess());
        assertEquals("4", csvDto.getCorrect());
        assertEquals("5", csvDto.getUpdateDate());
        assertEquals("6", csvDto.getChangeDate());
        assertEquals("7", csvDto.getName());
        assertEquals("8", csvDto.getNameImageId());
        assertEquals("9", csvDto.getKind());
        assertEquals("10", csvDto.getPrefectureName());
        assertEquals("11", csvDto.getCityName());
        assertEquals("12", csvDto.getStreetNumber());
        assertEquals("13", csvDto.getAddressImageId());
        assertEquals("14", csvDto.getPrefectureCode());
        assertEquals("15", csvDto.getCityCode());
        assertEquals("16", csvDto.getPostCode());
        assertEquals("17", csvDto.getAddressOutside());
        assertEquals("18", csvDto.getAddressOutsideImageId());
        assertEquals("19", csvDto.getCloseDate());
        assertEquals("20", csvDto.getCloseCause());
        assertEquals("21", csvDto.getSuccessorCorporateNumber());
        assertEquals("22", csvDto.getChangeCause());
        assertEquals("23", csvDto.getAssignmentDate());
        assertEquals("24", csvDto.getLatest());
        assertEquals("25", csvDto.getEnName());
        assertEquals("26", csvDto.getEnPrefectureName());
        assertEquals("27", csvDto.getEnCityName());
        assertEquals("28", csvDto.getEnAddressOutside());
        assertEquals("29", csvDto.getFurigana());
        assertEquals("30", csvDto.getHihyoji());

    }

}
