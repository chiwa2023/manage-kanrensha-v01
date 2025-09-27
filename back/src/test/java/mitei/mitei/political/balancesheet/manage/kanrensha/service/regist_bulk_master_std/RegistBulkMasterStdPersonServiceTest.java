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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistBulkMasterStdPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkMasterStdPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistBulkMasterStdPersonService registBulkMasterStdPersonService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterPersonRepository wkTblMasterPersonRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_master_person.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblMasterPersonEntity entityInput00 = new WkTblMasterPersonEntity();
        entityInput00.setWkTblMasterPersonId(839);
        UpdateWkTblStdPersonCapsuleDto capsuleDto00 = new UpdateWkTblStdPersonCapsuleDto();
        capsuleDto00.setWkTblMasterPersonEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registBulkMasterStdPersonService.practice(capsuleDto00).getWkTblMasterPersonId());

        final Integer callId = 296;
        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblStdPersonCapsuleDto capsuleDto01 = new UpdateWkTblStdPersonCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblMasterPersonEntity entityInput01 = wkTblMasterPersonRepository.findById(callId).get();
        WkTblMasterPersonEntity entityBase = new WkTblMasterPersonEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAddressBlock("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblMasterPersonEntity(entityBase);

        Integer newId = registBulkMasterStdPersonService.practice(capsuleDto01).getWkTblMasterPersonId();
        assertNotEquals(0, newId);
        WkTblMasterPersonEntity entityInput02 = wkTblMasterPersonRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblMasterPersonEntity entityCopy = wkTblMasterPersonRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblMasterPersonCode(), entityCopy.getWkTblMasterPersonCode());
        assertEquals(entityBase.getAddressBlock(), entityCopy.getAddressBlock());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());
        assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());
    }

}
