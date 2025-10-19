package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterPoliticalOrganizationAccessLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EditMasterPoliticalOrganizationAccessLogicTest {

    /** テスト対象 */
    @Autowired
    private EditMasterPoliticalOrganizationAccessLogic editMasterPoliticalOrganizationAccessLogic;

    /** マスタ政治団体連絡先レポジトリ */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository masterPoliticalOrganizationAccessRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("EditMasterPoliticalOrganizationAccessLogicTest.sql")
    void test() throws Exception {

        // 例外・テーブル未更新についてはCallFor...のテストで実施済みなので、ここでは検証しない

        final Integer oldId = 1101;
        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setAccessId(oldId);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト政治団体");
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("012");
        inputAccessDto.setPhon2("345");
        inputAccessDto.setPhon3("6789");
        inputAccessDto.setEmail("org@example.com");
        inputAccessDto.setMyPortalUrl("https://example.com/org");
        inputAccessDto.setSnsServiceName("テストSNS");
        inputAccessDto.setSnsPortalUrl("https://sns.example.com");
        inputAccessDto.setSnsAccount("@org_updated"); // 画面から編集
        kanrenshaPoliOrgDto.setInputAccessDto(inputAccessDto);

        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = new SaveKanrenshaPoliOrgCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaPoliOrgDto(kanrenshaPoliOrgDto);

        Integer newId = editMasterPoliticalOrganizationAccessLogic.practice(capsuleDto);

        // 新たに登録ができていること
        Optional<MasterPoliticalOrganizationAccessEntity> optional = masterPoliticalOrganizationAccessRepository.findById(newId);
        assertFalse(optional.isEmpty());
        MasterPoliticalOrganizationAccessEntity accessEntity = optional.get();
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount());

        // 呼び出し元データは過去履歴に変更されていること
        assertEquals(false, masterPoliticalOrganizationAccessRepository.findById(oldId).get().getIsLatest());
    }

}
