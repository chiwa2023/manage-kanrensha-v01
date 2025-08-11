package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

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

/**
 * ForceDumpStdMasterSabunPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("master_person.sql")
class ForceDumpStdMasterSabunPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ForceDumpStdMasterSabunPersonService forceDumpStdMasterSabunPersonService;

    @Test
    @Tag("TableTruncate")
    void test() {

        assertDoesNotThrow(() -> forceDumpStdMasterSabunPersonService.practice(LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1)));
    }

}
