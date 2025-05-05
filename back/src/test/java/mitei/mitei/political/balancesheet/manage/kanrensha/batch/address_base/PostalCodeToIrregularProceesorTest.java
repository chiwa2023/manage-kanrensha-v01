package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * PostalCodeToIrregularProceesor単体テスト
 */
class PostalCodeToIrregularProceesorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PostalCodeToIrregularProceesor proceesor = new PostalCodeToIrregularProceesor();

        AddressPostalEntity entity01 = new AddressPostalEntity();
        entity01.setLgCode("112233");
        entity01.setAddressName("テスト市テスト郡");
        entity01.setAddressOrg("テスト郡");
        entity01.setPostal1("1234567");
        entity01.setPostal2("4567");
        entity01.setIsGyoseikuData(true);

        AddressPostalIrregularEntity answerEntity00 = proceesor.process(entity01);

        assertEquals(entity01.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity01.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity01.getAddressOrg(), answerEntity00.getAddressOrg());
        assertEquals(entity01.getPostal1(), answerEntity00.getPostal1());
        assertEquals(entity01.getPostal2(), answerEntity00.getPostal2());

        assertEquals("", answerEntity00.getAddressPostal());
        assertEquals("", answerEntity00.getAddressBlock());

    }

}
