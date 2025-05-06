package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;

/**
 * HoujinNoCsvEntityProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class HoujinNoCsvEntityProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private HoujinNoCsvEntityProcessor houjinNoCsvEntityProcessor;
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {

        HoujinNoCsvDto csvDto = new HoujinNoCsvDto();

        csvDto.setSequenceNumber("1");
        csvDto.setCorporateNumber("1010001007380");
        csvDto.setProcess("01");
        csvDto.setCorrect("1");
        csvDto.setUpdateDate("2025-04-14");
        csvDto.setChangeDate("2015-10-05");
        csvDto.setName("株式会社藤原硝子店");
        csvDto.setNameImageId("aa");
        csvDto.setKind("301");
        csvDto.setPrefectureName("東京都");
        csvDto.setCityName("文京区");
        csvDto.setStreetNumber("本郷３丁目８番５号");
        csvDto.setAddressImageId("bbb");
        csvDto.setPrefectureCode("13");
        csvDto.setCityCode("105");
        csvDto.setPostCode("1130033");
        csvDto.setAddressOutside("ccc");
        csvDto.setAddressOutsideImageId("ddd");
        csvDto.setCloseDate("2016-09-28");
        csvDto.setCloseCause("閉鎖理由");
        csvDto.setSuccessorCorporateNumber("1234567890123");
        csvDto.setChangeCause("変更理由");
        csvDto.setAssignmentDate("2015-10-05");
        csvDto.setLatest("1");
        csvDto.setEnName("english1");
        csvDto.setEnPrefectureName("english2");
        csvDto.setEnCityName("english2");
        csvDto.setEnAddressOutside("english4");
        csvDto.setFurigana("フジワラガラステン");
        csvDto.setHihyoji("0");
        
        HoujinNoLatestEntity entity = houjinNoCsvEntityProcessor.process(csvDto);
        
        assertEquals(csvDto.getCorporateNumber(), entity.getCorporateNumber());
        assertEquals(csvDto.getProcess(), entity.getProcess());
        assertEquals(true, entity.getCorrect());
        assertEquals(LocalDate.of(2025, 4, 14), entity.getUpdateDate());
        assertEquals(LocalDate.of(2015, 10, 5), entity.getChangeDate());
        assertEquals(csvDto.getName(), entity.getName());
        assertEquals(csvDto.getKind(), entity.getKind());
        assertEquals(csvDto.getPrefectureName(), entity.getPrefectureName());
        assertEquals(csvDto.getCityName(), entity.getCityName());
        assertEquals(csvDto.getStreetNumber(), entity.getStreetNumber());
        assertEquals(csvDto.getPrefectureCode(), entity.getPrefectureCode());
        assertEquals(csvDto.getCityCode(), entity.getCityCode());
        assertEquals(csvDto.getPostCode(), entity.getPostCode());
        assertEquals(csvDto.getAddressOutside(), entity.getAddressOutside());
        assertEquals(LocalDate.of(2016, 9, 28), entity.getCloseDate());
        assertEquals(csvDto.getCloseCause(), entity.getCloseCause());
        assertEquals(csvDto.getSuccessorCorporateNumber(), entity.getSuccessorCorporateNumber());
        assertEquals(csvDto.getChangeCause(), entity.getChangeCause());
        assertEquals(LocalDate.of(2015, 10, 5), entity.getAssignmentDate());
        assertEquals(true, entity.getLatest());
        assertEquals(csvDto.getFurigana(), entity.getFurigana());
        assertEquals(false, entity.getHihyoji());
        assertEquals("株式会社藤原硝子店東京都文京区本郷3丁目8番5号フジワラガラステン", entity.getSearchText());

    }

}
