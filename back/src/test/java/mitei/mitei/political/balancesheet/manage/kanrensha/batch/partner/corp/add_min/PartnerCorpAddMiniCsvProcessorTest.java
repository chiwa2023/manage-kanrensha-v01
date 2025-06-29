package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;

/**
 * PartnerCorpAddMiniCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpAddMiniCsvProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerCorpAddMiniCsvProcessor partnerCorpAddMiniCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({"sample_partner_corp_history_01.sql","sample_master_corporation.sql"})
    void test() throws Exception {

        // 未入力カラムがあると追加作業をしません
        WkTblPartnerCorpAddMinEntity minEntity00 = partnerCorpAddMiniCsvProcessor.process(new PartnerCorpAddMiniDto());
        assertEquals(false, minEntity00.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;代表者が入力されていません;法人番号が入力されていません;", minEntity00.getJudgeReason());

        // 法人番号が形式通り(ハイフンを除いて数字13桁)でなければ追加作業をしません
        PartnerCorpAddMiniDto dto01 = new PartnerCorpAddMiniDto();
        dto01.setPartnerName("いいかげん政治団体");
        dto01.setAllAddress("宮崎県架空市");
        dto01.setCorpDelegate("代表者　次郎");
        dto01.setHoujinNo("123abcd45");

        WkTblPartnerCorpAddMinEntity minEntity01 = partnerCorpAddMiniCsvProcessor.process(dto01);
        assertEquals(false, minEntity01.getIsAffected());
        assertEquals("法人番号の形式ではありません(数字13桁);", minEntity01.getJudgeReason());

        
        // 完全一致する履歴がある場合は追加できません
        PartnerCorpAddMiniDto dto02 = new PartnerCorpAddMiniDto();
        dto02.setPartnerName("ぼったくり企業");
        dto02.setAllAddress("和歌山県架空市山麓町");
        dto02.setCorpDelegate("代表者　太郎");
        dto02.setHoujinNo("1234567890123");
        
        WkTblPartnerCorpAddMinEntity minEntity02 = partnerCorpAddMiniCsvProcessor.process(dto02);
        assertEquals(false, minEntity02.getIsAffected());
        assertEquals("すでに登録が存在します(1-2345-67-890123-4567890);", minEntity02.getJudgeReason());
        
        // 同名の団体が存在する場合はケースバイケースですが、バッチによる自動登録はできません
        PartnerCorpAddMiniDto dto03 = new PartnerCorpAddMiniDto();
        dto03.setPartnerName("ふんだくり企業");
        dto03.setAllAddress("和歌山県架空市山麓町");
        dto03.setCorpDelegate("代表者　太郎");
        dto03.setHoujinNo("1234567890123");
        
        WkTblPartnerCorpAddMinEntity minEntity03 = partnerCorpAddMiniCsvProcessor.process(dto03);
        assertEquals(false, minEntity03.getIsAffected());
        assertEquals("同名の団体があります。確認調査の上、必要に応じて追加してください;", minEntity03.getJudgeReason());

        // 全くの新規であれば追加作業します
        PartnerCorpAddMiniDto dto04 = new PartnerCorpAddMiniDto();
        dto04.setPartnerName("超元素製造組合");
        dto04.setAllAddress("宮崎県実在市湖畔町");
        dto04.setCorpDelegate("組合長　花子");
        dto04.setHoujinNo("9876543210987");

        WkTblPartnerCorpAddMinEntity minEntity04 = partnerCorpAddMiniCsvProcessor.process(dto04);
        assertEquals(true, minEntity04.getIsAffected());
        assertEquals("", minEntity04.getJudgeReason());
        
    }

}
