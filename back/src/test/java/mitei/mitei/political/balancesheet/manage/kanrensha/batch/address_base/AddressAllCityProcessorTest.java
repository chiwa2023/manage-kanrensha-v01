package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressAllCityEntity;

/**
 * AddressAllCityProcessor単体テスト
 */
class AddressAllCityProcessorTest {
    // CHECKSTYLE:OFF
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        // テスト対象
        AddressAllCityProcessor  processor = new AddressAllCityProcessor();         

        AllCityCsvDto item = new AllCityCsvDto();
        item.setAddressName("住所漢字");
        item.setAddressNameKana("住所かな");
        item.setLgCode("12345");
        item.setEffectDate(LocalDate.of(2023, 11, 13));
        
        AddressAllCityEntity entity = processor.process(item);
        
        assertEquals(item.getAddressName(), entity.getAddressName());
        assertEquals(item.getAddressNameKana(), entity.getAddressNameKana());
        assertEquals(item.getLgCode(), entity.getLgCode());
        assertEquals(item.getEffectDate(), entity.getEffectDate());
        
    }

}
