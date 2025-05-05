package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

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
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalWorksRepository;

/**
 * CleanWorksAddressPostalTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CleanWorksAddressPostalTaskletTest {

    /** テスト対象 */
    @Autowired
    private CleanWorksAddressPostalTasklet cleanWorksAddressPostalTasklet;

    /** 郵便番号作業Repository */
    @Autowired
    private AddressPostalWorksRepository addressPostalWorksRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("address_postal_works.sql")
    void test()throws Exception {
        
        // 処理前はsqlから登録した1件
        assertEquals(1, addressPostalWorksRepository.count());
        
        cleanWorksAddressPostalTasklet.execute(null, null);

        // 処理後無条件に削除完了
        assertEquals(0, addressPostalWorksRepository.count());
    }

}
