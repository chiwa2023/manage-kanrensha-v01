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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkHistoryPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkHistoryPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkHistoryPersonService registBulkHistoryPersonService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTblPartnerPersonHistoryRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_person_history.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblPartnerPersonHistoryEntity entityInput00 = new WkTblPartnerPersonHistoryEntity();
        entityInput00.setWkPartnerPersonHistoryId(839);
        UpdateWkTblHistoryPersonCapsuleDto capsuleDto00 = new UpdateWkTblHistoryPersonCapsuleDto();
        capsuleDto00.setWkTblPartnerPersonHistoryEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkHistoryPersonService.practice(capsuleDto00).getWkPartnerPersonHistoryId());

        final Integer callId = 98;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblHistoryPersonCapsuleDto capsuleDto01 = new UpdateWkTblHistoryPersonCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerPersonHistoryEntity entityInput01 = wkTblPartnerPersonHistoryRepository.findById(callId).get();
        WkTblPartnerPersonHistoryEntity entityBase = new WkTblPartnerPersonHistoryEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblPartnerPersonHistoryEntity(entityBase);

        Integer newId = registBulkHistoryPersonService.practice(capsuleDto01).getWkPartnerPersonHistoryId();
        assertNotEquals(0, newId);
        WkTblPartnerPersonHistoryEntity entityInput02 = wkTblPartnerPersonHistoryRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblPartnerPersonHistoryEntity entityCopy = wkTblPartnerPersonHistoryRepository.findById(newId).get();
        assertEquals(entityBase.getWkPartnerPersonHistoryCode(), entityCopy.getWkPartnerPersonHistoryCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
