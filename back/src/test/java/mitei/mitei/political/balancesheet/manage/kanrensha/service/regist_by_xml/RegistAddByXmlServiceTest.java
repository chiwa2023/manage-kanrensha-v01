package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * RegistAddByXmlService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistAddByXmlServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RegistAddByXmlService registAddByXmlService;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_master_all_by_xml.sql")
    void test() {

        // 存在しないデータを呼び出すと0が戻る
        WkTblMasterAllByXmlEntity entityInput00 = new WkTblMasterAllByXmlEntity();
        entityInput00.setWkTblMasterAllByXmlId(839);
        UpdateWkTblAddByXmlCapsuleDto capsuleDto00 = new UpdateWkTblAddByXmlCapsuleDto();
        capsuleDto00.setWkTblMasterAllByXmlEntity(entityInput00);
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto00.setUserPersonLeastDto(userDto);
        assertEquals(0, registAddByXmlService.practice(capsuleDto00).getWkTblMasterAllByXmlId());

        final Integer callId = 342;

        // 編集内容が追加され、元データが履歴になっている
        UpdateWkTblAddByXmlCapsuleDto capsuleDto01 = new UpdateWkTblAddByXmlCapsuleDto();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblMasterAllByXmlEntity entityInput01 = wkTblMasterAllByXmlRepository.findById(callId).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAllAddress("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblMasterAllByXmlEntity(entityBase);

        Integer newId = registAddByXmlService.practice(capsuleDto01).getWkTblMasterAllByXmlId();
        assertNotEquals(0, newId);
        WkTblMasterAllByXmlEntity entityInput02 = wkTblMasterAllByXmlRepository.findById(callId).get();
        assertEquals(SetTableDataHistoryUtil.DELETE_STATE, entityInput02.getIsLatest());
        WkTblMasterAllByXmlEntity entityCopy = wkTblMasterAllByXmlRepository.findById(newId).get();
        assertEquals(entityBase.getWkTblMasterAllByXmlCode(), entityCopy.getWkTblMasterAllByXmlCode());
        assertEquals(entityBase.getAllAddress(), entityCopy.getAllAddress());
        assertEquals(SetTableDataHistoryUtil.INSERT_STATE, entityCopy.getIsLatest());

        // TODO 判定処理実装後にテストを追加
        // assertEquals("名称が入力されていません;", entityCopy.getJudgeReason());

        fail("Not yet implemented");
    }

}
