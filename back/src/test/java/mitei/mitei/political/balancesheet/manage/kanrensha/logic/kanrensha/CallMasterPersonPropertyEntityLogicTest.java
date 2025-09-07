package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;

/**
 * CallMasterPersonPropertyEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_master_person_property.sql")
class CallMasterPersonPropertyEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallMasterPersonPropertyEntityLogic callMasterPersonPropertyEntityLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testEmpty() throws Exception {
        // データが存在しない場合、コードのみ設定された空のEntityが返る
        final String code = "code-not-exist";
        MasterPersonPropertyEntity propertyEntity = callMasterPersonPropertyEntityLogic.practice(code);
        assertEquals(code, propertyEntity.getPersonKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    void testNowData() throws Exception {
        // is_latest=trueが1件の場合、そのEntityが返る
        final String code = "code-normal";
        MasterPersonPropertyEntity propertyEntity = callMasterPersonPropertyEntityLogic.practice(code);
        assertEquals(1, propertyEntity.getMasterPersonPropertyId());
    }

    @Test
    @Tag("TableTruncate")
    void testDataStructureFailure() throws Exception {
        // is_latest=trueが複数件の場合、例外がスローされる
        final String code = "code-duplicate";
        assertThrows(DataRetrievalFailureException.class, () -> callMasterPersonPropertyEntityLogic.practice(code));
    }

    @Test
    @Tag("TableTruncate")
    void testHistory() throws Exception {
        // is_latest=trueが0件の場合、IDが最も大きいEntityが返る
        final String code = "code-history";
        MasterPersonPropertyEntity propertyEntity = callMasterPersonPropertyEntityLogic.practice(code);
        assertEquals(6, propertyEntity.getMasterPersonPropertyId());
    }

}
