package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PartnerPersonAddMiniLineMapper単体テスト
 */
class PartnerPersonAddMiniLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerPersonAddMiniLineMapper lineMapper = new PartnerPersonAddMiniLineMapper();
        PartnerPersonAddMiniDto dto = lineMapper.mapLine("\"迂回献金　太郎\",\"和歌山県架空市実在町\",\"経営者\"", 0);

        assertEquals("迂回献金　太郎", dto.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto.getAllAddress());
        assertEquals("経営者", dto.getPersonShokugyou());
    }

}
