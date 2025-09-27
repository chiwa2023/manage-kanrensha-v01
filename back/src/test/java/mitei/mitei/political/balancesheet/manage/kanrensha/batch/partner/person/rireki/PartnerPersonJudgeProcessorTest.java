package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonJudgeEntity;

/**
 * PartnerPersonJudgeProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonJudgeProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerPersonJudgeProcessor partnerPersonJudgeProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_master_person.sql")
    void test() throws Exception {
        // 未入力カラムがあると追加作業をしません
        WkTblPartnerPersonJudgeEntity judgeEntity00 = partnerPersonJudgeProcessor
                .process(new WkTblPartnerPersonHistoryEntity());
        assertEquals(false, judgeEntity00.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;関連者コードが入力されていません;", judgeEntity00.getJudgeReason());

        // マスタに登録がないと追加作業をしません(削除済)
        WkTblPartnerPersonHistoryEntity historyEntity01 = new WkTblPartnerPersonHistoryEntity();
        historyEntity01.setPartnerName("迂回献金　次郎");
        historyEntity01.setAllAddress("宮崎県架空市実在町");
        historyEntity01.setPersonShokugyou("教師");
        historyEntity01.setPersonKanrenshaCode("2-345657-QWERTY");

        WkTblPartnerPersonJudgeEntity judgeEntity01 = partnerPersonJudgeProcessor.process(historyEntity01);

        assertEquals(false, judgeEntity01.getIsAffected());
        assertEquals("コードと名称に合致する関連者が存在しません;", judgeEntity01.getJudgeReason());

        // 必要な入力があれば登録します
        WkTblPartnerPersonHistoryEntity historyEntity02 = new WkTblPartnerPersonHistoryEntity();
        historyEntity02.setPartnerName("迂回献金　太郎");
        historyEntity02.setAllAddress("和歌山県架空市実在町");
        historyEntity02.setPersonShokugyou("経営者");
        historyEntity02.setPersonKanrenshaCode("1-2345-ABCCDEF");

        WkTblPartnerPersonJudgeEntity judgeEntity02 = partnerPersonJudgeProcessor.process(historyEntity02);
        assertEquals(true, judgeEntity02.getIsAffected());
        assertEquals(" ", judgeEntity02.getJudgeReason());
    }

}
