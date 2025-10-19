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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;

/**
 * SearchPartnerAllByKanrenshaCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql({ "master_corporation.sql", "master_person.sql", "master_political_organization.sql" })
class SearchPartnerAllByKanrenshaCodeServiceTest {

    /** テスト対象 */
    @Autowired
    private SearchPartnerAllByKanrenshaCodeService searchPartnerAllByKanrenshaCodeService;

    /** 関連者区分個人 */
    private final static short PERSON = 1;

    /** 関連者区分個人 */
    private final static short CORP = 2;

    /** 関連者区分個人 */
    private final static short POLI_ORG = 3;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<PartnerCommonInfoDto> list1 = searchPartnerAllByKanrenshaCodeService.practice("1-2345-ABCCDEF");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        List<PartnerCommonInfoDto> list2 = searchPartnerAllByKanrenshaCodeService.practice("12-345-ABCCDEF");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        List<PartnerCommonInfoDto> list3 = searchPartnerAllByKanrenshaCodeService.practice("123-45-ABCCDEF");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());

    }

}
