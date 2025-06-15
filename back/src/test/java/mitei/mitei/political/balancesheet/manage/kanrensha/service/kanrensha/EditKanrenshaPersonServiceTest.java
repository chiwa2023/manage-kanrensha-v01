package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * EditKanrenshaPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EditKanrenshaPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private EditKanrenshaPersonService editKanrenshaPersonService;

    @Test
    void test() {

        editKanrenshaPersonService.practice(null);
        fail("Not yet implemented");
    }

}
