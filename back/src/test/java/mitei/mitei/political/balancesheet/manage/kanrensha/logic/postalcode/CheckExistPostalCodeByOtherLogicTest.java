package mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * ChceckExistPostalCodeByOtherLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckExistPostalCodeByOtherLogicTest {

    /** テスト対象 */
    @Autowired
    private CheckExistPostalCodeByOtherLogic checkExistPostalCodeByOtherLogic;
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        AddressPostalWorksEntity worksEntity00 = new AddressPostalWorksEntity();
        worksEntity00.setLgCode("011061");
        worksEntity00.setAddressName("札幌市南区真駒内");
        worksEntity00.setAddressOrg("真駒内（その他）"); // 処理には不要だが、どの郵便番号の操作をしているかを明記するため
        worksEntity00.setPostal1("0050861"); 

        List<AddressPostalEntity> list = checkExistPostalCodeByOtherLogic.practice(worksEntity00);
        assertEquals(1, list.size());
        AddressPostalEntity postalEntity0 = list.get(0);
        assertEquals("0050861",postalEntity0.getPostal1());
        assertEquals("真駒内（その他）",postalEntity0.getAddressOrg());

        
        AddressPostalWorksEntity worksEntity01 = new AddressPostalWorksEntity();
        worksEntity01.setLgCode("011061");
        worksEntity01.setAddressName("札幌市北区真駒内"); // 処理ミスでデータが狂った
        worksEntity01.setAddressOrg("真駒内（その他）"); // 処理には不要だが、どの郵便番号の操作をしているかを明記するため
        worksEntity01.setPostal1("0050861"); 
        List<AddressPostalEntity> list1 = checkExistPostalCodeByOtherLogic.practice(worksEntity01);

        assertTrue(list1.isEmpty());
    }
}
