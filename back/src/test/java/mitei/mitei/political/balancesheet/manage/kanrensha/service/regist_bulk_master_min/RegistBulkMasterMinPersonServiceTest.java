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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkMasterMinPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkMasterMinPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterMinPersonService registBulkMasterMinPersonService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerPersonAddMinRepository wkTblPartnerPersonAddMinRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_person_add_min.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblPartnerPersonAddMinEntity entityInput00 = new WkTblPartnerPersonAddMinEntity();
        entityInput00.setWkTblPartnerPersonAddMinId(839);
        UpdateWkTblMinPersonCapsuleDto capsuleDto00 = new UpdateWkTblMinPersonCapsuleDto();
        capsuleDto00.setWkTblPartnerPersonAddMinEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkMasterMinPersonService.practice(capsuleDto00).getWkTblPartnerPersonAddMinId());

        final Integer callId = 218;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblMinPersonCapsuleDto capsuleDto01 = new UpdateWkTblMinPersonCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerPersonAddMinEntity entityInput01 = wkTblPartnerPersonAddMinRepository.findById(callId).get();
        WkTblPartnerPersonAddMinEntity entityBase = new WkTblPartnerPersonAddMinEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblPartnerPersonAddMinEntity(entityBase);

        Integer newId = registBulkMasterMinPersonService.practice(capsuleDto01).getWkTblPartnerPersonAddMinId();
        assertNotEquals(0, newId);
        WkTblPartnerPersonAddMinEntity entityInput02 = wkTblPartnerPersonAddMinRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblPartnerPersonAddMinEntity entityCopy = wkTblPartnerPersonAddMinRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblPartnerPersonAddMinCode(), entityCopy.getWkTblPartnerPersonAddMinCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
