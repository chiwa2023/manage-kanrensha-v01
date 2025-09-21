package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.PickupOrgSelectOptionResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PickupRiyoushaOrgOptionComradeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupRiyoushaOrgOptionComradeServiceTest {

    /** テスト対象 */
    @Autowired
    private PickupRiyoushaOrgOptionComradeService pickupRiyoushaOrgOptionComradeService;

    @Test
    void test() throws Exception {

        PickupOrgSelectOptionResultDto resultDto = pickupRiyoushaOrgOptionComradeService
                .practice(CreateLeastUserForTestUtil.practice());

        List<SelectOptionIntegerDto> list = resultDto.getListOrgOptions();
        System.out.println(list.size());

        System.out.println(list.get(0).getText());

        fail("Not yet implemented");
    }

}
