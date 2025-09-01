package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory99Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory02Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory03Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory04Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory05Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory06Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory07Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory08Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory09Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory10Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory11Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory12Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory13Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory14Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory15Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory16Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory17Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory18Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory19Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory20Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory21Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory22Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory23Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory24Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory25Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory26Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory27Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory28Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory29Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory30Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory31Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory32Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory33Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory34Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory35Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory36Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory37Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory38Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory39Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory40Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory41Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory42Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory43Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory44Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory46Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory47Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory99Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * InsertPartnerPersonHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("delete_partner_person_history.sql")
class InsertPartnerPersonHistoryServiceTest { // NOPMD

    /** テスト対象 */
    @Autowired
    private InsertPartnerPersonHistoryService insertPartnerPersonHistoryService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private PartnerPersonHistory02Repository partnerPersonHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private PartnerPersonHistory03Repository partnerPersonHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private PartnerPersonHistory04Repository partnerPersonHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private PartnerPersonHistory05Repository partnerPersonHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private PartnerPersonHistory06Repository partnerPersonHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private PartnerPersonHistory07Repository partnerPersonHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private PartnerPersonHistory08Repository partnerPersonHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private PartnerPersonHistory09Repository partnerPersonHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private PartnerPersonHistory10Repository partnerPersonHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private PartnerPersonHistory11Repository partnerPersonHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private PartnerPersonHistory12Repository partnerPersonHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private PartnerPersonHistory13Repository partnerPersonHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private PartnerPersonHistory14Repository partnerPersonHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private PartnerPersonHistory15Repository partnerPersonHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private PartnerPersonHistory16Repository partnerPersonHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private PartnerPersonHistory17Repository partnerPersonHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private PartnerPersonHistory18Repository partnerPersonHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private PartnerPersonHistory19Repository partnerPersonHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private PartnerPersonHistory20Repository partnerPersonHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private PartnerPersonHistory21Repository partnerPersonHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private PartnerPersonHistory22Repository partnerPersonHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private PartnerPersonHistory23Repository partnerPersonHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private PartnerPersonHistory24Repository partnerPersonHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private PartnerPersonHistory25Repository partnerPersonHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private PartnerPersonHistory26Repository partnerPersonHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private PartnerPersonHistory27Repository partnerPersonHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private PartnerPersonHistory28Repository partnerPersonHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private PartnerPersonHistory29Repository partnerPersonHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private PartnerPersonHistory30Repository partnerPersonHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private PartnerPersonHistory31Repository partnerPersonHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private PartnerPersonHistory32Repository partnerPersonHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private PartnerPersonHistory33Repository partnerPersonHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private PartnerPersonHistory34Repository partnerPersonHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private PartnerPersonHistory35Repository partnerPersonHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private PartnerPersonHistory36Repository partnerPersonHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private PartnerPersonHistory37Repository partnerPersonHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private PartnerPersonHistory38Repository partnerPersonHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private PartnerPersonHistory39Repository partnerPersonHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private PartnerPersonHistory40Repository partnerPersonHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private PartnerPersonHistory41Repository partnerPersonHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private PartnerPersonHistory42Repository partnerPersonHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private PartnerPersonHistory43Repository partnerPersonHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private PartnerPersonHistory44Repository partnerPersonHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private PartnerPersonHistory45Repository partnerPersonHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private PartnerPersonHistory46Repository partnerPersonHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private PartnerPersonHistory47Repository partnerPersonHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private PartnerPersonHistory99Repository partnerPersonHistory99Repository;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎"); // NOPMD
        baseEntity.setAllAddress("北海道実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567"); // NOPMD
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory01Entity> listAns = partnerPersonHistory01Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory01Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());

    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("青森県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory02Entity> listAns = partnerPersonHistory02Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory02Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("岩手県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory03Entity> listAns = partnerPersonHistory03Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory03Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("宮城県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory04Entity> listAns = partnerPersonHistory04Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory04Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("秋田県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory05Entity> listAns = partnerPersonHistory05Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory05Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("山形県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory06Entity> listAns = partnerPersonHistory06Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory06Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("福島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory07Entity> listAns = partnerPersonHistory07Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory07Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("茨城県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory08Entity> listAns = partnerPersonHistory08Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory08Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("栃木県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory09Entity> listAns = partnerPersonHistory09Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory09Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("群馬県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory10Entity> listAns = partnerPersonHistory10Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory10Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("埼玉県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory11Entity> listAns = partnerPersonHistory11Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory11Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("千葉県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory12Entity> listAns = partnerPersonHistory12Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory12Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("東京都実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory13Entity> listAns = partnerPersonHistory13Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory13Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("神奈川県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory14Entity> listAns = partnerPersonHistory14Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory14Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("新潟県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory15Entity> listAns = partnerPersonHistory15Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory15Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("富山県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory16Entity> listAns = partnerPersonHistory16Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory16Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("石川県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory17Entity> listAns = partnerPersonHistory17Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory17Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("福井県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory18Entity> listAns = partnerPersonHistory18Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory18Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("山梨県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory19Entity> listAns = partnerPersonHistory19Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory19Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("長野県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory20Entity> listAns = partnerPersonHistory20Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory20Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("岐阜県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory21Entity> listAns = partnerPersonHistory21Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory21Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("静岡県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory22Entity> listAns = partnerPersonHistory22Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory22Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("愛知県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory23Entity> listAns = partnerPersonHistory23Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory23Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("三重県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory24Entity> listAns = partnerPersonHistory24Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory24Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("滋賀県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory25Entity> listAns = partnerPersonHistory25Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory25Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("京都府実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory26Entity> listAns = partnerPersonHistory26Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory26Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("大阪府実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory27Entity> listAns = partnerPersonHistory27Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory27Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("兵庫県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory28Entity> listAns = partnerPersonHistory28Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory28Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("奈良県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory29Entity> listAns = partnerPersonHistory29Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory29Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("和歌山県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory30Entity> listAns = partnerPersonHistory30Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory30Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("鳥取県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory31Entity> listAns = partnerPersonHistory31Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory31Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("島根県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory32Entity> listAns = partnerPersonHistory32Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory32Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("岡山県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory33Entity> listAns = partnerPersonHistory33Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory33Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("広島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory34Entity> listAns = partnerPersonHistory34Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory34Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("山口県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory35Entity> listAns = partnerPersonHistory35Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory35Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("徳島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory36Entity> listAns = partnerPersonHistory36Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory36Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("香川県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory37Entity> listAns = partnerPersonHistory37Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory37Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("愛媛県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory38Entity> listAns = partnerPersonHistory38Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory38Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("高知県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory39Entity> listAns = partnerPersonHistory39Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory39Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("福岡県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory40Entity> listAns = partnerPersonHistory40Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory40Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("佐賀県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory41Entity> listAns = partnerPersonHistory41Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory41Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("長崎県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory42Entity> listAns = partnerPersonHistory42Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory42Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("熊本県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory43Entity> listAns = partnerPersonHistory43Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory43Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("大分県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory44Entity> listAns = partnerPersonHistory44Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory44Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("宮崎県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory45Entity> listAns = partnerPersonHistory45Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory45Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("鹿児島県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory46Entity> listAns = partnerPersonHistory46Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory46Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("沖縄県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory47Entity> listAns = partnerPersonHistory47Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory47Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        PartnerPersonHistoryBaseEntity baseEntity = new PartnerPersonHistoryBaseEntity();
        baseEntity.setPartnerName("迂回献金　太郎");
        baseEntity.setAllAddress("青県実在市湖畔町");
        baseEntity.setPersonShokugyou("教師");
        baseEntity.setPersonKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPersonHistoryService.practice(userDto, baseEntity);

        List<PartnerPersonHistory99Entity> listAns = partnerPersonHistory99Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPersonHistory99Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

}
