package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RetryBatchAddByXmlService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
//すべてのマスタと履歴を削除してバッティングしないようにする
@Sql({ "sample_wk_tbl_master_all_by_xml2.sql", "delete_history_corp01.sql", "delete_history_person01.sql",
        "delete_master_corp.sql", "delete_master_person.sql", "delete_history_poli_org01.sql",
        "delete_master_political_organization.sql" })
class RetryBatchAddByXmlServiceTest {

    /** テスト対象 */
    @Autowired
    private RetryBatchAddByXmlService retryBatchAddByXmlService;

    @Test
    @Tag("TableTruncate")
    void test() {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> retryBatchAddByXmlService.practice(userDto));
    }

}
