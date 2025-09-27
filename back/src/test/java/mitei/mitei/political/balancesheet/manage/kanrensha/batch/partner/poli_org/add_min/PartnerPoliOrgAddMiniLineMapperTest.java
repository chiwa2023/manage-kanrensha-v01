package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PartnerPoliOrgAddMiniLineMapper単体テスト
 */
class PartnerPoliOrgAddMiniLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerPoliOrgAddMiniLineMapper lineMapper = new PartnerPoliOrgAddMiniLineMapper();
        PartnerPoliOrgAddMiniDto dto = lineMapper.mapLine("\"ちゃらんぽらん政治団体\",\"和歌山県架空市実在町\",\"代表者　太郎\",\"05\"", 0);

        assertEquals("ちゃらんぽらん政治団体", dto.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto.getAllAddress());
        assertEquals("代表者　太郎", dto.getPoliOrgDelegate());
        assertEquals("05", dto.getDantaiKbn());

    }

}
