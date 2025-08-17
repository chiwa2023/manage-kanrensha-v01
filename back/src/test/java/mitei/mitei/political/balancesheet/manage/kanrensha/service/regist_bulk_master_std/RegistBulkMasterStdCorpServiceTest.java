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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkMasterStdCorpService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkMasterStdCorpServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterStdCorpService registBulkMasterStdCorpService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterCorpRepository wkTblMasterCorpRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_master_corp.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblMasterCorpEntity entityInput00 = new WkTblMasterCorpEntity();
        entityInput00.setWkTblMasterCorpId(839);
        UpdateWkTblStdCorpCapsuleDto capsuleDto00 = new UpdateWkTblStdCorpCapsuleDto();
        capsuleDto00.setWkTblMasterCorpEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkMasterStdCorpService.practice(capsuleDto00).getWkTblMasterCorpId());

        final Integer callId = 412;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblStdCorpCapsuleDto capsuleDto01 = new UpdateWkTblStdCorpCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblMasterCorpEntity entityInput01 = wkTblMasterCorpRepository.findById(callId).get();
        WkTblMasterCorpEntity entityBase = new WkTblMasterCorpEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAddressBlock("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblMasterCorpEntity(entityBase);

        Integer newId = registBulkMasterStdCorpService.practice(capsuleDto01).getWkTblMasterCorpId();
        assertNotEquals(0, newId);
        WkTblMasterCorpEntity entityInput02 = wkTblMasterCorpRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblMasterCorpEntity entityCopy = wkTblMasterCorpRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblMasterCorpCode(), entityCopy.getWkTblMasterCorpCode());
        assertEquals(entityBase.getAddressBlock(), entityCopy.getAddressBlock());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
