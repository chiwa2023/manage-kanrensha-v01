package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.NewComerDto;

/**
 * CheckNewUserCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckNewUserCodeServiceTest {

    /** テスト対象 */
    @Autowired
    private CheckNewUserCodeService checkNewUserCodeService;

    /** コード発行Service */
    @Autowired
    private PublishNewUserCodeService publishNewUserCodeService;

    @Test
    @Tag("TableTruncate")
    @Sql("delete_user_new.sql")
    void test() {

        NewComerDto requestDto = new NewComerDto();
        requestDto.setMailAddress("aaa@politician.balanse.report.net");
        requestDto.setLimitDateTime(LocalDateTime.now().plusDays(1L));

        NewComerDto publishDto = publishNewUserCodeService.practice(requestDto);

        NewComerDto responseDto = checkNewUserCodeService.practice(publishDto);

        assertEquals(publishDto.getMailAddress(), responseDto.getMailAddress());
        assertEquals(publishDto.getRegistCode(), responseDto.getRegistCode());
        assertEquals(publishDto.getLimitDateTime(), responseDto.getLimitDateTime());
        
        assertEquals(true,responseDto.getIsSuccess());
    }

}
