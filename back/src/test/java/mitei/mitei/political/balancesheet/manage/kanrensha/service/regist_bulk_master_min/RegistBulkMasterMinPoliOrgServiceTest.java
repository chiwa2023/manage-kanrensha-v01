package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkMasterMinPoliOrgService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkMasterMinPoliOrgServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterMinPoliOrgService registBulkMasterMinPoliOrgService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinRepository wkTblPartnerPoliOrgAddMinRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_poli_org_add_min.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblPartnerPoliOrgAddMinEntity entityInput00 = new WkTblPartnerPoliOrgAddMinEntity();
        entityInput00.setWkTblPartnerPoliOrgAddMinId(839);
        UpdateWkTblMinPoliOrgCapsuleDto capsuleDto00 = new UpdateWkTblMinPoliOrgCapsuleDto();
        capsuleDto00.setWkTblPartnerPoliOrgAddMinEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkMasterMinPoliOrgService.practice(capsuleDto00));

        final Integer callId = 298;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblMinPoliOrgCapsuleDto capsuleDto01 = new UpdateWkTblMinPoliOrgCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerPoliOrgAddMinEntity entityInput01 = wkTblPartnerPoliOrgAddMinRepository.findById(callId).get();
        WkTblPartnerPoliOrgAddMinEntity entityBase = new WkTblPartnerPoliOrgAddMinEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblPartnerPoliOrgAddMinEntity(entityBase);

        Integer newId = registBulkMasterMinPoliOrgService.practice(capsuleDto01);
        assertNotEquals(0, newId);
        WkTblPartnerPoliOrgAddMinEntity entityInput02 = wkTblPartnerPoliOrgAddMinRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblPartnerPoliOrgAddMinEntity entityCopy = wkTblPartnerPoliOrgAddMinRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblPartnerPoliOrgAddMinCode(), entityCopy.getWkTblPartnerPoliOrgAddMinCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
