package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ExecuteBatchHistoryPoliOrgService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ExecuteBatchHistoryPoliOrgServiceTest {

    /** テスト対象 */
    @Autowired
    private ExecuteBatchHistoryPoliOrgService executeBatchHistoryPoliOrgService;

    @Test
    @Tag("TableTruncate")
    void test() {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        Path readFilePath = Paths.get("190/test/", "関連者政治団体履歴.csv");
        assertDoesNotThrow(() -> executeBatchHistoryPoliOrgService.practice(readFilePath.toString(), userDto));
    }

}
