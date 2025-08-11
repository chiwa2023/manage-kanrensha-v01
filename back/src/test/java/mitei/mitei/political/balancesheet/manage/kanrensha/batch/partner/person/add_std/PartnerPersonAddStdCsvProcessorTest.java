package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;

/**
 * PartnerPersonAddStdCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonAddStdCsvProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerPersonAddStdCsvProcessor partnerPersonAddStdCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_master_person.sql", "sample_partner_person_history.sql" })
    void test() throws Exception {

        // 未入力カラムがあると追加作業をしません
        WkTblMasterPersonEntity wktblEntity00 = partnerPersonAddStdCsvProcessor.process(new PartnerPersonAddStdDto());
        assertEquals(false, wktblEntity00.getIsAffected());

        // 未入力エラーメッセージ
        StringBuilder builder0 = new StringBuilder();
        builder0.append("名称が入力されていません;") // NOPMD
        .append("住所が入力されていません;")
        // .append("職業が入力されていません;")
        .append("住所郵便番号までが入力されていません;")
                .append("住所番地までが入力されていません;").append("住所建物までが入力されていません;").append("電話番号市外局番が入力されていません;")
                .append("電話番号局番が入力されていません;").append("電話番号番号が入力されていません;").append("メールアドレスが入力されていません;");
        assertEquals(builder0.toString(), wktblEntity00.getJudgeReason());

        PartnerPersonAddStdDto dto01 = this.createHissuInputDto();
        dto01.setPartnerName("迂回献金　次郎");
        final String textLength13 = "1234567890123";
        dto01.setPhon1(textLength13);
        dto01.setPhon2(textLength13);
        dto01.setPhon3(textLength13);
        dto01.setPostal1(textLength13);
        dto01.setPostal2(textLength13);
        dto01.setLgCode(textLength13);
        dto01.setMachiazaId(textLength13);
        dto01.setBlkId(textLength13);
        dto01.setRsdtId(textLength13);
        dto01.setRsdt2Id(textLength13);

        WkTblMasterPersonEntity wktblEntity01 = partnerPersonAddStdCsvProcessor.process(dto01);
        assertEquals(false, wktblEntity01.getIsAffected());
        StringBuilder builder1 = new StringBuilder();
        builder1.append("電話番号市外局番が10文字以上です;") // NOPMD
        .append("電話番号局番が10文字以上です;").append("電話番号fc番号が10文字以上です;")
                .append("郵便番号1が6文字以上です;").append("郵便番号2が6文字以上です;").append("地方自治体コードが8文字以上です;").append("町字コードが9文字以上です;").append("街区コードが5文字以上です;")
                .append("住居コードが5文字以上です;").append("住居2コードが7文字以上です;");
        assertEquals(builder1.toString(), wktblEntity01.getJudgeReason());

        // 完全一致する履歴がある場合は追加できません
        PartnerPersonAddStdDto dto02 = this.createHissuInputDto();
        dto02.setPartnerName("迂回献金　太郎");
        dto02.setAllAddress("北海道架空市湖畔町");
        dto02.setPersonShokugyou("団体役員");
        WkTblMasterPersonEntity wktblEntity02 = partnerPersonAddStdCsvProcessor.process(dto02);
        assertEquals(false, wktblEntity02.getIsAffected());
        assertEquals("すでに登録が存在します(1-2345-567);", wktblEntity02.getJudgeReason());

        // 同名の団体が存在する場合はケースバイケースですが、少なくともバッチによる自動登録はできません
        PartnerPersonAddStdDto dto03 = this.createHissuInputDto();
        dto03.setPartnerName("寄付上限　花子");
        dto03.setAllAddress("山形県架空市実在町");
        dto03.setPersonShokugyou("医師");

        WkTblMasterPersonEntity wktblEntity03 = partnerPersonAddStdCsvProcessor.process(dto03);
        assertEquals(false, wktblEntity03.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", wktblEntity03.getJudgeReason());

        // 全くの新規であれば追加作業します
        PartnerPersonAddStdDto dto04 = this.createHissuInputDto();
        dto04.setPartnerName("寄付上限　直子");
        dto04.setAllAddress("宮崎県架空市実在町");
        dto03.setPersonShokugyou("教師");

        WkTblMasterPersonEntity wktblEntity04 = partnerPersonAddStdCsvProcessor.process(dto04);
        assertEquals(true, wktblEntity04.getIsAffected());

    }

    private PartnerPersonAddStdDto createHissuInputDto() {
        PartnerPersonAddStdDto dto = new PartnerPersonAddStdDto();

        dto.setPartnerName("迂回献金　太郎");
        dto.setAllAddress("北海道架空市湖畔町");
        dto.setPersonShokugyou("団体役員");
        dto.setAddressPostal("北海道架空市湖畔町");
        dto.setAddressBlock("100番地2");
        dto.setAddressBuilding("四角アパート303");
        dto.setPostal1("012");
        dto.setPostal2("3456");
        dto.setPhon1("012");
        dto.setPhon2("3456");
        dto.setPhon3("7890");
        dto.setEmail("aaa@bbb.net");

        return dto;
    }

}
