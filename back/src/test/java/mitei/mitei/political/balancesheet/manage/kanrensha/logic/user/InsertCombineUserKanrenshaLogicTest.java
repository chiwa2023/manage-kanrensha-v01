package mitei.mitei.political.balancesheet.manage.kanrensha.logic.user;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserKanrenshaCombineEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserKanrenshaCombineRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertCombineUserKanrenshaLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertCombineUserKanrenshaLogicTest {

    /** テスト対象 */
    @Autowired
    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

    /** ユーザ利用者紐づけRepository */
    @Autowired
    private UserKanrenshaCombineRepository userKanrenshaCombineRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();

        final Short kanrenshaKbn = KanrenshaKbnConstants.PERSON;

        final Integer userCode = 821;
        final String kanrenshaCode = "1234-555";

        Integer newId = insertCombineUserKanrenshaLogic.practcie(userCode, kanrenshaKbn, kanrenshaCode, userDto);

        Optional<UserKanrenshaCombineEntity> optional = userKanrenshaCombineRepository.findById(newId);
        assertFalse(optional.isEmpty());

        UserKanrenshaCombineEntity entity = optional.get();

        assertEquals(userCode, entity.getUseUserCode());
        assertEquals(kanrenshaKbn, entity.getKanrenshaKbn());
        assertEquals(kanrenshaCode, entity.getKanrenshaCode());
        assertEquals(userDto.getUserPersonCode(), entity.getInsertUserCode(), "登録データと操作者が間違いなく別である");
    }

}
