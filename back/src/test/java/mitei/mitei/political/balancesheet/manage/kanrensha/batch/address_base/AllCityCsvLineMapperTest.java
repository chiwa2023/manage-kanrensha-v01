package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * AllCityCsvLineMapper単体テスト
 */
class AllCityCsvLineMapperTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AllCityCsvLineMapper lineMapper = new AllCityCsvLineMapper();

        String line = "112233,2,3,4,5,6,7,8,9,10,11,12,13,2022-11-12,14";

        AllCityCsvDto dto = lineMapper.mapLine(line, 0);

        assertEquals("25811", dto.getAddressName());
        assertEquals("36912", dto.getAddressNameKana());
        assertEquals("112233", dto.getLgCode());
        assertEquals(LocalDate.of(2022, 11, 12), dto.getEffectDate());
    }

}
