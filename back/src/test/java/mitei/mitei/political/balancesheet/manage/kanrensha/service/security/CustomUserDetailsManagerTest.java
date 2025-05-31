package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.LoginStatusEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.LoginHistory2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.LoginStatusRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.LoginHistory2025Repository;

/**
 * CustomUserDetailsManager単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CustomUserDetailsManagerTest {

    /** テスト対象 */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ログイン履歴Repository(2025) */
    @Autowired
    private LoginHistory2025Repository loginHistory2025Repository;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "sample_login_status.sql", "sample_user_role.sql", "delete_login_history_2025.sql" })
    void testLoadUserByUsernameLoginSuccess() {

        // 正常ケース
        String mail = "aaa@politician.balanse.report.net";
        UserDetails userDetails = customUserDetailsManager.loadUserByUsername(mail);

        assertEquals(mail, userDetails.getUsername());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());

        List<GrantedAuthority> listAuthority = new ArrayList<>(userDetails.getAuthorities());

        GrantedAuthority authority0 = listAuthority.get(0);
        assertEquals("ROLE_" + "manager", authority0.getAuthority());

        // ログイン履歴(時刻についてはテスト対象外とする)
        List<LoginHistory2025Entity> list = loginHistory2025Repository.findAll();
        assertEquals(1, list.size());
        LoginHistory2025Entity historyEntity = list.get(0);
        assertEquals("", historyEntity.getDiabledReason());
        assertEquals(false, historyEntity.getDisabled());
        assertEquals(mail, historyEntity.getEmail());
        assertEquals("", historyEntity.getFailReason());
        assertEquals(true, historyEntity.getIsSuccess());

        // TODO 異常ケース アカウント長期活動なし

        // TODO 異常ケース アカウントがロックされている

        // TODO 異常ケース 信頼期間から外れている

        // TODO 異常ケース 使用可否

        fail("Not yet implemented");
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "delete_login_status.sql", "delete_user_role.sql" })
    void testCreateUser() {

        UserDetails userDetails = User.builder().password("111").username("222").build();

        customUserDetailsManager.createUser(userDetails);

        List<LoginStatusEntity> list = loginStatusRepository.findAll();
        assertEquals(1, list.size());
        LoginStatusEntity statusEntity = list.get(0);

        assertEquals("", statusEntity.getDiabledReason());
        assertEquals(false, statusEntity.getDisabled());
        assertEquals(userDetails.getUsername(), statusEntity.getEmail());
        assertEquals("", statusEntity.getFailReason());
        assertEquals(true, statusEntity.getIsSuccess());
        LocalDateTime timeA = LocalDateTime.now().plusSeconds(30L);
        LocalDateTime timeB = LocalDateTime.now().minusSeconds(30);
        assertTrue(statusEntity.getLoginTime().isAfter(timeB));
        assertTrue(statusEntity.getLoginTime().isBefore(timeA));
        assertTrue(statusEntity.getPassChangeTime().isAfter(timeB));
        assertTrue(statusEntity.getPassChangeTime().isBefore(timeA));
        assertEquals(userDetails.getPassword(), statusEntity.getPassword());
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_login_status.sql")
    void testUserExist() {
        // 存在するメールであればtrue
        assertTrue(customUserDetailsManager.userExists("aaa@politician.balanse.report.net"));
    }

}
