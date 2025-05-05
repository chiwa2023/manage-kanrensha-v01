package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalWorksRepository;

/**
 * ChoicePostalCodeIrregularItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ChoicePostalCodeIrregularItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ChoicePostalCodeIrregularItemWriter choicePostalCodeIrregularItemWriter;

    /** 郵便番号作業Repository */
    @Autowired
    private AddressPostalWorksRepository addressPostalWorksRepository;

    /** 郵便番号作業Repository */
    @Autowired
    private AddressPostalRepository addressPRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    // @Sql({"delete_address_postal_works.sql","address_postal.sql"})
    @Sql("delete_address_postal_works.sql")
    void test() {


        // 番地、丁目が２種入っているデータは住所リストを見ながらしないと修正できないので自動修正しない
        AddressPostalWorksEntity worksEntity01 = new AddressPostalWorksEntity();
        worksEntity01.setAddressOrg("門静（４丁目５５〜１１４番地）");

        // 編集対象
        AddressPostalWorksEntity worksEntity06 = new AddressPostalWorksEntity();
        String postal = "0741271";

        worksEntity06.setAddressPostalIrregularId(185);
        worksEntity06.setPostal1(postal);
        worksEntity06.setPostal2("1271");
        worksEntity06.setLgCode("012289");
        worksEntity06.setAddressOrg("広里町（１〜５丁目）");
        worksEntity06.setAddressName("深川市広里町");
        worksEntity06.setAddressPostal("");
        worksEntity06.setAddressBlock("");
        worksEntity06.setIsAddPostal(true);
        worksEntity06.setIsRepairRsdt(false);

        List<AddressPostalWorksEntity> list = new ArrayList<>();
        list.add(worksEntity01);
        list.add(worksEntity06);

        // 事前に同一郵便番号件数を確認
        int countPre = addressPRepository.findByPostal1OrderByAddressNameAsc(postal).size();
        assertEquals(2, countPre, "作業前件数2");

        // Chunkを作成してセット
        Chunk<? extends AddressPostalWorksEntity> items = new Chunk<>(list);
        choicePostalCodeIrregularItemWriter.write(items);
        
        // 郵便番号は7件にデータが増えている
        List<AddressPostalEntity> listPro = addressPRepository.findByPostal1OrderByAddressNameAsc(postal);
        assertEquals(7, listPro.size(), "作業前件数+5");
        
        AddressPostalEntity postalEntity0 = listPro.get(0);
        assertEquals(postal, postalEntity0.getPostal1());
        assertEquals("深川市広里町", postalEntity0.getAddressName());
        
        AddressPostalEntity postalEntity1 = listPro.get(1);
        assertEquals(postal, postalEntity1.getPostal1());
        assertEquals("深川市広里町一丁目", postalEntity1.getAddressName());
        
        AddressPostalEntity postalEntity2 = listPro.get(2);
        assertEquals(postal, postalEntity2.getPostal1());
        assertEquals("深川市広里町三丁目", postalEntity2.getAddressName());
        
        AddressPostalEntity postalEntity3 = listPro.get(3);
        assertEquals(postal, postalEntity3.getPostal1());
        assertEquals("深川市広里町二丁目", postalEntity3.getAddressName());
        
        AddressPostalEntity postalEntity4 = listPro.get(4);
        assertEquals(postal, postalEntity4.getPostal1());
        assertEquals("深川市広里町五丁目", postalEntity4.getAddressName());
        
        AddressPostalEntity postalEntity5 = listPro.get(5);
        assertEquals(postal, postalEntity5.getPostal1());
        assertEquals("深川市広里町四丁目", postalEntity5.getAddressName());

        AddressPostalEntity postalEntity6 = listPro.get(6);
        assertEquals(postal, postalEntity6.getPostal1());
        assertEquals("深川市音江町", postalEntity6.getAddressName());

        // 2件渡したが、1件は範囲複写対象外のため1件だけ登録がされた
        List<AddressPostalWorksEntity> listAnswer = addressPostalWorksRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        AddressPostalWorksEntity worksEntity = listAnswer.get(0);
        
        assertEquals(worksEntity06.getAddressPostalIrregularId(),worksEntity.getAddressPostalIrregularId());
        assertEquals(worksEntity06.getPostal1(),worksEntity.getPostal1());
        assertEquals(worksEntity06.getPostal2(),worksEntity.getPostal2());
        assertEquals(worksEntity06.getLgCode(),worksEntity.getLgCode());
        assertEquals(worksEntity06.getAddressOrg(),worksEntity.getAddressOrg());
        assertEquals(worksEntity06.getAddressName(),worksEntity.getAddressName());
        assertEquals(worksEntity06.getAddressPostal(),worksEntity.getAddressPostal());
        assertEquals(worksEntity06.getAddressBlock(),worksEntity.getAddressBlock());
        assertEquals(worksEntity06.getIsAddPostal(),worksEntity.getIsAddPostal());
        assertEquals(worksEntity06.getIsRepairRsdt(),worksEntity.getIsRepairRsdt());
        
    }

}
