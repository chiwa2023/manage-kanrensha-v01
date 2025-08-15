package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min;

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
 * RetryBatchMasterMinPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RetryBatchMasterMinPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private RetryBatchMasterMinPersonService retryBatchMasterMinPersonService;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "sample_wk_tbl_partner_person_add_min.sql", "delete_hsitory_person.sql", "delete_master_person.sql" })
    void test() {
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> retryBatchMasterMinPersonService.practice(userDto));
    }

}
