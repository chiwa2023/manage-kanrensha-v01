package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
 * SelectPostalCodeOtherItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SelectPostalCodeOtherItemWriterTest {

    /** テスト対象 */
    @Autowired
    private SelectPostalCodeOtherItemWriter selectPostalCodeOtherItemWriter;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号作業Repository */
    @Autowired
    private AddressPostalWorksRepository addressPostalWorksRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_address_postal_works.sql")
    void test() {

        AddressPostalWorksEntity worksEntity00 = new AddressPostalWorksEntity();
        worksEntity00.setLgCode("011061");
        worksEntity00.setAddressName("札幌市南区真駒内");
        worksEntity00.setAddressOrg("真駒内（その他）"); // 処理には不要だが、どの郵便番号の操作をしているかを明記するため
        worksEntity00.setPostal1("0050861");

        List<AddressPostalWorksEntity> list = new ArrayList<>();
        list.add(worksEntity00);
        Chunk<? extends AddressPostalWorksEntity> items = new Chunk<>(list);

        // 処理前は住居テーブルを参照しない設定
        AddressPostalEntity postalPreEntity = addressPostalRepository
                .findByPostal1OrderByAddressNameAsc(worksEntity00.getPostal1()).get(0);
        assertFalse(postalPreEntity.getIsGyoseikuData());

        selectPostalCodeOtherItemWriter.write(items);

        List<AddressPostalWorksEntity> listWorks = addressPostalWorksRepository.findAll();
        assertEquals(1, listWorks.size());
        AddressPostalWorksEntity worksEntity = list.get(0);
        assertEquals(worksEntity00.getLgCode(), worksEntity.getLgCode());
        assertEquals(worksEntity00.getAddressName(), worksEntity.getAddressName());
        assertEquals(worksEntity00.getAddressOrg(), worksEntity.getAddressOrg());
        assertEquals(worksEntity00.getPostal1(), worksEntity.getPostal1());

        // 処理後は住居テーブルを参照する設定に変更
        AddressPostalEntity postalProEntity = addressPostalRepository
                .findByPostal1OrderByAddressNameAsc(worksEntity00.getPostal1()).get(0);
        assertTrue(postalProEntity.getIsGyoseikuData());

    }

}
