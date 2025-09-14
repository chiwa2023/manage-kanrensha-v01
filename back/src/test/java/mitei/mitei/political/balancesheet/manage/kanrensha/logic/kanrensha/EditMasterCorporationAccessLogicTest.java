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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterCorporationAccessLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EditMasterCorporationAccessLogicTest {

    /** テスト対象 */
    @Autowired
    private EditMasterCorporationAccessLogic editMasterCorporationAccessLogic;

    /** マスタ企業団体連絡先レポジトリ */
    @Autowired
    private MasterCorporationAccessRepository masterCorporationAccessRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("EditMasterCorporationAccessLogicTest.sql")
    void test() throws Exception {

        final Integer oldId = 2601;
        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setAccessId(oldId);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト法人");
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setSnsAccount("@corp_updated"); // 画面から編集
        kanrenshaCorpDto.setInputAccessDto(inputAccessDto);

        SaveKanrenshaCorpCapsuleDto capsuleDto = new SaveKanrenshaCorpCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaCorpDto(kanrenshaCorpDto);

        Integer newId = editMasterCorporationAccessLogic.practice(capsuleDto);

        Optional<MasterCorporationAccessEntity> optional = masterCorporationAccessRepository.findById(newId);
        assertFalse(optional.isEmpty());
        MasterCorporationAccessEntity accessEntity = optional.get();
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount());

        assertEquals(false, masterCorporationAccessRepository.findById(oldId).get().getIsLatest());
    }

}
