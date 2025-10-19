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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;

/**
 * CallMasterPoliOrgPropertyEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_master_political_organization_property.sql")
class CallMasterPoliOrgPropertyEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallMasterPoliOrgPropertyEntityLogic callMasterPoliOrgPropertyEntityLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testEmpty() throws Exception {

        // 関連者コードのみ一致する空Entityが返る(マスタに最小登録しかしていない場合)
        final String code = "9-32XZ-BC4fg";
        MasterPoliticalOrganizationPropertyEntity propertyEntity = callMasterPoliOrgPropertyEntityLogic.practice(code);
        assertEquals(code, propertyEntity.getPoliOrgKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    void testNowData() throws Exception {

        // 現在使用できる正常データが返る
        final String code = "19f-6e65jsp-6in6";
        MasterPoliticalOrganizationPropertyEntity propertyEntity = callMasterPoliOrgPropertyEntityLogic.practice(code);
        assertEquals(901, propertyEntity.getMasterPoliticalOrganizationPropertyId());
    }

    @Test
    @Tag("TableTruncate")
    void testDataStructureFailure() throws Exception {

        // 現在使用できる最新データが複数ある場合、データの整合性に問題があるのでSEに修正依頼をかける必要がある
        final String code = "2U4-58Z46eh5-965uht";
        assertThrows(DataRetrievalFailureException.class, () -> callMasterPoliOrgPropertyEntityLogic.practice(code));
    }

    @Test
    @Tag("TableTruncate")
    void testHistory() throws Exception {

        // 該当マスタ全体が不使用状態の場合は、不使用になった時点での最終データを返却
        final String code = "7A5-476da5-6gr346";
        MasterPoliticalOrganizationPropertyEntity propertyEntity = callMasterPoliOrgPropertyEntityLogic.practice(code);
        assertEquals(924, propertyEntity.getMasterPoliticalOrganizationPropertyId());
    }

}
