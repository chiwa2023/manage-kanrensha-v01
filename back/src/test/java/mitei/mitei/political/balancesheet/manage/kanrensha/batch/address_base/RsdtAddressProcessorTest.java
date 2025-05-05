package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * RsdtAddressProcessor単体テスト
 */
class RsdtAddressProcessorTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        RsdtAddressCsvLineMapper lineMapper = new RsdtAddressCsvLineMapper();
        String line0 = "011053,0013018,017,011,123,札幌市,豊平区,月寒東五条,十八丁目,aaa,bbb,17,11,99,0,1,1,0,2022-11-19,,0,";

        RsdtAddressCsvDto csvDto0 = lineMapper.mapLine(line0, 0);

        RsdtAddressProcessor rsdtAddressProcessor = new RsdtAddressProcessor();

        AddressRsdtBaseEntity baseEntity = rsdtAddressProcessor.process(csvDto0);
        
        assertEquals("011053", baseEntity.getLgCode());
        assertEquals("", baseEntity.getPostalCode()); // 以降の処理で追加
        assertEquals("0013018", baseEntity.getMachiazaId());
        assertEquals("017011123", baseEntity.getParcelRsdtId());
        assertEquals(LocalDate.of(2022,11,19), baseEntity.getEffectDate());
        assertEquals("札幌市豊平区月寒東五条十八丁目aaa17番地11号", baseEntity.getAddressBlock());
        assertEquals("99号室", baseEntity.getAddressBuilding());
    }

}
