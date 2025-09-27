package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendInviteCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaInviteNewRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InviteNewPersonPublishCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("InviteNewPersonPublishCodeServiceTest.sql")
class InviteNewPersonPublishCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InviteNewPersonPublishCodeService inviteNewPersonPublishCodeService;

    /** 利用者組織個人紐づけ用コードRepository */
    @Autowired
    private RiyoushaInviteNewRepository riyoushaInviteNewRepository;

    @Test
    @Tag("TableTruncate") // NOPMD 
    void testComrade() throws Exception {

        SendInviteCodeCapsuleDto capsuleDto = new SendInviteCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setOrgId(227);
        capsuleDto.setOrgRole(UserRoleConstants.ROLE_COMRADE);
        capsuleDto.setPersonMail("aaa@politician.balanse.report.net");

        Integer newId = inviteNewPersonPublishCodeService.practice(capsuleDto);

        assertNotEquals(0, newId);

        RiyoushaInviteNewEntity entity = riyoushaInviteNewRepository.findById(newId).get();

        assertEquals(capsuleDto.getOrgRole(), entity.getDantaiRole());
        assertEquals(capsuleDto.getPersonMail(), entity.getMailAddress());
        assertNotNull(entity.getRegistCode());
        // 89,81,'たろー'
        assertEquals(89, entity.getPersonUserId());
        assertEquals(81, entity.getPersonUserCode());
        assertEquals("たろー", entity.getPersonUserName());

        assertEquals(227, entity.getRiyoushaDantaiId());
        assertEquals(220, entity.getRiyoushaDantaiCode());
        assertEquals("APIユーザ　花子", entity.getRiyoushaDantaiName());
    }

    @Test
    @Tag("TableTruncate")
    void testManager() throws Exception {

        SendInviteCodeCapsuleDto capsuleDto = new SendInviteCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setOrgId(313);
        capsuleDto.setOrgRole(UserRoleConstants.ROLE_MANAGER);
        capsuleDto.setPersonMail("aaa@politician.balanse.report.net");

        Integer newId = inviteNewPersonPublishCodeService.practice(capsuleDto);

        assertNotEquals(0, newId);

        RiyoushaInviteNewEntity entity = riyoushaInviteNewRepository.findById(newId).get();

        assertEquals(capsuleDto.getOrgRole(), entity.getDantaiRole());
        assertEquals(capsuleDto.getPersonMail(), entity.getMailAddress());
        assertNotNull(entity.getRegistCode());
        // 89,81,'たろー'
        assertEquals(89, entity.getPersonUserId());
        assertEquals(81, entity.getPersonUserCode());
        assertEquals("たろー", entity.getPersonUserName());

        assertEquals(313, entity.getRiyoushaDantaiId());
        assertEquals(310, entity.getRiyoushaDantaiCode());
        assertEquals("運営者　花子", entity.getRiyoushaDantaiName());
    }

    @Test
    @Tag("TableTruncate")
    void testAdmin() throws Exception {

        SendInviteCodeCapsuleDto capsuleDto = new SendInviteCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setOrgId(467);
        capsuleDto.setOrgRole(UserRoleConstants.ROLE_ADMIN);
        capsuleDto.setPersonMail("aaa@politician.balanse.report.net");

        Integer newId = inviteNewPersonPublishCodeService.practice(capsuleDto);

        assertNotEquals(0, newId);

        RiyoushaInviteNewEntity entity = riyoushaInviteNewRepository.findById(newId).get();

        assertEquals(capsuleDto.getOrgRole(), entity.getDantaiRole());
        assertEquals(capsuleDto.getPersonMail(), entity.getMailAddress());
        assertNotNull(entity.getRegistCode());
        // 89,81,'たろー'
        assertEquals(89, entity.getPersonUserId());
        assertEquals(81, entity.getPersonUserCode());
        assertEquals("たろー", entity.getPersonUserName());

        assertEquals(467, entity.getRiyoushaDantaiId());
        assertEquals(193, entity.getRiyoushaDantaiCode());
        assertEquals("管理者　花子", entity.getRiyoushaDantaiName());
    }

    @Test
    @Tag("TableTruncate")
    void testExceptionCallperson() throws Exception {

        SendInviteCodeCapsuleDto capsuleDto = new SendInviteCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setOrgId(467);
        capsuleDto.setOrgRole(UserRoleConstants.ROLE_ADMIN);
        capsuleDto.setPersonMail("12345@example.net");

        assertThrows(EmptyResultDataAccessException.class, () -> inviteNewPersonPublishCodeService.practice(capsuleDto),
                "メアドが存在しないのでユーザが呼べない");
    }

    @Test
    @Tag("TableTruncate")
    void testExceptionCallDantai() throws Exception {

        SendInviteCodeCapsuleDto capsuleDto = new SendInviteCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setOrgId(467);
        capsuleDto.setOrgRole(UserRoleConstants.ROLE_MANAGER);
        capsuleDto.setPersonMail("12345@example.net");

        assertThrows(EmptyResultDataAccessException.class, () -> inviteNewPersonPublishCodeService.practice(capsuleDto),
                "団体が存在しない(権限が誤っている)ので団体が呼べない");
    }

}
