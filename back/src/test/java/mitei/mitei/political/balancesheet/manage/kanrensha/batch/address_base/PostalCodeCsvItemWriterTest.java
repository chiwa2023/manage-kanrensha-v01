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
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * PostalCodeCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeCsvItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeCsvItemWriter postalCodeCsvItemWriter;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_address_postal.sql")
    void test() {

        AddressPostalEntity entity01 = new AddressPostalEntity();
        entity01.setLgCode("112233");
        entity01.setAddressName("テスト市テスト郡");
        entity01.setAddressOrg("テスト郡");
        entity01.setPostal1("1234567");
        entity01.setPostal2("4567");
        entity01.setIsGyoseikuData(true);

        List<AddressPostalEntity> list = new ArrayList<>();
        list.add(entity01);

        // Chunkを作成してセット
        Chunk<? extends AddressPostalEntity> items = new Chunk<>(list);
        postalCodeCsvItemWriter.write(items);

        List<AddressPostalEntity> listAnswer = addressPostalRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        AddressPostalEntity answerEntity00 = listAnswer.get(0);

        assertEquals(entity01.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity01.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity01.getAddressOrg(), answerEntity00.getAddressOrg());
        assertEquals(entity01.getPostal1(), answerEntity00.getPostal1());
        assertEquals(entity01.getPostal2(), answerEntity00.getPostal2());
        assertEquals(entity01.getIsGyoseikuData(), answerEntity00.getIsGyoseikuData());

    }

}
