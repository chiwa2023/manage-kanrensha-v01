package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoLatestRepository;

/**
 * HoujinNoTableItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class HoujinNoTableItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private HoujinNoTableItemWriter houjinNoTableItemWriter;

    /** 法人番号最新Repository */
    @Autowired
    private HoujinNoLatestRepository houjinNoLatestRepository;

    /** 法人番号履歴Repository */
    @Autowired
    private HoujinNoHistoryRepository houjinNoHistoryRepository;

    @Test
    @Tag("TableTruncate")
    @Sql({ "delete_houjin_no_latest.sql", "delete_houjin_no_history.sql" })
    void test() throws Exception { // NOPMD

        // 最新に登録
        HoujinNoLatestEntity entity00 = new HoujinNoLatestEntity();
        entity00.setLatest(true);
        entity00.setCorporateNumber("1010001007380");
        entity00.setProcess("01");
        entity00.setCorrect(true);
        entity00.setUpdateDate(LocalDate.of(2021, 6, 13));
        entity00.setChangeDate(LocalDate.of(2021, 6, 14));
        entity00.setName("株式会社藤原硝子店");
        entity00.setKind("301");
        entity00.setPrefectureName("東京都");
        entity00.setCityName("文京区");
        entity00.setStreetNumber("本郷３丁目８番５号");
        entity00.setPrefectureCode("13");
        entity00.setCityCode("105");
        entity00.setPostCode("1130033");
        entity00.setAddressOutside("ccc");
        entity00.setCloseDate(LocalDate.of(2021, 6, 15));
        entity00.setCloseCause("01");
        entity00.setSuccessorCorporateNumber("1234567890123");
        entity00.setChangeCause("変更理由1");
        entity00.setAssignmentDate(LocalDate.of(2021, 6, 16));
        entity00.setFurigana("フジワラガラステン");
        entity00.setHihyoji(false);

        // 履歴に登録
        HoujinNoLatestEntity entity01 = new HoujinNoLatestEntity();
        entity01.setLatest(false);
        entity01.setCorporateNumber("1010001064562");
        entity01.setProcess("01");
        entity01.setCorrect(true);
        entity01.setUpdateDate(LocalDate.of(2021, 7, 13));
        entity01.setChangeDate(LocalDate.of(2021, 7, 14));
        entity01.setName("プライズ株式会社");
        entity01.setKind("301");
        entity01.setPrefectureName("東京都");
        entity01.setCityName("新宿区");
        entity01.setStreetNumber("神楽坂５丁目２０番地５神楽坂アインスタワー２０４");
        entity01.setPrefectureCode("13");
        entity01.setCityCode("105");
        entity01.setPostCode("1130033");
        entity01.setAddressOutside("dd");
        entity01.setCloseDate(LocalDate.of(2021, 7, 15));
        entity01.setCloseCause("01");
        entity01.setSuccessorCorporateNumber("9876543");
        entity01.setChangeCause("変更理由2");
        entity01.setAssignmentDate(LocalDate.of(2021, 7, 16));
        entity01.setFurigana("プライズ");
        entity01.setHihyoji(false);

        List<HoujinNoLatestEntity> list = new ArrayList<>();
        list.add(entity00);
        list.add(entity01);

        // Chunkを作成してセット
        Chunk<? extends HoujinNoLatestEntity> items = new Chunk<>(list);

        houjinNoTableItemWriter.write(items);

        List<HoujinNoLatestEntity> listLatest = houjinNoLatestRepository.findAll();
        assertEquals(1, listLatest.size());

        HoujinNoLatestEntity entity10 = listLatest.get(0);
        assertEquals(entity00.getCorporateNumber(), entity10.getCorporateNumber());
        assertEquals(entity00.getProcess(), entity10.getProcess());
        assertEquals(entity00.getCorrect(), entity10.getCorrect());
        assertEquals(entity00.getUpdateDate(), entity10.getUpdateDate());
        assertEquals(entity00.getChangeDate(), entity10.getChangeDate());
        assertEquals(entity00.getName(), entity10.getName());
        assertEquals(entity00.getKind(), entity10.getKind());
        assertEquals(entity00.getPrefectureName(), entity10.getPrefectureName());
        assertEquals(entity00.getCityName(), entity10.getCityName());
        assertEquals(entity00.getStreetNumber(), entity10.getStreetNumber());
        assertEquals(entity00.getPrefectureCode(), entity10.getPrefectureCode());
        assertEquals(entity00.getCityCode(), entity10.getCityCode());
        assertEquals(entity00.getPostCode(), entity10.getPostCode());
        assertEquals(entity00.getAddressOutside(), entity10.getAddressOutside());
        assertEquals(entity00.getCloseDate(), entity10.getCloseDate());
        assertEquals(entity00.getCloseCause(), entity10.getCloseCause());
        assertEquals(entity00.getSuccessorCorporateNumber(), entity10.getSuccessorCorporateNumber());
        assertEquals(entity00.getChangeCause(), entity10.getChangeCause());
        assertEquals(entity00.getAssignmentDate(), entity10.getAssignmentDate());
        assertEquals(entity00.getLatest(), entity10.getLatest());
        assertEquals(entity00.getFurigana(), entity10.getFurigana());
        assertEquals(entity00.getHihyoji(), entity10.getHihyoji());
        assertEquals(entity00.getSearchText(), entity10.getSearchText());

        List<HoujinNoHistoryEntity> listHistory = houjinNoHistoryRepository.findAll();
        assertEquals(1, listHistory.size());

        HoujinNoHistoryEntity entity11 = listHistory.get(0);
        assertEquals(entity01.getCorporateNumber(), entity11.getCorporateNumber());
        assertEquals(entity01.getProcess(), entity11.getProcess());
        assertEquals(entity01.getCorrect(), entity11.getCorrect());
        assertEquals(entity01.getUpdateDate(), entity11.getUpdateDate());
        assertEquals(entity01.getChangeDate(), entity11.getChangeDate());
        assertEquals(entity01.getName(), entity11.getName());
        assertEquals(entity01.getKind(), entity11.getKind());
        assertEquals(entity01.getPrefectureName(), entity11.getPrefectureName());
        assertEquals(entity01.getCityName(), entity11.getCityName());
        assertEquals(entity01.getStreetNumber(), entity11.getStreetNumber());
        assertEquals(entity01.getPrefectureCode(), entity11.getPrefectureCode());
        assertEquals(entity01.getCityCode(), entity11.getCityCode());
        assertEquals(entity01.getPostCode(), entity11.getPostCode());
        assertEquals(entity01.getAddressOutside(), entity11.getAddressOutside());
        assertEquals(entity01.getCloseDate(), entity11.getCloseDate());
        assertEquals(entity01.getCloseCause(), entity11.getCloseCause());
        assertEquals(entity01.getSuccessorCorporateNumber(), entity11.getSuccessorCorporateNumber());
        assertEquals(entity01.getChangeCause(), entity11.getChangeCause());
        assertEquals(entity01.getAssignmentDate(), entity11.getAssignmentDate());
        assertEquals(entity01.getLatest(), entity11.getLatest());
        assertEquals(entity01.getFurigana(), entity11.getFurigana());
        assertEquals(entity01.getHihyoji(), entity11.getHihyoji());
    }

}
