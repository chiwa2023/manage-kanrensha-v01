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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkHistoryCorpService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkHistoryCorpServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkHistoryCorpService registBulkHistoryCorpService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerCorpHistoryRepository wkTblPartnerCorpHistoryRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_corp_history.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblPartnerCorpHistoryEntity entityInput00 = new WkTblPartnerCorpHistoryEntity();
        entityInput00.setWkPartnerCorpHistoryId(839);
        UpdateWkTblHistoryCorpCapsuleDto capsuleDto00 = new UpdateWkTblHistoryCorpCapsuleDto();
        capsuleDto00.setWkTblPartnerCorpHistoryEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkHistoryCorpService.practice(capsuleDto00).getWkPartnerCorpHistoryId());

        final Integer callId = 102;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblHistoryCorpCapsuleDto capsuleDto01 = new UpdateWkTblHistoryCorpCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerCorpHistoryEntity entityInput01 = wkTblPartnerCorpHistoryRepository.findById(callId).get();
        WkTblPartnerCorpHistoryEntity entityBase = new WkTblPartnerCorpHistoryEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblPartnerCorpHistoryEntity(entityBase);

        Integer newId = registBulkHistoryCorpService.practice(capsuleDto01).getWkPartnerCorpHistoryId();
        assertNotEquals(0, newId);
        WkTblPartnerCorpHistoryEntity entityInput02 = wkTblPartnerCorpHistoryRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblPartnerCorpHistoryEntity entityCopy = wkTblPartnerCorpHistoryRepository.findById(newId).get();
        assertEquals(entityBase.getWkPartnerCorpHistoryCode(), entityCopy.getWkPartnerCorpHistoryCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
