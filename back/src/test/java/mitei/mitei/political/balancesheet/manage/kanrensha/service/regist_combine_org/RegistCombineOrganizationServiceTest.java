package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_combine_org;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistCombineOrganizationService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistCombineOrganizationServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistCombineOrganizationService registCombineOrganizationService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerCombineOrgRepository wkTblPartnerCombineOrgRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "sample_wk_tbl_partner_combine_org.sql", "master_person.sql", "master_corporation.sql",
            "master_political_organization.sql" })
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblPartnerCombineOrgEntity entityInput00 = new WkTblPartnerCombineOrgEntity();
        entityInput00.setWkTblPartnerCombineOrgId(839);
        UpdateWkTblCombineOrgCapsuleDto capsuleDto00 = new UpdateWkTblCombineOrgCapsuleDto();
        capsuleDto00.setWkTblPartnerCombineOrgEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registCombineOrganizationService.practice(capsuleDto00));

        final Integer callId = 211;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblCombineOrgCapsuleDto capsuleDto01 = new UpdateWkTblCombineOrgCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerCombineOrgEntity entityInput01 = wkTblPartnerCombineOrgRepository.findById(callId).get();
        WkTblPartnerCombineOrgEntity entityBase = new WkTblPartnerCombineOrgEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setOrgName("超元素製造組合");
        entityBase.setYearArrayText("1234567");
        capsuleDto01.setWkTblPartnerCombineOrgEntity(entityBase);

        Integer newId = registCombineOrganizationService.practice(capsuleDto01);
        assertNotEquals(0, newId);
        WkTblPartnerCombineOrgEntity entityInput02 = wkTblPartnerCombineOrgRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblPartnerCombineOrgEntity entityCopy = wkTblPartnerCombineOrgRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblPartnerCombineOrgCode(), entityCopy.getWkTblPartnerCombineOrgCode());
        assertEquals(entityBase.getOrgName(), entityCopy.getOrgName());
        assertEquals(entityBase.getYearArrayText(), entityCopy.getYearArrayText());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());

        // TODO 判定処理実装後にテストを追加
        // assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());

        fail("Not yet implemented");
    }

}
