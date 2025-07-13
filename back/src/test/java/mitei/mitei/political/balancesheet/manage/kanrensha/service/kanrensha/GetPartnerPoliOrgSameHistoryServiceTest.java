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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;

/**
 * GetPartnerPoliOrgSameHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql( "sample_partner_poli_org_history.sql")
class GetPartnerPoliOrgSameHistoryServiceTest {

    /** テスト対象 */
    @Autowired
    private GetPartnerPoliOrgSameHistoryService getPartnerPoliOrgSameHistoryService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_partner_poli_org_history01.sql")
    void test() throws Exception {

        final String name = "ちゃらんぽらん政治団体";
        final String address = "北海道架空市";
        final String delegate = "代表者　太郎";

        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address,
                delegate);

        assertEquals(1, list.size());
    }
    
    void test01() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "北海道架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

}
