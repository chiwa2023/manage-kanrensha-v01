package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;

/**
 * PostalCodeIrregularItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeIrregularItemReaderTest {

    /** テスト対象 */ 
    @Autowired
    private PostalCodeIrregularItemReader postalCodeIrregularItemReader;
    
    @Test
    @Tag("TableTruncate")
    @Sql("address_postal.sql")
    void test()throws Exception {
        
        // 最初に取得できる不規則データ
        AddressPostalEntity entity =   postalCodeIrregularItemReader.read();
        assertEquals("0600042", entity.getPostal1());
        assertEquals("01101", entity.getLgCode());
    }

}
