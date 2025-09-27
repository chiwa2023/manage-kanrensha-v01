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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserRiyoushaCombineEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserRiyoushaCombineRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertCombineUserRiyoushaLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertCombineUserRiyoushaLogicTest {

    /** テスト対象 */
    @Autowired
    private InsertCombineUserRiyoushaLogic insertCombineUserRiyoushaLogic;

    /** ユーザ利用者紐づけRepository */
    @Autowired
    private UserRiyoushaCombineRepository userRiyoushaCombineRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        final String role = "manager";

        final Integer userCode = 821;
        final Integer riyoushaCode = 3394;

        Integer newId = insertCombineUserRiyoushaLogic.practcie(userCode, role, riyoushaCode, userDto);

        Optional<UserRiyoushaCombineEntity> optional = userRiyoushaCombineRepository.findById(newId);
        assertFalse(optional.isEmpty());

        UserRiyoushaCombineEntity entity = optional.get();

        assertEquals(userCode, entity.getUseUserCode());
        assertEquals(role, entity.getRole());
        assertEquals(riyoushaCode, entity.getRiyoushaCode());
        assertEquals(userDto.getUserPersonCode(), entity.getInsertUserCode(), "登録データと操作者が間違いなく別である");
    }

}
