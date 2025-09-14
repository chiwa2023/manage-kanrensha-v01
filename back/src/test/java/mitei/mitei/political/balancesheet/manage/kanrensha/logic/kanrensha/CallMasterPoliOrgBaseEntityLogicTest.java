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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;

/**
 * CallMasterPoliOrgBaseEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_master_political_organization_base.sql")
class CallMasterPoliOrgBaseEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallMasterPoliOrgBaseEntityLogic callMasterPoliOrgBaseEntityLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testEmpty() throws Exception {

        // 関連者コードのみ一致する空Entityが返る(マスタに最小登録しかしていない場合)
        final String code = "9-32XZ-BC4fg";
        MasterPoliticalOrganizationBaseEntity baseEntity = callMasterPoliOrgBaseEntityLogic.practice(code);
        assertEquals(code, baseEntity.getPoliOrgKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    void testNowData() throws Exception {

        // 現在使用できる正常データが返る
        final String code = "25G-6Hyrpg98-6g6";
        MasterPoliticalOrganizationBaseEntity baseEntity = callMasterPoliOrgBaseEntityLogic.practice(code);
        assertEquals(801, baseEntity.getMasterPoliticalOrganizationBaseId());
    }

    @Test
    @Tag("TableTruncate")
    void testDataStructureFailure() throws Exception {

        // 現在使用できる最新データが複数ある場合、データの整合性に問題があるのでSEに修正依頼をかける必要がある
        final String code = "4y7-3Gyk46-912e";
        assertThrows(DataRetrievalFailureException.class, () -> callMasterPoliOrgBaseEntityLogic.practice(code));
    }

    @Test
    @Tag("TableTruncate")
    void testHistory() throws Exception {

        // 該当マスタ全体が不使用状態の場合は、不使用になった時点での最終データを返却
        final String code = "6L3-4ke595-6124fd";
        MasterPoliticalOrganizationBaseEntity baseEntity = callMasterPoliOrgBaseEntityLogic.practice(code);
        assertEquals(824, baseEntity.getMasterPoliticalOrganizationBaseId());
    }

}
