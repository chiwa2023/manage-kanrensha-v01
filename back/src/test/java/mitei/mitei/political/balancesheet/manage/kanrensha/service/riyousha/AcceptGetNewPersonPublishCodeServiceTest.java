package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendAcceptCodeResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AcceptGetNewPersonPublishCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AcceptGetNewPersonPublishCodeServiceTest.sql")
class AcceptGetNewPersonPublishCodeServiceTest {
    // CHECKSTYLE:OFF MAgicNumber

    /** テスト対象 */
    @Autowired
    private AcceptGetNewPersonPublishCodeService acceptGetNewPersonPublishCodeService;

    @Test
    void test() throws Exception {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();

        SendAcceptCodeResultDto resultDto = acceptGetNewPersonPublishCodeService.practice(userDto);
        List<RiyoushaInviteNewEntity> list = resultDto.getListAcceptCode();

        assertEquals(1, list.size());

        RiyoushaInviteNewEntity entity = list.get(0);
        assertEquals("ROLE_manager", entity.getDantaiRole());
        assertEquals("bbb@politician.balanse.report.net", entity.getMailAddress());
        assertEquals(196, entity.getPersonUserId());
        assertEquals(190, entity.getPersonUserCode());
        assertEquals("たろー", entity.getPersonUserName());
        assertEquals(323, entity.getRiyoushaDantaiId());
        assertEquals(320, entity.getRiyoushaDantaiCode());
        assertEquals("管理者　花子", entity.getRiyoushaDantaiName());
        assertEquals("9z8y7x-6v5w", entity.getRegistCode());

    }

}
