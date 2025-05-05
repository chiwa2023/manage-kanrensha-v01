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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * WorksPostalItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WorksPostalItemReaderTest {

    /** テスト対象 */
    @Autowired
    private WorksPostalItemReader worksPostalItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("address_postal_works.sql")
    void test() throws Exception {

        AddressPostalWorksEntity entity00 = worksPostalItemReader.read();
        assertEquals(1, entity00.getAddressPostalWorksId());

        AddressPostalWorksEntity entity01 = worksPostalItemReader.read();
        assertEquals(null, entity01);

    }

}
