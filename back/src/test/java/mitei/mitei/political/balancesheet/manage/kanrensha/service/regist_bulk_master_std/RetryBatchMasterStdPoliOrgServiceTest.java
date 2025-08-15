package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_std;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RetryBatchMasterStdPoliOrgService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RetryBatchMasterStdPoliOrgServiceTest {

    /** テスト対象 */
    @Autowired
    private RetryBatchMasterStdPoliOrgService retryBatchMasterStdPoliOrgService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "sample_wk_tbl_master_poli_org.sql", "delete_hsitory_poli_org.sql",
            "delete_master_political_organizatin.sql", "delete_master_political_organization_access.sql",
            "delete_master_political_organization_address.sql", "delete_master_political_organization_base.sql",
            "delete_master_political_organization_property.sql" })
    void test() {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> retryBatchMasterStdPoliOrgService.practice(userDto));
    }

}
