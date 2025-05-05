package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * PostalCodeCsvLineMapper単体テスト
 */
class PostalCodeCsvLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeCsvLineMapper lineMapper = new PostalCodeCsvLineMapper();

        // 以下に掲載がない場合
        String line00 = "01101,\"060  \",\"0600000\",\"ホッカイドウ\",\"サッポロシチュウオウク\",\"イカニケイサイガナイバアイ\",\"北海道\",\"札幌市中央区\",\"以下に掲載がない場合\",0,0,0,0,0,0";

        PostalCodeCsvDto csvDto00 = lineMapper.mapLine(line00, 0);

        assertEquals("01101", csvDto00.getLgCode());
        assertEquals("0600000", csvDto00.getPostal1());
        assertEquals("0000", csvDto00.getPostal2());
        assertEquals("以下に掲載がない場合", csvDto00.getAddressOrg());
        assertEquals("札幌市中央区", csvDto00.getAddressName());
        assertEquals(true, csvDto00.getIsGyoseikuData());

        // （注釈がついていて、別途データ整備が必要
        String line01 = "01101,\"060  \",\"0600005\",\"ホッカイドウ\",\"サッポロシチュウオウク\",\"キタ５ジョウニシ（１−２４チョウメ）\",\"北海道\",\"札幌市中央区\",\"北五条西（１〜２４丁目）\",1,0,1,0,0,0";

        PostalCodeCsvDto csvDto01 = lineMapper.mapLine(line01, 0);

        assertEquals("01101", csvDto01.getLgCode());
        assertEquals("0600005", csvDto01.getPostal1());
        assertEquals("0005", csvDto01.getPostal2());
        assertEquals("北五条西（１〜２４丁目）", csvDto01.getAddressOrg());
        assertEquals("札幌市中央区北五条西", csvDto01.getAddressName());
        assertEquals(false, csvDto01.getIsGyoseikuData());

        // 一般的な処理
        String line02 = "01101,\"060  \",\"0600035\",\"ホッカイドウ\",\"サッポロシチュウオウク\",\"キタ５ジョウヒガシ\",\"北海道\",\"札幌市中央区\",\"北五条東\",0,0,1,0,0,0";

        PostalCodeCsvDto csvDto02 = lineMapper.mapLine(line02, 0);

        assertEquals("01101", csvDto02.getLgCode());
        assertEquals("0600035", csvDto02.getPostal1());
        assertEquals("0035", csvDto02.getPostal2());
        assertEquals("北五条東", csvDto02.getAddressOrg());
        assertEquals("札幌市中央区北五条東", csvDto02.getAddressName());
        assertEquals(true, csvDto02.getIsGyoseikuData());

    }

}
