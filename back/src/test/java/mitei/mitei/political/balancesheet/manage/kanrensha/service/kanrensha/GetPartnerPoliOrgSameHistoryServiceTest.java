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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;

/**
 * GetPartnerPoliOrgSameHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql( "sample_partner_poli_org_history.sql")
class GetPartnerPoliOrgSameHistoryServiceTest { // NOPMD

    /** テスト対象 */
    @Autowired
    private GetPartnerPoliOrgSameHistoryService getPartnerPoliOrgSameHistoryService;

    /** 010006,北海道 */
    @Test
    @Tag("TableTruncate") // NOPMD
    void test01() throws Exception {
        final String name = "ちゃらんぽらん政治団体"; // NOPMD
        final String address = "北海道架空市山麓町";
        final String delegate = "代表者　太郎"; // NOPMD
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 020001,青森県 */
    @Test
    @Tag("TableTruncate")
    void test02() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "青森県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 030007,岩手県 */
    @Test
    @Tag("TableTruncate")
    void test03() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "岩手県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 040002,宮城県 */
    @Test
    @Tag("TableTruncate")
    void test04() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "宮城県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 050008,秋田県 */
    @Test
    @Tag("TableTruncate")
    void test05() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "秋田県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 060003,山形県 */
    @Test
    @Tag("TableTruncate")
    void test06() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "山形県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 070009,福島県 */
    @Test
    @Tag("TableTruncate")
    void test07() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "福島県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 080004,茨城県 */
    @Test
    @Tag("TableTruncate")
    void test08() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "茨城県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }
     
    /** 090000,栃木県 */
    @Test
    @Tag("TableTruncate")
    void test09() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "栃木県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 100005,群馬県 */
    @Test
    @Tag("TableTruncate")
    void test10() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "群馬県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 110001,埼玉県 */
    @Test
    @Tag("TableTruncate")
    void test11() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "埼玉県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 120006,千葉県 */
    @Test
    @Tag("TableTruncate")
    void test12() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "千葉県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 130001,東京都 */
    @Test
    @Tag("TableTruncate")
    void test13() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "東京都架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 140007,神奈川県 */
    @Test
    @Tag("TableTruncate")
    void test14() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "神奈川県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 150002,新潟県 */
    @Test
    @Tag("TableTruncate")
    void test15() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "新潟県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 160008,富山県 */
    @Test
    @Tag("TableTruncate")
    void test16() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "富山県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 170003,石川県 */
    @Test
    @Tag("TableTruncate")
    void test17() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "石川県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 180009,福井県 */
    @Test
    @Tag("TableTruncate")
    void test18() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "福井県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 190004,山梨県 */
    @Test
    @Tag("TableTruncate")
    void test19() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "山梨県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 200000,長野県 */
    @Test
    @Tag("TableTruncate")
    void test20() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "長野県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 210005,岐阜県 */
    @Test
    @Tag("TableTruncate")
    void test21() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "岐阜県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 220001,静岡県 */
    @Test
    @Tag("TableTruncate")
    void test22() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "静岡県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 230006,愛知県 */
    @Test
    @Tag("TableTruncate")
    void test23() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "愛知県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 240001,三重県 */
    @Test
    @Tag("TableTruncate")
    void test24() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "三重県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 250007,滋賀県 */
    @Test
    @Tag("TableTruncate")
    void test25() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "滋賀県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 260002,京都府 */
    @Test
    @Tag("TableTruncate")
    void test26() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "京都府架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 270008,大阪府 */
    @Test
    @Tag("TableTruncate")
    void test27() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "大阪府架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 280003,兵庫県 */
    @Test
    @Tag("TableTruncate")
    void test28() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "兵庫県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 290009,奈良県 */
    @Test
    @Tag("TableTruncate")
    void test29() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "奈良県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 300004,和歌山県 */
    @Test
    @Tag("TableTruncate")
    void test30() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "和歌山県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 310000,鳥取県 */
    @Test
    @Tag("TableTruncate")
    void test31() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "鳥取県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 320005,島根県 */
    @Test
    @Tag("TableTruncate")
    void test32() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "島根県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 330001,岡山県 */
    @Test
    @Tag("TableTruncate")
    void test33() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "岡山県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 340006,広島県 */
    @Test
    @Tag("TableTruncate")
    void test34() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "広島県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 350001,山口県 */
    @Test
    @Tag("TableTruncate")
    void test35() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "山口県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 360007,徳島県 */
    @Test
    @Tag("TableTruncate")
    void test36() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "徳島県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 370002,香川県 */
    @Test
    @Tag("TableTruncate")
    void test37() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "香川県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 380008,愛媛県 */
    @Test
    @Tag("TableTruncate")
    void test38() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "愛媛県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 390003,高知県 */
    @Test
    @Tag("TableTruncate")
    void test39() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "高知県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 400009,福岡県 */
    @Test
    @Tag("TableTruncate")
    void test40() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "福岡県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 410004,佐賀県 */
    @Test
    @Tag("TableTruncate")
    void test41() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "佐賀県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 420000,長崎県 */
    @Test
    @Tag("TableTruncate")
    void test42() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "長崎県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 430005,熊本県 */
    @Test
    @Tag("TableTruncate")
    void test43() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "熊本県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 440001,大分県 */
    @Test
    @Tag("TableTruncate")
    void test44() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "大分県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 450006,宮崎県 */
    @Test
    @Tag("TableTruncate")
    void test45() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "宮崎県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 460001,鹿児島県 */
    @Test
    @Tag("TableTruncate")
    void test46() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "鹿児島県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 470007,沖縄県 */
    @Test
    @Tag("TableTruncate")
    void test47() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "沖縄県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

    /** 99,その他 */
    @Test
    @Tag("TableTruncate")
    void test99() throws Exception {
        final String name = "ちゃらんぽらん政治団体";
        final String address = "青県架空市山麓町";
        final String delegate = "代表者　太郎";
        
        List<PartnerPoliOrgHistoryBaseEntity> list = getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
        assertEquals(1, list.size());
        
        PartnerPoliOrgHistoryBaseEntity entity = list.get(0);
        assertEquals(name, entity.getPartnerName());
        assertEquals(address, entity.getAllAddress());
        assertEquals(delegate, entity.getPoliOrgDelegate());
    }

}
