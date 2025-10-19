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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;

/**
 * CallMasterCorpAddressEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_master_corporation_address.sql")
class CallMasterCorpAddressEntityLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CallMasterCorpAddressEntityLogic callMasterCorpAddressEntityLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testEmpty() throws Exception {

        // 関連者コードのみ一致する空Entityが返る(マスタに最小登録しかしていない場合)
        final String code = "9-32XZ-BC4fg";
        MasterCorporationAddressEntity addressEntity = callMasterCorpAddressEntityLogic.practice(code);
        assertEquals(code, addressEntity.getCorpKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    void testNowData() throws Exception {

        // 現在使用できる正常データが返る
        final String code = "7-6adf-676";
        MasterCorporationAddressEntity addressEntity = callMasterCorpAddressEntityLogic.practice(code);
        assertEquals(2201, addressEntity.getMasterCorporationAddressId());
    }

    @Test
    @Tag("TableTruncate")
    void testDataStructureFailure() throws Exception {

        // 現在使用できる最新データが複数ある場合、データの整合性に問題があるのでSEに修正依頼をかける必要がある
        final String code = "4-9yeh5-94";
        assertThrows(DataRetrievalFailureException.class, () -> callMasterCorpAddressEntityLogic.practice(code));
    }

    @Test
    @Tag("TableTruncate")
    void testHistory() throws Exception {

        // 該当マスタ全体が不使用状態の場合は、不使用になった時点での最終データを返却
        final String code = "8-512itv-6L7";
        MasterCorporationAddressEntity addressEntity = callMasterCorpAddressEntityLogic.practice(code);
        assertEquals(2224, addressEntity.getMasterCorporationAddressId());
    }

}
