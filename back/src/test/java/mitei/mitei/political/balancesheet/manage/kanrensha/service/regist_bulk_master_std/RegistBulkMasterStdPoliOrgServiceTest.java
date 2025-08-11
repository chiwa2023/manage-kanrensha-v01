package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_std;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkMasterStdPoliOrgService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkMasterStdPoliOrgServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterStdPoliOrgService registBulkMasterStdPoliOrgService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_master_poli_org.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblMasterPoliOrgEntity entityInput00 = new WkTblMasterPoliOrgEntity();
        entityInput00.setWkTblMasterPoliOrgId(839);
        UpdateWkTblStdPoliOrgCapsuleDto capsuleDto00 = new UpdateWkTblStdPoliOrgCapsuleDto();
        capsuleDto00.setWkTblMasterPoliOrgEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkMasterStdPoliOrgService.practice(capsuleDto00));

        final Integer callId = 533;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblStdPoliOrgCapsuleDto capsuleDto01 = new UpdateWkTblStdPoliOrgCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblMasterPoliOrgEntity entityInput01 = wkTblMasterPoliOrgRepository.findById(callId).get();
        WkTblMasterPoliOrgEntity entityBase = new WkTblMasterPoliOrgEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAddressBlock("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblMasterPoliOrgEntity(entityBase);

        Integer newId = registBulkMasterStdPoliOrgService.practice(capsuleDto01);
        assertNotEquals(0, newId);
        WkTblMasterPoliOrgEntity entityInput02 = wkTblMasterPoliOrgRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblMasterPoliOrgEntity entityCopy = wkTblMasterPoliOrgRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblMasterPoliOrgCode(), entityCopy.getWkTblMasterPoliOrgCode());
        assertEquals(entityBase.getAddressBlock(), entityCopy.getAddressBlock());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
