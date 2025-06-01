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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserNewRepository;

/**
 * PublishNewUserCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PublishNewUserCodeServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PublishNewUserCodeService publishNewUserCodeService;

    /** 新規登録中ユーザRepository */
    @Autowired
    private UserNewRepository userNewRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("delete_user_new.sql")
    void test() {

        NewComerDto requestDto = new NewComerDto();
        requestDto.setMailAddress("aaa@politician.balanse.report.net");
        requestDto.setLimitDateTime(LocalDateTime.of(2022, 12, 15, 12, 34, 56));

        NewComerDto responseDto = publishNewUserCodeService.practice(requestDto);

        // 空にして登録したので1行だけ存在する
        UserNewEntity userNewEntity = userNewRepository.findAll().get(0);
        

        assertEquals(userNewEntity.getEmail(),responseDto.getMailAddress());
        assertEquals(userNewEntity.getRegistCode(),responseDto.getRegistCode());
        assertEquals(userNewEntity.getLimitDatetime(),responseDto.getLimitDateTime());
    }

}
