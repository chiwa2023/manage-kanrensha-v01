package mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * SelectSingleAddressLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SelectSingleAddressLogicTest {

    /** テスト対象 */
    @Autowired
    private SelectSingleAddressLogic selectSingleAddressLogic;
    
    @Test
    @Tag("TableTruncate")
    void test() {
        
        AddressPostalWorksEntity worksEntity00 = new AddressPostalWorksEntity();
        worksEntity00.setLgCode("012351");
        worksEntity00.setAddressName("石狩市八幡町");
        worksEntity00.setAddressOrg("八幡町（五の沢）");
        worksEntity00.setPostal1("0613480"); 

        List<AddressPostalEntity> list00 = selectSingleAddressLogic.practice(worksEntity00);
        assertEquals(1, list00.size());
        
        // 郵便番号0613480の呼び出し住所(address_name)が石狩市八幡町から石狩市八幡町五の沢に変更、かつ住居データに参照可能に
        AddressPostalEntity entityPostal00 = list00.get(0);
        assertEquals("0613480", entityPostal00.getPostal1());
        assertEquals("石狩市八幡町五の沢", entityPostal00.getAddressName());
        assertEquals(true, entityPostal00.getIsGyoseikuData());
        
        
        
        AddressPostalWorksEntity worksEntity01 = new AddressPostalWorksEntity();
        worksEntity01.setLgCode("012351");
        worksEntity01.setAddressName("札幌市八幡町"); // 編集中にデータが破損した
        worksEntity01.setAddressOrg("八幡町（五の沢）");
        worksEntity01.setPostal1("0613480"); 

        List<AddressPostalEntity> list01 = selectSingleAddressLogic.practice(worksEntity01);
        assertEquals(0, list01.size());
    }

}
