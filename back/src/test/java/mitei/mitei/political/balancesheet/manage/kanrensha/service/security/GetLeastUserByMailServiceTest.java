package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;

/**
 * GetLeastUserByMailService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetLeastUserByMailServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetLeastUserByMailService getLeastUserByMailService;

    /** 認証プロバイダ */
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Test
    @Tag("TableTruncate")
    @Sql({ "sample_user_person.sql", "sample_login_status.sql", "sample_user_role.sql" })
    void test() {

        final String mail = "aaa@politician.balanse.report.net";
        final String pass = "qwerty1234";

        Authentication authentication = daoAuthenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(mail, pass));

        UserPersonLeastDto leastDto = getLeastUserByMailService.practice(mail, authentication);

        assertEquals(89, leastDto.getUserPersonId());
        assertEquals(81, leastDto.getUserPersonCode());
        assertEquals("たろー", leastDto.getUserPersonName());

        List<String> listAuth = leastDto.getListRoles();
        assertEquals(1, listAuth.size());
        assertEquals("ROLE_manager", listAuth.get(0));

    }

}
