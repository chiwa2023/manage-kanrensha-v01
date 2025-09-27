package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.PickupOrgSelectOptionResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PickupRiyoushaOrgOptionManagerService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("PickupRiyoushaOrgOptionManagerServiceTest.sql")
class PickupRiyoushaOrgOptionManagerServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PickupRiyoushaOrgOptionManagerService pickupRiyoushaOrgOptionManagerService;

    @Test
    @Tag("TableTruncate")
    void testNew() throws Exception {

        PickupOrgSelectOptionResultDto resultDto = pickupRiyoushaOrgOptionManagerService
                .practice(CreateLeastUserForTestUtil.practice());

        List<SelectOptionIntegerDto> list = resultDto.getListOrgOptions();
        assertEquals(1, list.size());
        assertEquals("新規追加", list.get(0).getText());
    }

    @Test
    @Tag("TableTruncate")
    void testMulti() throws Exception {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.setUserPersonCode(813);

        PickupOrgSelectOptionResultDto resultDto = pickupRiyoushaOrgOptionManagerService.practice(userDto);

        List<SelectOptionIntegerDto> list = resultDto.getListOrgOptions();
        assertEquals(2, list.size());
        assertEquals(323, list.get(0).getValue());
        assertEquals(431, list.get(1).getValue());
    }

    @Test
    @Tag("TableTruncate")
    void testNotCallUser() throws Exception {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.setUserPersonCode(643);

        assertThrows(EmptyResultDataAccessException.class,
                () -> pickupRiyoushaOrgOptionManagerService.practice(userDto));
    }

}
