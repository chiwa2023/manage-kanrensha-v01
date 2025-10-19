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
 * ForceDumpHistoryPoliOrgService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "partner_poli_org_history_01.sql", "partner_poli_org_history_02.sql", "partner_poli_org_history_03.sql",
        "partner_poli_org_history_04.sql", "partner_poli_org_history_05.sql", "partner_poli_org_history_06.sql",
        "partner_poli_org_history_07.sql", "partner_poli_org_history_08.sql", "partner_poli_org_history_09.sql",
        "partner_poli_org_history_10.sql", "partner_poli_org_history_11.sql", "partner_poli_org_history_12.sql",
        "partner_poli_org_history_13.sql", "partner_poli_org_history_14.sql", "partner_poli_org_history_15.sql",
        "partner_poli_org_history_16.sql", "partner_poli_org_history_16.sql", "partner_poli_org_history_17.sql",
        "partner_poli_org_history_18.sql", "partner_poli_org_history_19.sql", "partner_poli_org_history_20.sql",
        "partner_poli_org_history_21.sql", "partner_poli_org_history_22.sql", "partner_poli_org_history_23.sql",
        "partner_poli_org_history_24.sql", "partner_poli_org_history_25.sql", "partner_poli_org_history_26.sql",
        "partner_poli_org_history_27.sql", "partner_poli_org_history_28.sql", "partner_poli_org_history_29.sql",
        "partner_poli_org_history_30.sql", "partner_poli_org_history_31.sql", "partner_poli_org_history_32.sql",
        "partner_poli_org_history_33.sql", "partner_poli_org_history_34.sql", "partner_poli_org_history_35.sql",
        "partner_poli_org_history_36.sql", "partner_poli_org_history_37.sql", "partner_poli_org_history_38.sql",
        "partner_poli_org_history_39.sql", "partner_poli_org_history_40.sql", "partner_poli_org_history_41.sql",
        "partner_poli_org_history_42.sql", "partner_poli_org_history_43.sql", "partner_poli_org_history_44.sql",
        "partner_poli_org_history_45.sql", "partner_poli_org_history_46.sql", "partner_poli_org_history_47.sql",
        "partner_poli_org_history_99.sql" })
class ForceDumpHistoryPoliOrgServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ForceDumpHistoryPoliOrgService forceDumpHistoryPoliOrgService;

    @Test
    @Tag("TableTruncate")
    void test() {
        assertDoesNotThrow(() -> forceDumpHistoryPoliOrgService.practice(LocalDate.of(2024, 1, 1)));
    }

}
