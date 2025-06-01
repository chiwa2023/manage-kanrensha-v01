package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.LoginStatusEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.LoginHistory2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.LoginHistory2025Repository;

/**
 * CopyLoginStatusHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CopyLoginStatusHistoryServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyLoginStatusHistoryService copyLoginStatusHistoryService;

    /** ログイン履歴Repository(2025) */
    @Autowired
    private LoginHistory2025Repository loginHistory2025Repository;

    @Test
    @Tag("TableTruncate")
    @Sql("../../logic/year/y2025/delete_login_history_2025.sql")
    void test() {

        LoginStatusEntity statusEntity = new LoginStatusEntity();
        statusEntity.setDiabledReason("1111");
        statusEntity.setDisabled(true);
        statusEntity.setEmail("2222");
        statusEntity.setFailReason("333");
        statusEntity.setIsSuccess(false);
        statusEntity.setLoginTime(LocalDateTime.of(2022, 12, 5, 12, 34, 56));
        statusEntity.setPassChangeTime(LocalDateTime.of(2023, 9, 18, 1, 23, 45));
        statusEntity.setPassword("4444");

        copyLoginStatusHistoryService.practice(statusEntity, 2025);

        List<LoginHistory2025Entity> list = loginHistory2025Repository.findAll();
        assertEquals(1, list.size());

        LoginHistory2025Entity historyEntity = list.get(0);

        assertEquals(statusEntity.getDiabledReason(), historyEntity.getDiabledReason());
        assertEquals(statusEntity.getDisabled(), historyEntity.getDisabled());
        assertEquals(statusEntity.getEmail(), historyEntity.getEmail());
        assertEquals(statusEntity.getFailReason(), historyEntity.getFailReason());
        assertEquals(statusEntity.getIsSuccess(), historyEntity.getIsSuccess());
        assertEquals(statusEntity.getLoginTime(), historyEntity.getLoginTime());
        assertEquals(statusEntity.getPassChangeTime(), historyEntity.getPassChangeTime());
    }

}
