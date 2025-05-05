package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;

/**
 * PostalCodeNormalEntityProcessor単体テスト
 */
class PostalCodeNormalEntityProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeNormalEntityProcessor processor = new PostalCodeNormalEntityProcessor();

        PostalCodeCsvDto csvDto = new PostalCodeCsvDto();

        csvDto.setLgCode("01101");
        csvDto.setPostal1("0897342");
        csvDto.setPostal2("7342");
        csvDto.setAddressOrg("山麓町");
        csvDto.setAddressName("実在市山麓町");
        csvDto.setIsGyoseikuData(true);

        AddressPostalEntity entity = processor.process(csvDto);

        assertEquals(csvDto.getLgCode()+"1", entity.getLgCode());
        assertEquals(csvDto.getPostal1(), entity.getPostal1());
        assertEquals(csvDto.getPostal2(), entity.getPostal2());
        assertEquals(csvDto.getAddressOrg(), entity.getAddressOrg());
        assertEquals(csvDto.getAddressName(), entity.getAddressName());
        assertEquals(csvDto.getIsGyoseikuData(), entity.getIsGyoseikuData());

    }

}
