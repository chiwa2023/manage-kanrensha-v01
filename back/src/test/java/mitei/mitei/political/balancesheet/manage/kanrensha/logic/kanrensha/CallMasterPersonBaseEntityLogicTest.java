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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * CallMasterPersonBaseEntityLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_master_person_base.sql")
class CallMasterPersonBaseEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallMasterPersonBaseEntityLogic callMasterPersonBaseEntityLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testEmpty() throws Exception {
        // データが存在しない場合、コードのみ設定された空のEntityが返る
        final String code = "code-not-exist";
        MasterPersonBaseEntity baseEntity = callMasterPersonBaseEntityLogic.practice(code);
        assertEquals(code, baseEntity.getPersonKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    void testNowData() throws Exception {
        // is_latest=trueが1件の場合、そのEntityが返る
        final String code = "code-normal";
        MasterPersonBaseEntity baseEntity = callMasterPersonBaseEntityLogic.practice(code);
        assertEquals(1, baseEntity.getMasterPersonBaseId());
    }

    @Test
    @Tag("TableTruncate")
    void testDataStructureFailure() throws Exception {
        // is_latest=trueが複数件の場合、例外がスローされる
        final String code = "code-duplicate";
        assertThrows(DataRetrievalFailureException.class, () -> callMasterPersonBaseEntityLogic.practice(code));
    }

    @Test
    @Tag("TableTruncate")
    void testHistory() throws Exception {
        // is_latest=trueが0件の場合、IDが最も大きいEntityが返る
        final String code = "code-history";
        MasterPersonBaseEntity baseEntity = callMasterPersonBaseEntityLogic.practice(code);
        assertEquals(6, baseEntity.getMasterPersonBaseId());
    }

}
