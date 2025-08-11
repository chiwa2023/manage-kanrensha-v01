package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PartnerPoliOrgHistoryLineMapper単体テスト
 */
class PartnerPoliOrgHistoryLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerPoliOrgHistoryLineMapper lineMapper = new PartnerPoliOrgHistoryLineMapper();

        PartnerPoliOrgHistoryDto dto = lineMapper.mapLine("ちゃらんぽらん政治団体,山形県架空市湖畔町,代表者　太郎,1234-567e,9876", 0);

        assertEquals("ちゃらんぽらん政治団体", dto.getPartnerName());
        assertEquals("山形県架空市湖畔町", dto.getAllAddress());
        assertEquals("代表者　太郎", dto.getPoliOrgDelegate());
        assertEquals("1234-567e", dto.getPoliOrgKanrenshaCode());
        assertEquals("9876", dto.getOrgDelegateCode());
    }

}
