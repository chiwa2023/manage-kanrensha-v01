package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;

/**
 * PartnerPoliOrgAddMiniCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgAddMiniCsvProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgAddMiniCsvProcessor partnerPoliOrgAddMiniCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_partner_poli_org_history_01.sql", "sample_master_political_organization.sql" })
    void test() throws Exception {

        // 未入力カラムがあると追加作業をしません
        WkTblPartnerPoliOrgAddMinEntity minEntity00 = partnerPoliOrgAddMiniCsvProcessor
                .process(new PartnerPoliOrgAddMiniDto());
        assertEquals(false, minEntity00.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;代表者が入力されていません;政治団体区分が入力されていません;", minEntity00.getJudgeReason());

        // 政治団体区分が想定値(01-06)でなければ追加作業をしません
        PartnerPoliOrgAddMiniDto dto01 = new PartnerPoliOrgAddMiniDto();
        dto01.setPartnerName("いいかげん政治団体");
        dto01.setAllAddress("宮崎県架空市");
        dto01.setPoliOrgDelegate("代表者　次郎");
        dto01.setDantaiKbn("123abcd45");

        WkTblPartnerPoliOrgAddMinEntity minEntity01 = partnerPoliOrgAddMiniCsvProcessor.process(dto01);
        assertEquals(false, minEntity01.getIsAffected());
        assertEquals("政治団体区分の値が不正です;", minEntity01.getJudgeReason());

        // 完全一致する履歴がある場合は追加できません
        PartnerPoliOrgAddMiniDto dto02 = new PartnerPoliOrgAddMiniDto();
        dto02.setPartnerName("いいかげん政治団体");
        dto02.setAllAddress("宮崎県架空市実在町");
        dto02.setPoliOrgDelegate("代表者　次郎");
        dto02.setDantaiKbn("05");

        WkTblPartnerPoliOrgAddMinEntity minEntity02 = partnerPoliOrgAddMiniCsvProcessor.process(dto02);
        assertEquals(false, minEntity02.getIsAffected());
        assertEquals("すでに登録が存在します(98-98-9876);", minEntity02.getJudgeReason());

        // 同名の団体が存在する場合はケースバイケースですが、バッチによる自動登録はできません
        PartnerPoliOrgAddMiniDto dto03 = new PartnerPoliOrgAddMiniDto();
        dto03.setPartnerName("ちゃらんぽらん団体");
        dto03.setAllAddress("和歌山県架空市山麓町");
        dto03.setPoliOrgDelegate("代表者　次郎");
        dto03.setDantaiKbn("01");

        WkTblPartnerPoliOrgAddMinEntity minEntity03 = partnerPoliOrgAddMiniCsvProcessor.process(dto03);
        assertEquals(false, minEntity03.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", minEntity03.getJudgeReason());

        // 全くの新規であれば追加作業します
        PartnerPoliOrgAddMiniDto dto04 = new PartnerPoliOrgAddMiniDto();
        dto04.setPartnerName("政治団体A");
        dto04.setAllAddress("宮崎県実在市湖畔町");
        dto04.setPoliOrgDelegate("組合長　花子");
        dto04.setDantaiKbn("06");

        WkTblPartnerPoliOrgAddMinEntity minEntity04 = partnerPoliOrgAddMiniCsvProcessor.process(dto04);
        assertEquals(true, minEntity04.getIsAffected());
        assertEquals(" ", minEntity04.getJudgeReason());

    }

}
