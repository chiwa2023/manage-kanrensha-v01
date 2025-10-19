package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PartnerCorpAddDMiniLineMapper単体テスト
 */
class PartnerCorpAddMiniLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerCorpAddMiniLineMapper lineMapper = new PartnerCorpAddMiniLineMapper();
        PartnerCorpAddMiniDto dto = lineMapper.mapLine("\"ぼったくり企業\",\"和歌山県架空市実在町\",\"代表者　太郎\",\"1233444\"", 0);

        assertEquals("ぼったくり企業", dto.getPartnerName());
        assertEquals("和歌山県架空市実在町", dto.getAllAddress());
        assertEquals("代表者　太郎", dto.getCorpDelegate());
        assertEquals("1233444", dto.getHoujinNo());

        // 法人番号は、とりあえず今後扱いやすいようにハイフン除去のNormalize(正常かどうかは別途判定する)
        PartnerCorpAddMiniDto dto1 = lineMapper.mapLine("\"ぼったくり企業\",\"和歌山県架空市実在町\",\"代表者　太郎\",\"１－2-\"", 0);
        assertEquals("12", dto1.getHoujinNo());

    }

}
