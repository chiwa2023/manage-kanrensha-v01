package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgJudgeEntity;

/**
 * PartnerPoliOrgJudgeProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgJudgeProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgJudgeProcessor partnerPoliOrgJudgeProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_master_political_organization.sql")
    void test() throws Exception {

        // 未入力カラムがあると追加作業をしません
        WkTblPartnerPoliOrgJudgeEntity judgeEntity00 = partnerPoliOrgJudgeProcessor
                .process(new WkTblPartnerPoliOrgHistoryEntity());
        assertEquals(false, judgeEntity00.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;関連者コードが入力されていません;", judgeEntity00.getJudgeReason());

        // マスタに登録がないと追加作業をしません
        WkTblPartnerPoliOrgHistoryEntity historyEntity01 = new WkTblPartnerPoliOrgHistoryEntity();
        historyEntity01.setPartnerName("いいかげん政治団体");
        historyEntity01.setAllAddress("宮崎県架空市実在町");
        historyEntity01.setPoliOrgDelegate("代表者　次郎");
        historyEntity01.setPoliOrgKanrenshaCode("23-45657-QWERTY");

        WkTblPartnerPoliOrgJudgeEntity judgeEntity01 = partnerPoliOrgJudgeProcessor.process(historyEntity01);

        assertEquals(false, judgeEntity01.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在しません;", judgeEntity01.getJudgeReason());

        // 必要な入力があれば登録します
        WkTblPartnerPoliOrgHistoryEntity historyEntity02 = new WkTblPartnerPoliOrgHistoryEntity();
        historyEntity02.setPartnerName("ちゃらんぽらん団体");
        historyEntity02.setAllAddress("和歌山県架空市実在町");
        historyEntity02.setPoliOrgDelegate("代表者　太郎");
        historyEntity02.setPoliOrgKanrenshaCode("12-345-ABCCDEF");

        WkTblPartnerPoliOrgJudgeEntity judgeEntity02 = partnerPoliOrgJudgeProcessor.process(historyEntity02);
        assertEquals(true, judgeEntity02.getIsAffected());
        assertEquals(" ", judgeEntity02.getJudgeReason());

    }

}
