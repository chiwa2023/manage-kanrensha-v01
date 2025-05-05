package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.Chunk;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressAllCityEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * AddressAllCityItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class AddressAllCityItemWriterTest {
    // CHECKSTYLE:OFF
    
    /** テスト対象 */
    @Autowired
    private AddressAllCityItemWriter addressAllCityItemWriter;
    
    /** 市区町村テーブルRepository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_address_all_city.sql")
    void test() {
        
        AddressAllCityEntity entity01 = new AddressAllCityEntity();
        entity01.setLgCode("112233");
        entity01.setAddressName("テスト県テスト郡");
        entity01.setAddressNameKana("てすとけんてすとぐん");
        entity01.setEffectDate(LocalDate.of(1948, 7, 29));
        
        List<AddressAllCityEntity> list = new ArrayList<>();
        list.add(entity01);

        // Chunkを作成してセット
        Chunk<? extends AddressAllCityEntity> items = new Chunk<>(list);
        addressAllCityItemWriter.write(items);
        

        List<AddressAllCityEntity> listAnswer = addressAllCityRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        AddressAllCityEntity answerEntity00 = listAnswer.get(0);

        assertEquals(entity01.getLgCode(), answerEntity00.getLgCode());
        assertEquals(entity01.getAddressName(), answerEntity00.getAddressName());
        assertEquals(entity01.getAddressNameKana(), answerEntity00.getAddressNameKana());
        assertEquals(entity01.getEffectDate(), answerEntity00.getEffectDate());
        
    }

}
