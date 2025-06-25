package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp;

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
 * PartnerCorpWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpWkTblFixProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerCorpWkTblFixProcessor partnerCorpWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_master_corporation.sql")
    void test() throws Exception {

        // ワークテーブルに存在しないデータを呼び出すと編集のないインスタンスが戻る
        WkTblPartnerCorpHistoryEntity historyEntity00 = partnerCorpWkTblFixProcessor
                .process(new WkTblPartnerCorpJudgeEntity());
        assertEquals(0, historyEntity00.getWkPartnerCorpHistoryId());
        assertEquals(false, historyEntity00.getIsAffected());

        // ワークテーブルに存在するデータの場合、判定の内容が反映されてくる(呼び出しとは異なる値)
        WkTblPartnerCorpJudgeEntity judgeEntity01 = new WkTblPartnerCorpJudgeEntity();
        judgeEntity01.setWkPartnerCorpHistoryId(102);
        judgeEntity01.setIsAffected(false);
        judgeEntity01.setJudgeReason("未入力");

        WkTblPartnerCorpHistoryEntity historyEntity01 = partnerCorpWkTblFixProcessor.process(judgeEntity01);
        assertEquals(judgeEntity01.getWkPartnerCorpHistoryId(), historyEntity01.getWkPartnerCorpHistoryId());
        assertEquals(judgeEntity01.getIsAffected(), historyEntity01.getIsAffected());
        assertEquals(judgeEntity01.getJudgeReason(), historyEntity01.getJudgeReason());

    }

}
