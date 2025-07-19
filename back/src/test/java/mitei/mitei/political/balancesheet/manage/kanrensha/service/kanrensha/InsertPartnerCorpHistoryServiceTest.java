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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerCorpHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory99Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory02Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory03Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory04Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory05Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory06Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory07Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory08Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory09Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory10Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory11Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory12Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory13Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory14Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory15Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory16Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory17Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory18Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory19Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory20Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory21Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory22Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory23Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory24Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory25Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory26Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory27Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory28Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory29Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory30Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory31Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory32Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory33Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory34Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory35Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory36Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory37Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory38Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory39Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory40Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory41Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory42Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory43Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory44Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory46Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory47Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory99Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * InsertPartnerCorpHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("delete_partner_corp_history.sql")
class InsertPartnerCorpHistoryServiceTest { // NOPMD

    /** テスト対象 */
    @Autowired
    private InsertPartnerCorpHistoryService insertPartnerCorpHistoryService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerCorpHistory01Repository partnerCorpHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private PartnerCorpHistory02Repository partnerCorpHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private PartnerCorpHistory03Repository partnerCorpHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private PartnerCorpHistory04Repository partnerCorpHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private PartnerCorpHistory05Repository partnerCorpHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private PartnerCorpHistory06Repository partnerCorpHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private PartnerCorpHistory07Repository partnerCorpHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private PartnerCorpHistory08Repository partnerCorpHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private PartnerCorpHistory09Repository partnerCorpHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private PartnerCorpHistory10Repository partnerCorpHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private PartnerCorpHistory11Repository partnerCorpHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private PartnerCorpHistory12Repository partnerCorpHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private PartnerCorpHistory13Repository partnerCorpHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private PartnerCorpHistory14Repository partnerCorpHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private PartnerCorpHistory15Repository partnerCorpHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private PartnerCorpHistory16Repository partnerCorpHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private PartnerCorpHistory17Repository partnerCorpHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private PartnerCorpHistory18Repository partnerCorpHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private PartnerCorpHistory19Repository partnerCorpHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private PartnerCorpHistory20Repository partnerCorpHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private PartnerCorpHistory21Repository partnerCorpHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private PartnerCorpHistory22Repository partnerCorpHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private PartnerCorpHistory23Repository partnerCorpHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private PartnerCorpHistory24Repository partnerCorpHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private PartnerCorpHistory25Repository partnerCorpHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private PartnerCorpHistory26Repository partnerCorpHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private PartnerCorpHistory27Repository partnerCorpHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private PartnerCorpHistory28Repository partnerCorpHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private PartnerCorpHistory29Repository partnerCorpHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private PartnerCorpHistory30Repository partnerCorpHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private PartnerCorpHistory31Repository partnerCorpHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private PartnerCorpHistory32Repository partnerCorpHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private PartnerCorpHistory33Repository partnerCorpHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private PartnerCorpHistory34Repository partnerCorpHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private PartnerCorpHistory35Repository partnerCorpHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private PartnerCorpHistory36Repository partnerCorpHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private PartnerCorpHistory37Repository partnerCorpHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private PartnerCorpHistory38Repository partnerCorpHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private PartnerCorpHistory39Repository partnerCorpHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private PartnerCorpHistory40Repository partnerCorpHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private PartnerCorpHistory41Repository partnerCorpHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private PartnerCorpHistory42Repository partnerCorpHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private PartnerCorpHistory43Repository partnerCorpHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private PartnerCorpHistory44Repository partnerCorpHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private PartnerCorpHistory45Repository partnerCorpHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private PartnerCorpHistory46Repository partnerCorpHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private PartnerCorpHistory47Repository partnerCorpHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private PartnerCorpHistory99Repository partnerCorpHistory99Repository;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合"); // NOPMD
        baseEntity.setAllAddress("北海道実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子"); // NOPMD
        baseEntity.setCorpKanrenshaCode("123-4567"); // NOPMD
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory01Entity> listAns = partnerCorpHistory01Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory01Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());

    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("青森県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory02Entity> listAns = partnerCorpHistory02Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory02Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("岩手県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory03Entity> listAns = partnerCorpHistory03Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory03Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("宮城県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory04Entity> listAns = partnerCorpHistory04Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory04Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("秋田県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory05Entity> listAns = partnerCorpHistory05Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory05Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("山形県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory06Entity> listAns = partnerCorpHistory06Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory06Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("福島県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory07Entity> listAns = partnerCorpHistory07Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory07Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("茨城県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory08Entity> listAns = partnerCorpHistory08Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory08Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("栃木県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory09Entity> listAns = partnerCorpHistory09Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory09Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("群馬県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory10Entity> listAns = partnerCorpHistory10Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory10Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("埼玉県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory11Entity> listAns = partnerCorpHistory11Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory11Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("千葉県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory12Entity> listAns = partnerCorpHistory12Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory12Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("東京都実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory13Entity> listAns = partnerCorpHistory13Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory13Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("神奈川県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory14Entity> listAns = partnerCorpHistory14Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory14Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("新潟県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory15Entity> listAns = partnerCorpHistory15Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory15Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("富山県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory16Entity> listAns = partnerCorpHistory16Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory16Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("石川県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory17Entity> listAns = partnerCorpHistory17Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory17Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("福井県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory18Entity> listAns = partnerCorpHistory18Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory18Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("山梨県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory19Entity> listAns = partnerCorpHistory19Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory19Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("長野県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory20Entity> listAns = partnerCorpHistory20Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory20Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("岐阜県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory21Entity> listAns = partnerCorpHistory21Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory21Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("静岡県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory22Entity> listAns = partnerCorpHistory22Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory22Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("愛知県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory23Entity> listAns = partnerCorpHistory23Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory23Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("三重県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory24Entity> listAns = partnerCorpHistory24Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory24Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("滋賀県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory25Entity> listAns = partnerCorpHistory25Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory25Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("京都府実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory26Entity> listAns = partnerCorpHistory26Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory26Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("大阪府実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory27Entity> listAns = partnerCorpHistory27Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory27Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("兵庫県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory28Entity> listAns = partnerCorpHistory28Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory28Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("奈良県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory29Entity> listAns = partnerCorpHistory29Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory29Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("和歌山県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory30Entity> listAns = partnerCorpHistory30Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory30Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("鳥取県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory31Entity> listAns = partnerCorpHistory31Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory31Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("島根県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory32Entity> listAns = partnerCorpHistory32Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory32Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("岡山県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory33Entity> listAns = partnerCorpHistory33Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory33Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("広島県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory34Entity> listAns = partnerCorpHistory34Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory34Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("山口県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory35Entity> listAns = partnerCorpHistory35Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory35Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("徳島県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory36Entity> listAns = partnerCorpHistory36Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory36Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("香川県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory37Entity> listAns = partnerCorpHistory37Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory37Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("愛媛県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory38Entity> listAns = partnerCorpHistory38Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory38Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("高知県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory39Entity> listAns = partnerCorpHistory39Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory39Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("福岡県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory40Entity> listAns = partnerCorpHistory40Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory40Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("佐賀県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory41Entity> listAns = partnerCorpHistory41Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory41Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("長崎県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory42Entity> listAns = partnerCorpHistory42Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory42Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("熊本県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory43Entity> listAns = partnerCorpHistory43Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory43Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("大分県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory44Entity> listAns = partnerCorpHistory44Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory44Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("宮崎県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory45Entity> listAns = partnerCorpHistory45Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory45Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("鹿児島県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory46Entity> listAns = partnerCorpHistory46Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory46Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("沖縄県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory47Entity> listAns = partnerCorpHistory47Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory47Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        PartnerCorpHistoryBaseEntity baseEntity = new PartnerCorpHistoryBaseEntity();
        baseEntity.setPartnerName("超元素製造組合");
        baseEntity.setAllAddress("青県実在市湖畔町");
        baseEntity.setCorpDelegate("組合長　花子");
        baseEntity.setCorpKanrenshaCode("123-4567");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerCorpHistoryService.practice(userDto, baseEntity);

        List<PartnerCorpHistory99Entity> listAns = partnerCorpHistory99Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerCorpHistory99Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

}
