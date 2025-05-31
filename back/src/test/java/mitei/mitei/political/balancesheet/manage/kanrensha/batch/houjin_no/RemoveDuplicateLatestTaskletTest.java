package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoLatestRepository;

/**
 * RemoveDuplicateLatestTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RemoveDuplicateLatestTaskletTest {
    // CHECKSTYLE:OFF

    /** 法人番号最新Repository */
    @Autowired
    private HoujinNoLatestRepository houjinNoLatestRepository;

    /** テスト対象 */
    @Autowired
    private RemoveDuplicateLatestTasklet removeDuplicateLatestTasklet;

    @Test
    @Tag("TableTruncate")
    @Sql({ "remove_houjin_no_latest.sql", "remove_houjin_no_history.sql" })
    void test() throws Exception {

        assertEquals(3, houjinNoLatestRepository.count());
        removeDuplicateLatestTasklet.execute(null, null);

        List<HoujinNoLatestEntity> list = houjinNoLatestRepository.findAll();

        assertEquals(2, list.size());
        assertEquals(246, list.get(0).getHoujinNoLatestId());
        assertEquals(247, list.get(1).getHoujinNoLatestId());
    }

}
