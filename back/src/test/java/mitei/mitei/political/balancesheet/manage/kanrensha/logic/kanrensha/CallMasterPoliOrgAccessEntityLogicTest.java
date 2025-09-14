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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;

/**
 * CallMasterPoliOrgAccessEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_master_political_organization_access.sql")
class CallMasterPoliOrgAccessEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallMasterPoliOrgAccessEntityLogic callMasterPoliOrgAccessEntityLogic;
    
    @Test
    @Tag("TableTruncate") // NOPMD
    void testEmpty() throws Exception {

        // 関連者コードのみ一致する空Entityが返る(マスタに最小登録しかしていない場合)
        final String code = "9-32XZ-BC4fg";
        MasterPoliticalOrganizationAccessEntity accessEntity = callMasterPoliOrgAccessEntityLogic.practice(code);
        assertEquals(code, accessEntity.getPoliOrgKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    void testNowData() throws Exception {

        // 現在使用できる正常データが返る
        final String code = "7A8-6pg98-66";
        MasterPoliticalOrganizationAccessEntity accessEntity = callMasterPoliOrgAccessEntityLogic.practice(code);
        assertEquals(601, accessEntity.getMasterPoliticalOrganizationAccessId());
    }

    @Test
    @Tag("TableTruncate")
    void testDataStructureFailure() throws Exception {

        // 現在使用できる最新データが複数ある場合、データの整合性に問題があるのでSEに修正依頼をかける必要がある
        final String code = "3U9-3G46eh5-94";
        assertThrows(DataRetrievalFailureException.class, () -> callMasterPoliOrgAccessEntityLogic.practice(code));
    }

    @Test
    @Tag("TableTruncate")
    void testHistory() throws Exception {

        // 該当マスタ全体が不使用状態の場合は、不使用になった時点での最終データを返却
        final String code = "1H3-4asx95-6L7";
        MasterPoliticalOrganizationAccessEntity accessEntity = callMasterPoliOrgAccessEntityLogic.practice(code);
        assertEquals(624, accessEntity.getMasterPoliticalOrganizationAccessId());
    }

}
