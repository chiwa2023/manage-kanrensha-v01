package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;

/**
 * SearchPartnerAllByHistoryNameAddressService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql({ "sample_partner_person_history.sql", "sample_partner_corp_history.sql", "sample_partner_poli_org_history.sql" })
class SearchPartnerAllByHistoryNameAddressServiceTest { // NOPMD TooManyMethod

    /** テスト対象 */
    @Autowired
    private SearchPartnerAllByHistoryNameAddressService searchPartnerAllByHistoryNameAddressService;

    /** 関連者区分個人 */
    private final static short PERSON = 1;

    /** 関連者区分個人 */
    private final static short CORP = 2;

    /** 関連者区分個人 */
    private final static short POLI_ORG = 3;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "北海道架空市山麓町"); // NOPMD
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "北海道架空市山麓町"); // NOPMD
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice( //
                "ちゃらんぽらん政治団体", "北海道架空市山麓町"); // NOPMD
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "青森県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "青森県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "青森県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "岩手県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "岩手県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "岩手県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "宮城県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "宮城県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "宮城県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "秋田県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "秋田県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "秋田県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "山形県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "山形県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "山形県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "福島県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "福島県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "福島県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "茨城県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "茨城県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "茨城県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "栃木県架空市山麓町"); // NOPMD
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "栃木県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "栃木県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "栃木県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "栃木県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "栃木県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "埼玉県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "埼玉県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "埼玉県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "千葉県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "千葉県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "千葉県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "東京都架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "東京都架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "東京都架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎",
                "神奈川県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業",
                "神奈川県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "神奈川県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "新潟県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "新潟県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "新潟県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "富山県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "富山県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "富山県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "石川県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "石川県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "石川県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "福井県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "福井県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "福井県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "山梨県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "山梨県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "山梨県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "長野県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "長野県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "長野県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "岐阜県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "岐阜県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "岐阜県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "静岡県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "静岡県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "静岡県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "愛知県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "愛知県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "愛知県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "三重県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "三重県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "三重県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "滋賀県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "滋賀県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "滋賀県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "京都府架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "京都府架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "京都府架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "大阪府架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "大阪府架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "大阪府架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "兵庫県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "兵庫県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "兵庫県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "奈良県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "奈良県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "奈良県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎",
                "和歌山県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業",
                "和歌山県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "和歌山県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "鳥取県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "鳥取県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "鳥取県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "島根県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "島根県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "島根県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "岡山県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "岡山県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "岡山県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "広島県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "広島県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "広島県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "山口県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "山口県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "山口県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "徳島県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "徳島県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "徳島県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "香川県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "香川県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "香川県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "愛媛県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "愛媛県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "愛媛県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "高知県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "高知県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "高知県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "福岡県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "福岡県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "福岡県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "佐賀県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "佐賀県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "佐賀県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "長崎県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "長崎県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "長崎県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "熊本県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "熊本県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "熊本県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "大分県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "大分県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "大分県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "宮崎県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "宮崎県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "宮崎県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎",
                "鹿児島県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業",
                "鹿児島県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "鹿児島県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "沖縄県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "沖縄県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "沖縄県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        // 個人
        List<PartnerCommonInfoDto> list1 = searchPartnerAllByHistoryNameAddressService.practice("迂回献金　太郎", "青県架空市山麓町");
        assertEquals(1, list1.size());
        assertEquals(PERSON, list1.get(0).getKanrenshaKbn());

        // 企業団体
        List<PartnerCommonInfoDto> list2 = searchPartnerAllByHistoryNameAddressService.practice("ぼったくり企業", "青県架空市山麓町");
        assertEquals(1, list2.size());
        assertEquals(CORP, list2.get(0).getKanrenshaKbn());

        // 政治団体
        List<PartnerCommonInfoDto> list3 = searchPartnerAllByHistoryNameAddressService.practice("ちゃらんぽらん政治団体",
                "青県架空市山麓町");
        assertEquals(1, list3.size());
        assertEquals(POLI_ORG, list3.get(0).getKanrenshaKbn());
    }

}
