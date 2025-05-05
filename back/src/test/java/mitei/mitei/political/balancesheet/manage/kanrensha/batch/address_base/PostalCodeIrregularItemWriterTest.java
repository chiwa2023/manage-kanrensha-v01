package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * PostalCodeIrregularItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeIrregularItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeIrregularItemWriter postalCodeIrregularItemWriter;

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("delete_address_postal_irregular.sql")
    void test() {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        entity.setLgCode("123123");
        entity.setPostal1("9876543");
        entity.setPostal2("6543");
        entity.setAddressOrg("山麓町（字小山、字大山）");
        entity.setAddressName("架空市山麓町");
        entity.setAddressPostal("宮崎県架空市山麓町");
        entity.setAddressBlock("字小山１９５番地３");

        List<AddressPostalIrregularEntity> list = new ArrayList<>();
        list.add(entity);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalIrregularEntity> items = new Chunk<>(list);
        postalCodeIrregularItemWriter.write(items);

        List<AddressPostalIrregularEntity> listAnswer = addressPostalIrregularRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        AddressPostalIrregularEntity answerEntity00 = listAnswer.get(0);

        assertEquals(entity.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity.getPostal1(), answerEntity00.getPostal1());
        assertEquals(entity.getPostal2(), answerEntity00.getPostal2());
        assertEquals(entity.getAddressOrg(), answerEntity00.getAddressOrg());
        assertEquals(entity.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity.getAddressPostal(), answerEntity00.getAddressPostal());
        assertEquals(entity.getAddressBlock(), answerEntity00.getAddressBlock());
    }

}
