package mitei.mitei.political.balancesheet.manage.kanrensha.service.security; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.LoginStatusEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserRoleEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.LoginHistory2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.LoginStatusRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserRoleRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.LoginHistory2025Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CustomUserDetailsManager単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CustomUserDetailsManagerTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ログイン履歴Repository(2025) */
    @Autowired
    private LoginHistory2025Repository loginHistory2025Repository;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    @Transactional
    @Tag("TableTruncate") // NOPMD
    @Sql({ "sample_login_status.sql", "sample_user_role.sql", "delete_login_history_2025.sql" }) // NOPMD
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

    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "sample_login_status.sql", "sample_user_role.sql", "delete_login_history_2025.sql" })
    void testFailLogin() {

        // 存在しないユーザ(NoSuchElementExceptionを出して親でキャッチする)
        assertThrows(NoSuchElementException.class,
                () -> customUserDetailsManager.loadUserByUsername("aa@politician.balanse.report.net"));

        UserDetails userDetails11 = customUserDetailsManager.loadUserByUsername("bbb@politician.balanse.report.net");
        // 信頼期間から外れている
        assertFalse(userDetails11.isCredentialsNonExpired());
        // アカウント長期活動なし
        assertFalse(userDetails11.isAccountNonExpired());
        // 使用可否
        assertFalse(userDetails11.isEnabled());

        // TODO アカウントがロックは現状使っていないが、必要ならば実装する
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
    void testDeleteUser() {

        String email = "aaa@politician.balanse.report.net";

        customUserDetailsManager.deleteUser(email);

        Optional<LoginStatusEntity> optional = loginStatusRepository.findById(email);
        assertFalse(optional.isEmpty());
        LoginStatusEntity entity = optional.get();

        assertTrue(entity.getDisabled());
        assertEquals("人為による退会操作", entity.getDiabledReason());

        // 存在しないユーザを指定されてもとりあえず落ちない
        assertDoesNotThrow(() -> customUserDetailsManager.deleteUser("abcde"));
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "sample_login_status.sql", "sample_user_person.sql", "sample_user_role.sql",
            "delete_login_history_2025.sql" })
    void testDeleteUserPerson() {

        UserPersonLeastDto operateUserDto = CreateLeastUserForTestUtil.practice();
        // 存在しないユーザを処理しようとするとfalseが戻る
        assertFalse(customUserDetailsManager.deleteUser(operateUserDto, operateUserDto));

        final int personId = 89;

        UserPersonLeastDto deleteUserDto = new UserPersonLeastDto();
        deleteUserDto.setUserPersonId(personId); // idとemailに整合性があればOK
        deleteUserDto.setUserPersonCode(81);
        deleteUserDto.setUserPersonName("たろー");

        // 存在するユーザであれば正常処理
        assertTrue(customUserDetailsManager.deleteUser(deleteUserDto, operateUserDto));
        UserPersonEntity userPersonEntity = userPersonRepository.findById(personId).get();
        assertFalse(userPersonEntity.getIsLatest());
        assertEquals(operateUserDto.getUserPersonId(), userPersonEntity.getDeleteUserId());
        assertEquals(operateUserDto.getUserPersonCode(), userPersonEntity.getDeleteUserCode());
        assertEquals(operateUserDto.getUserPersonName(), userPersonEntity.getDeleteUserName());

        String email = userPersonEntity.getEmail();
        LoginStatusEntity loginStatusEntity = loginStatusRepository.findById(email).get();
        assertTrue(loginStatusEntity.getDisabled());

        List<UserRoleEntity> list0 = userRoleRepository.findByIsLatestAndEmail(true, email);
        assertTrue(list0.isEmpty());

        List<UserRoleEntity> list1 = userRoleRepository.findByIsLatestAndEmail(false, email);
        assertEquals(1, list1.size());

        UserRoleEntity entity0 = list1.get(0);
        assertFalse(entity0.getIsLatest());
        assertEquals(operateUserDto.getUserPersonId(), entity0.getDeleteUserId());
        assertEquals(operateUserDto.getUserPersonCode(), entity0.getDeleteUserCode());
        assertEquals(operateUserDto.getUserPersonName(), entity0.getDeleteUserName());
    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_login_status.sql")
    void testChangePassword() {

        // パスワード更新できたらtrue
        // 実際はパスワードはencodeしたものを挿入するが、テストクラスでpasswordEncodeを@Autowiredしようとすると
        // SecurityConfigと循環参照を起こしてしまうので、できないため
        // 実装内容としてはencode、encodeなし関係なし
        assertTrue(customUserDetailsManager.changePassword("ccc@politician.balanse.report.net", "12345", "67890"));

        // パスワード更新ができなかったときは理由にかかわらずfalseを返す
        // ユーザが存在しない
        assertFalse(customUserDetailsManager.changePassword("cc@politician.balanse.report.net", "12345", "67890"));
        // 旧パスワードが誤り※新たに更新した67890と異なる
        assertFalse(customUserDetailsManager.changePassword("ccc@politician.balanse.report.net", "99999", "67890"));
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
