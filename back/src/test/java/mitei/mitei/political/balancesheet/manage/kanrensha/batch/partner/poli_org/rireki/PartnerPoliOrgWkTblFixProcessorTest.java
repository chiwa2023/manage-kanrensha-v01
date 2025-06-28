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
 * PartnerPoliOrgWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgWkTblFixProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgWkTblFixProcessor partnerPoliOrgWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_wk_tbl_partner_poli_org_history.sql")
    void test() throws Exception {

        // ワークテーブルに存在しないデータを呼び出すと編集のないインスタンスが戻る
        WkTblPartnerPoliOrgHistoryEntity historyEntity00 = partnerPoliOrgWkTblFixProcessor
                .process(new WkTblPartnerPoliOrgJudgeEntity());
        assertEquals(0, historyEntity00.getWkPartnerPoliOrgHistoryId());
        assertEquals(false, historyEntity00.getIsAffected());

        // ワークテーブルに存在するデータの場合、判定の内容が反映されてくる(呼び出しとは異なる値)
        WkTblPartnerPoliOrgJudgeEntity judgeEntity01 = new WkTblPartnerPoliOrgJudgeEntity();
        judgeEntity01.setWkPartnerPoliOrgHistoryId(315);
        judgeEntity01.setIsAffected(false);
        judgeEntity01.setJudgeReason("未入力");

        WkTblPartnerPoliOrgHistoryEntity historyEntity01 = partnerPoliOrgWkTblFixProcessor.process(judgeEntity01);
        assertEquals(judgeEntity01.getWkPartnerPoliOrgHistoryId(), historyEntity01.getWkPartnerPoliOrgHistoryId());
        assertEquals(judgeEntity01.getIsAffected(), historyEntity01.getIsAffected());
        assertEquals(judgeEntity01.getJudgeReason(), historyEntity01.getJudgeReason());

    }

}
