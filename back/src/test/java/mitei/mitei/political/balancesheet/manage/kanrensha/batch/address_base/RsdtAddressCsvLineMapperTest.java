package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * RsdtAddressCsvLineMapper単体テスト
 */
class RsdtAddressCsvLineMapperTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        RsdtAddressCsvLineMapper lineMapper = new RsdtAddressCsvLineMapper();
        String line0 = "011053,0013018,017,011,123,札幌市,豊平区,月寒東五条,十八丁目,aaa,bbb,17,11,99,0,1,1,0,2022-11-19,,0,";

        RsdtAddressCsvDto csvDto0 = lineMapper.mapLine(line0, 0);

        assertEquals("011053", csvDto0.getLgCode());
        assertEquals("0013018", csvDto0.getMachiazaId());
        assertEquals("017", csvDto0.getBlkId());
        assertEquals("011", csvDto0.getRsdtId());
        assertEquals("123", csvDto0.getRsdt2Id());
        assertEquals("札幌市", csvDto0.getCity());
        assertEquals("豊平区", csvDto0.getWard());
        assertEquals("月寒東五条", csvDto0.getOazaCho());
        assertEquals("十八丁目", csvDto0.getChome());
        assertEquals("aaa", csvDto0.getKoaza());
        assertEquals("bbb", csvDto0.getMachiazaDist());
        assertEquals("17", csvDto0.getBlkNum());
        assertEquals("11", csvDto0.getRsdtNum());
        assertEquals("99", csvDto0.getRsdtNum2());
        assertEquals(0, csvDto0.getBasicRsdtDiv());
        assertEquals(1, csvDto0.getRsdtAddrFlg());
        assertEquals(0, csvDto0.getStatusFlg());
        assertEquals(LocalDate.of(2022, 11, 19), csvDto0.getEffectDate());
        assertEquals(LocalDate.of(1948, 7, 29), csvDto0.getAbolitionDate());

        // 全項目空対応
        String line1 = ",,,,,,,,,,,,,,,,,,,,0,";

        RsdtAddressCsvDto csvDto1 = lineMapper.mapLine(line1, 0);

        assertEquals("", csvDto1.getLgCode());
        assertEquals("", csvDto1.getMachiazaId());
        assertEquals("", csvDto1.getBlkId());
        assertEquals("", csvDto1.getRsdtId());
        assertEquals("", csvDto1.getRsdt2Id());
        assertEquals("", csvDto1.getCity());
        assertEquals("", csvDto1.getWard());
        assertEquals("", csvDto1.getOazaCho());
        assertEquals("", csvDto1.getChome());
        assertEquals("", csvDto1.getKoaza());
        assertEquals("", csvDto1.getMachiazaDist());
        assertEquals("", csvDto1.getBlkNum());
        assertEquals("", csvDto1.getRsdtNum());
        assertEquals("", csvDto1.getRsdtNum2());
        assertEquals(0, csvDto1.getBasicRsdtDiv());
        assertEquals(0, csvDto1.getRsdtAddrFlg());
        assertEquals(0, csvDto1.getStatusFlg());
        assertEquals(LocalDate.of(1948, 7, 29), csvDto1.getEffectDate());
        assertEquals(LocalDate.of(1948, 7, 29), csvDto1.getAbolitionDate());

    }

}
