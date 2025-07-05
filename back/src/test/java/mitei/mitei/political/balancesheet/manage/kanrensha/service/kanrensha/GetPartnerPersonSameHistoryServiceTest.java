package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;

/**
 * GetPartnerPersonSameHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetPartnerPersonSameHistoryServiceTest {

    /** テスト対象 */
    @Autowired
    private GetPartnerPersonSameHistoryService getPartnerPersonSameHistoryService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_partner_person_history01.sql")
    void test() throws Exception {

        final String name = "迂回献金　太郎";
        final String address = "北海道架空市";
        final String shokugyou = "医師";

        List<PartnerPersonHistoryBaseEntity> list = getPartnerPersonSameHistoryService.practice(name, address,
                shokugyou);

        assertEquals(1, list.size());

        PartnerPersonHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(shokugyou, entity.getPersonShokugyou());

    }

}
