package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * CloneRsdtTableTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CloneRsdtTableTaskletTest {

    /** テスト対象 */
    @Autowired
    private CloneRsdtTableTasklet cloneRsdtTableTasklet;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("address_all_city.sql")
    @SuppressWarnings("unchecked")
    void test() throws Exception {

        Query queryDrop = entityManager.createNativeQuery("DROP TABLE IF EXISTS address_rsdt_98989");
        queryDrop.executeUpdate();

        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'test_manage_kanrensha' AND table_name = 'address_rsdt_98989' ";

        Query queryCount = entityManager.createNativeQuery(sql, Integer.class);
        List<Integer> listPre = (List<Integer>) queryCount.getResultList();
        assertEquals(0, listPre.get(0));

        cloneRsdtTableTasklet.execute(null, null);

        List<Integer> listPro = queryCount.getResultList();
        assertEquals(1, listPro.get(0));
    }

}
