package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PartnerCorpHistoryLineMapper単体テスト
 */
class PartnerCorpHistoryLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerCorpHistoryLineMapper lineMapper = new PartnerCorpHistoryLineMapper();

        PartnerCorpHistoryDto dto = lineMapper.mapLine("ちゃらんぽらん政治団体,山形県架空市湖畔町,代表者　太郎,1234-567e", 0);

        assertEquals("ちゃらんぽらん政治団体", dto.getPartnerName());
        assertEquals("山形県架空市湖畔町", dto.getAllAddress());
        assertEquals("代表者　太郎", dto.getCorpDelegate());
        assertEquals("1234-567e", dto.getCorpKanrenshaCode());

    }

}
