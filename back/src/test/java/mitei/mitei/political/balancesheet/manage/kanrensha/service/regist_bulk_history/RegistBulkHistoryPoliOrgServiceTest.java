package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkHistoryPoliOrgService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkHistoryPoliOrgServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkHistoryPoliOrgService registBulkHistoryPoliOrgService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTblPartnerPoliOrgHistoryRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_poli_org_history.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblPartnerPoliOrgHistoryEntity entityInput00 = new WkTblPartnerPoliOrgHistoryEntity();
        entityInput00.setWkPartnerPoliOrgHistoryId(839);
        UpdateWkTblHistoryPoliOrgCapsuleDto capsuleDto00 = new UpdateWkTblHistoryPoliOrgCapsuleDto();
        capsuleDto00.setWkTblPartnerPoliOrgHistoryEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkHistoryPoliOrgService.practice(capsuleDto00).getWkPartnerPoliOrgHistoryId());

        final Integer callId = 313;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblHistoryPoliOrgCapsuleDto capsuleDto01 = new UpdateWkTblHistoryPoliOrgCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerPoliOrgHistoryEntity entityInput01 = wkTblPartnerPoliOrgHistoryRepository.findById(callId).get();
        WkTblPartnerPoliOrgHistoryEntity entityBase = new WkTblPartnerPoliOrgHistoryEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblPartnerPoliOrgHistoryEntity(entityBase);

        Integer newId = registBulkHistoryPoliOrgService.practice(capsuleDto01).getWkPartnerPoliOrgHistoryId();
        assertNotEquals(0, newId);
        WkTblPartnerPoliOrgHistoryEntity entityInput02 = wkTblPartnerPoliOrgHistoryRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblPartnerPoliOrgHistoryEntity entityCopy = wkTblPartnerPoliOrgHistoryRepository.findById(newId).get();
        assertEquals(entityBase.getWkPartnerPoliOrgHistoryCode(), entityCopy.getWkPartnerPoliOrgHistoryCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
