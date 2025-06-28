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
 * PartnerPersonWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonWkTblFixProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerPersonWkTblFixProcessor partnerPersonWkTblFixProcessor;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_wk_tbl_partner_person_history.sql")
    void test()throws Exception {
        
        // ワークテーブルに存在しないデータを呼び出すと編集のない空インスタンスが戻る
        WkTblPartnerPersonHistoryEntity historyEntity00 = partnerPersonWkTblFixProcessor
                .process(new WkTblPartnerPersonJudgeEntity());
        assertEquals(0, historyEntity00.getWkPartnerPersonHistoryId());
        assertEquals(false, historyEntity00.getIsAffected());

        // ワークテーブルに存在するデータの場合、判定の内容が反映されてくる(呼び出しとは異なる値)
        WkTblPartnerPersonJudgeEntity judgeEntity01 = new WkTblPartnerPersonJudgeEntity();
        judgeEntity01.setWkPartnerPersonHistoryId(101);
        judgeEntity01.setIsAffected(false);
        judgeEntity01.setJudgeReason("未入力");

        WkTblPartnerPersonHistoryEntity historyEntity01 = partnerPersonWkTblFixProcessor.process(judgeEntity01);
        assertEquals(judgeEntity01.getWkPartnerPersonHistoryId(), historyEntity01.getWkPartnerPersonHistoryId());
        assertEquals(judgeEntity01.getIsAffected(), historyEntity01.getIsAffected());
        assertEquals(judgeEntity01.getJudgeReason(), historyEntity01.getJudgeReason());
        
    }

}
