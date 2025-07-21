package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpJudgeEntity;

/**
 * PartnerCorpJudgeProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpJudgeProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerCorpJudgeProcessor partnerCorpJudgeProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_master_corporation.sql")
    void test() throws Exception {

        // 未入力カラムがあると追加作業をしません
        WkTblPartnerCorpJudgeEntity judgeEntity00 = partnerCorpJudgeProcessor
                .process(new WkTblPartnerCorpHistoryEntity());
        assertEquals(false, judgeEntity00.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;関連者コードが入力されていません;", judgeEntity00.getJudgeReason());

        // マスタに登録がないと追加作業をしません
        WkTblPartnerCorpHistoryEntity historyEntity01 = new WkTblPartnerCorpHistoryEntity();
        historyEntity01.setPartnerName("いいかげん政治団体");
        historyEntity01.setAllAddress("宮崎県架空市");
        historyEntity01.setCorpDelegate("代表者　次郎");
        historyEntity01.setCorpKanrenshaCode("111-222-3333");
        
        WkTblPartnerCorpJudgeEntity judgeEntity01 = partnerCorpJudgeProcessor.process(historyEntity01);

        assertEquals(false, judgeEntity01.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在しません;", judgeEntity01.getJudgeReason());
        
        // 必要な入力があれば登録します
        WkTblPartnerCorpHistoryEntity historyEntity02 = new WkTblPartnerCorpHistoryEntity();
        historyEntity02.setPartnerName("ちゃらんぽらん政治団体");
        historyEntity02.setAllAddress("和歌山県実在市山麓町");
        historyEntity02.setCorpDelegate("代表者　太郎");
        historyEntity02.setCorpKanrenshaCode("111-222-3333");
        
        WkTblPartnerCorpJudgeEntity judgeEntity02 = partnerCorpJudgeProcessor.process(historyEntity02);
        assertEquals(true, judgeEntity02.getIsAffected());
        assertEquals("", judgeEntity02.getJudgeReason());
        
    }

}
