package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PartnerPersonHistoryLineMapper単体テスト
 */
class PartnerPersonHistoryLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerPersonHistoryLineMapper lineMapper = new PartnerPersonHistoryLineMapper();

        PartnerPersonHistoryDto dto = lineMapper.mapLine("\"迂回献金　太郎\",\"和歌山県架空市実在町\",\"経営者\",\"1-2345-ABCCDEF\"", 0);

        assertEquals("迂回献金　太郎", dto.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto.getAllAddress());
        assertEquals("経営者", dto.getPersonShokugyou());
        assertEquals("1-2345-ABCCDEF", dto.getPersonKanrenshaCode());

    }

}
