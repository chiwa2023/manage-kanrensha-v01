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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory99Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory02Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory03Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory04Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory05Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory06Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory07Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory08Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory09Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory10Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory11Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory12Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory13Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory14Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory15Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory16Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory17Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory18Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory19Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory20Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory21Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory22Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory23Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory24Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory25Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory26Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory27Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory28Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory29Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory30Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory31Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory32Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory33Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory34Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory35Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory36Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory37Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory38Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory39Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory40Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory41Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory42Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory43Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory44Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory46Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory47Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory99Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * InsertPartnerPoliOrgHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("delete_partner_poli_org_history.sql")
class InsertPartnerPoliOrgHistoryServiceTest { // NOPMD

    /** テスト対象 */
    @Autowired
    private InsertPartnerPoliOrgHistoryService insertPartnerPoliOrgHistoryService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private PartnerPoliOrgHistory02Repository partnerPoliOrgHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private PartnerPoliOrgHistory03Repository partnerPoliOrgHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private PartnerPoliOrgHistory04Repository partnerPoliOrgHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private PartnerPoliOrgHistory05Repository partnerPoliOrgHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private PartnerPoliOrgHistory06Repository partnerPoliOrgHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private PartnerPoliOrgHistory07Repository partnerPoliOrgHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private PartnerPoliOrgHistory08Repository partnerPoliOrgHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private PartnerPoliOrgHistory09Repository partnerPoliOrgHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private PartnerPoliOrgHistory10Repository partnerPoliOrgHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private PartnerPoliOrgHistory11Repository partnerPoliOrgHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private PartnerPoliOrgHistory12Repository partnerPoliOrgHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private PartnerPoliOrgHistory13Repository partnerPoliOrgHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private PartnerPoliOrgHistory14Repository partnerPoliOrgHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private PartnerPoliOrgHistory15Repository partnerPoliOrgHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private PartnerPoliOrgHistory16Repository partnerPoliOrgHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private PartnerPoliOrgHistory17Repository partnerPoliOrgHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private PartnerPoliOrgHistory18Repository partnerPoliOrgHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private PartnerPoliOrgHistory19Repository partnerPoliOrgHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private PartnerPoliOrgHistory20Repository partnerPoliOrgHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private PartnerPoliOrgHistory21Repository partnerPoliOrgHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private PartnerPoliOrgHistory22Repository partnerPoliOrgHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private PartnerPoliOrgHistory23Repository partnerPoliOrgHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private PartnerPoliOrgHistory24Repository partnerPoliOrgHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private PartnerPoliOrgHistory25Repository partnerPoliOrgHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private PartnerPoliOrgHistory26Repository partnerPoliOrgHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private PartnerPoliOrgHistory27Repository partnerPoliOrgHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private PartnerPoliOrgHistory28Repository partnerPoliOrgHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private PartnerPoliOrgHistory29Repository partnerPoliOrgHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private PartnerPoliOrgHistory30Repository partnerPoliOrgHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private PartnerPoliOrgHistory31Repository partnerPoliOrgHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private PartnerPoliOrgHistory32Repository partnerPoliOrgHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private PartnerPoliOrgHistory33Repository partnerPoliOrgHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private PartnerPoliOrgHistory34Repository partnerPoliOrgHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private PartnerPoliOrgHistory35Repository partnerPoliOrgHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private PartnerPoliOrgHistory36Repository partnerPoliOrgHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private PartnerPoliOrgHistory37Repository partnerPoliOrgHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private PartnerPoliOrgHistory38Repository partnerPoliOrgHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private PartnerPoliOrgHistory39Repository partnerPoliOrgHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private PartnerPoliOrgHistory40Repository partnerPoliOrgHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private PartnerPoliOrgHistory41Repository partnerPoliOrgHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private PartnerPoliOrgHistory42Repository partnerPoliOrgHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private PartnerPoliOrgHistory43Repository partnerPoliOrgHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private PartnerPoliOrgHistory44Repository partnerPoliOrgHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private PartnerPoliOrgHistory45Repository partnerPoliOrgHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private PartnerPoliOrgHistory46Repository partnerPoliOrgHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private PartnerPoliOrgHistory47Repository partnerPoliOrgHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private PartnerPoliOrgHistory99Repository partnerPoliOrgHistory99Repository;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体"); // NOPMD
        baseEntity.setAllAddress("北海道実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎"); // NOPMD
        baseEntity.setPoliOrgKanrenshaCode("123-4567"); // NOPMD
        baseEntity.setOrgDelegateCode("987-6543"); // NOPMD
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory01Entity> listAns = partnerPoliOrgHistory01Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory01Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());

    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("青森県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory02Entity> listAns = partnerPoliOrgHistory02Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory02Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("岩手県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory03Entity> listAns = partnerPoliOrgHistory03Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory03Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("宮城県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory04Entity> listAns = partnerPoliOrgHistory04Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory04Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("秋田県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory05Entity> listAns = partnerPoliOrgHistory05Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory05Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("山形県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory06Entity> listAns = partnerPoliOrgHistory06Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory06Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("福島県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory07Entity> listAns = partnerPoliOrgHistory07Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory07Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("茨城県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory08Entity> listAns = partnerPoliOrgHistory08Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory08Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("栃木県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory09Entity> listAns = partnerPoliOrgHistory09Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory09Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("群馬県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory10Entity> listAns = partnerPoliOrgHistory10Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory10Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("埼玉県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory11Entity> listAns = partnerPoliOrgHistory11Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory11Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("千葉県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory12Entity> listAns = partnerPoliOrgHistory12Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory12Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("東京都実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory13Entity> listAns = partnerPoliOrgHistory13Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory13Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("神奈川県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory14Entity> listAns = partnerPoliOrgHistory14Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory14Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("新潟県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory15Entity> listAns = partnerPoliOrgHistory15Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory15Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("富山県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory16Entity> listAns = partnerPoliOrgHistory16Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory16Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("石川県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory17Entity> listAns = partnerPoliOrgHistory17Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory17Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("福井県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory18Entity> listAns = partnerPoliOrgHistory18Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory18Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("山梨県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory19Entity> listAns = partnerPoliOrgHistory19Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory19Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("長野県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory20Entity> listAns = partnerPoliOrgHistory20Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory20Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("岐阜県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory21Entity> listAns = partnerPoliOrgHistory21Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory21Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("静岡県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory22Entity> listAns = partnerPoliOrgHistory22Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory22Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("愛知県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory23Entity> listAns = partnerPoliOrgHistory23Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory23Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("三重県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory24Entity> listAns = partnerPoliOrgHistory24Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory24Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("滋賀県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory25Entity> listAns = partnerPoliOrgHistory25Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory25Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("京都府実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory26Entity> listAns = partnerPoliOrgHistory26Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory26Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("大阪府実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory27Entity> listAns = partnerPoliOrgHistory27Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory27Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("兵庫県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory28Entity> listAns = partnerPoliOrgHistory28Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory28Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("奈良県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory29Entity> listAns = partnerPoliOrgHistory29Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory29Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("和歌山県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory30Entity> listAns = partnerPoliOrgHistory30Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory30Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("鳥取県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory31Entity> listAns = partnerPoliOrgHistory31Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory31Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("島根県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory32Entity> listAns = partnerPoliOrgHistory32Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory32Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("岡山県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory33Entity> listAns = partnerPoliOrgHistory33Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory33Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("広島県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory34Entity> listAns = partnerPoliOrgHistory34Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory34Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("山口県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory35Entity> listAns = partnerPoliOrgHistory35Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory35Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("徳島県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory36Entity> listAns = partnerPoliOrgHistory36Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory36Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("香川県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory37Entity> listAns = partnerPoliOrgHistory37Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory37Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("愛媛県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory38Entity> listAns = partnerPoliOrgHistory38Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory38Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("高知県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory39Entity> listAns = partnerPoliOrgHistory39Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory39Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("福岡県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory40Entity> listAns = partnerPoliOrgHistory40Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory40Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("佐賀県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory41Entity> listAns = partnerPoliOrgHistory41Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory41Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("長崎県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory42Entity> listAns = partnerPoliOrgHistory42Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory42Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("熊本県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory43Entity> listAns = partnerPoliOrgHistory43Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory43Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("大分県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory44Entity> listAns = partnerPoliOrgHistory44Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory44Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("宮崎県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory45Entity> listAns = partnerPoliOrgHistory45Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory45Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("鹿児島県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory46Entity> listAns = partnerPoliOrgHistory46Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory46Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("沖縄県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory47Entity> listAns = partnerPoliOrgHistory47Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory47Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        PartnerPoliOrgHistoryBaseEntity baseEntity = new PartnerPoliOrgHistoryBaseEntity();
        baseEntity.setPartnerName("ちゃらんぽらん政治団体");
        baseEntity.setAllAddress("青県実在市湖畔町");
        baseEntity.setPoliOrgDelegate("代表者　太郎");
        baseEntity.setPoliOrgKanrenshaCode("123-4567");
        baseEntity.setOrgDelegateCode("987-6543");
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);

        insertPartnerPoliOrgHistoryService.practice(userDto, baseEntity);

        List<PartnerPoliOrgHistory99Entity> listAns = partnerPoliOrgHistory99Repository.findAll();
        assertEquals(1L, listAns.size());
        // 件数を数えるだけでは間違いなく挿入されたかの確認にならないので住所を確認する
        PartnerPoliOrgHistory99Entity ansEntity = listAns.get(0);
        assertEquals(baseEntity.getAllAddress(), ansEntity.getAllAddress());
    }

}
