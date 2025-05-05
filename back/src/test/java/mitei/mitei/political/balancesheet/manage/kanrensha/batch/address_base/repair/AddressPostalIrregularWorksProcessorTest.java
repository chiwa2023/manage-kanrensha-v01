package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * AddressPostalIrregularWorksProcessor単体テスト
 */
class AddressPostalIrregularWorksProcessorTest {
    // CHECKSTYLE:OFF
    
    @Test
    void test()throws Exception {
        
        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        entity.setLgCode("123123");
        entity.setPostal1("9876543");
        entity.setPostal2("6543");
        entity.setAddressOrg("山麓町（字小山、字大山）");
        entity.setAddressName("架空市山麓町");
        entity.setAddressPostal("宮崎県架空市山麓町");
        entity.setAddressBlock("字小山１９５番地３");

        entity.setAddressPostalIrregularId(1859);
        entity.setIsAddPostal(true);
        entity.setIsRepairRsdt(false);

        
        AddressPostalIrregularWorksProcessor worksProcessor = new AddressPostalIrregularWorksProcessor();
        
        AddressPostalWorksEntity worksEntity = worksProcessor.process(entity);
        
        assertEquals(entity.getLgCode(), worksEntity.getLgCode());
        assertEquals(entity.getPostal1(), worksEntity.getPostal1());
        assertEquals(entity.getPostal2(), worksEntity.getPostal2());
        assertEquals(entity.getAddressOrg(), worksEntity.getAddressOrg());
        assertEquals(entity.getAddressName(), worksEntity.getAddressName());
        assertEquals(entity.getAddressPostal(), worksEntity.getAddressPostal());
        assertEquals(entity.getAddressBlock(), worksEntity.getAddressBlock());
        assertEquals(entity.getAddressPostalIrregularId(), worksEntity.getAddressPostalIrregularId());
        assertEquals(entity.getIsAddPostal(), worksEntity.getIsAddPostal());
        assertEquals(entity.getIsRepairRsdt(), worksEntity.getIsRepairRsdt());
    }

}
