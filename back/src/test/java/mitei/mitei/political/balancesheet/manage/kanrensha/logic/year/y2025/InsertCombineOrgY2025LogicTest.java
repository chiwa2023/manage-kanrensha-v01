package mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.PartnerCombineOrg2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.PartnerCombineOrg2025Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertCombineOrgY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertCombineOrgY2025LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertCombineOrgY2025Logic insertCombineOrgY2025Logic;

    /** 個人団体紐づけRepository(2025) */
    @Autowired
    private PartnerCombineOrg2025Repository partnerCombineOrg2025Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_partner_combine_org_2025.sql")
    void test() {

        WkTblPartnerCombineOrgEntity entity00 = new WkTblPartnerCombineOrgEntity();

        entity00.setKanrenshaKbn(KanrenshaKbnConstants.CORP);
        entity00.setPersonKanrenshaCode("111-222");
        entity00.setPersonName("迂回献金　太郎");
        entity00.setOrgKanrenshaCode("333-555");
        entity00.setOrgName("ちゃらんぽらん政治団体");
        entity00.setStartYear(Short.valueOf("2025"));
        entity00.setEndYear(Short.valueOf("2025"));
        entity00.setYearArrayText("2025");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        Integer insertId = insertCombineOrgY2025Logic.practice(entity00, CreateLeastUserForTestUtil.practice());
        assertNotEquals(0, insertId); // 正常に登録

        PartnerCombineOrg2025Entity entityAns = partnerCombineOrg2025Repository.findById(insertId).get();

        assertEquals(entity00.getKanrenshaKbn(), entityAns.getKanrenshaKbn());
        assertEquals(entity00.getPersonKanrenshaCode(), entityAns.getPersonKanrenshaCode());
        assertEquals(entity00.getPersonName(), entityAns.getPersonName());
        assertEquals(entity00.getOrgKanrenshaCode(), entityAns.getOrgKanrenshaCode());
        assertEquals(entity00.getOrgName(), entityAns.getOrgName());
    }

}
