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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * EditMasterPersonAccessLogic単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EditMasterPersonAccessLogicTest {

    /** テスト対象 */
    @Autowired
    private EditMasterPersonAccessLogic editMasterPersonAccessLogic;

    /** マスタ個人連絡先レポジトリ */
    @Autowired
    private MasterPersonAccessRepository masterPersonAccessRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("sample_master_person_access.sql")
    void test() throws Exception {

        // 例外・テーブル未更新についてはテスト済みなので、ここでは検証しない

        final Integer oldId = 265;
        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setAccessId(oldId);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金　太郎");
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("012");
        inputAccessDto.setPhon2("345");
        inputAccessDto.setPhon3("6789");
        inputAccessDto.setEmail("taro@jakushou-sns.net");
        inputAccessDto.setMyPortalUrl("https://myblog.com/userid=11");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho.net/index.html");
        inputAccessDto.setSnsAccount("@taro4999"); // 画面から編集
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setKanrenshaPersonDto(kanrenshaPersonDto);

        Integer newId = editMasterPersonAccessLogic.practice(capsuleDto);

        // 新たに登録ができていること
        Optional<MasterPersonAccessEntity> optional = masterPersonAccessRepository.findById(newId);
        assertFalse(optional.isEmpty());
        MasterPersonAccessEntity accessEntity = optional.get();
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount());

        // 呼び出し元データは過去履歴に変更されていること
        assertEquals(false, masterPersonAccessRepository.findById(oldId).get().getIsLatest());
    }

}
