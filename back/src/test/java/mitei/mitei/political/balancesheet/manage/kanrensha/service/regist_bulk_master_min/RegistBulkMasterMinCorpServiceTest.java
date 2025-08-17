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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkMasterMinCorpService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkMasterMinCorpServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterMinCorpService registBulkMasterMinCorpService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerCorpAddMinRepository wkTblPartnerCorpAddMinRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_corp_add_min.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblPartnerCorpAddMinEntity entityInput00 = new WkTblPartnerCorpAddMinEntity();
        entityInput00.setWkTblPartnerCorpAddMinId(839);
        UpdateWkTblMinCorpCapsuleDto capsuleDto00 = new UpdateWkTblMinCorpCapsuleDto();
        capsuleDto00.setWkTblPartnerCorpAddMinEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkMasterMinCorpService.practice(capsuleDto00).getWkTblPartnerCorpAddMinId());

        final Integer callId =102;
        
        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblMinCorpCapsuleDto capsuleDto01 = new UpdateWkTblMinCorpCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerCorpAddMinEntity entityInput01 = wkTblPartnerCorpAddMinRepository.findById(callId).get();
        WkTblPartnerCorpAddMinEntity entityBase = new WkTblPartnerCorpAddMinEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblPartnerCorpAddMinEntity(entityBase);

        Integer newId = registBulkMasterMinCorpService.practice(capsuleDto01).getWkTblPartnerCorpAddMinId();
        assertNotEquals(0, newId);
        WkTblPartnerCorpAddMinEntity entityInput02 = wkTblPartnerCorpAddMinRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblPartnerCorpAddMinEntity entityCopy = wkTblPartnerCorpAddMinRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblPartnerCorpAddMinCode(), entityCopy.getWkTblPartnerCorpAddMinCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
