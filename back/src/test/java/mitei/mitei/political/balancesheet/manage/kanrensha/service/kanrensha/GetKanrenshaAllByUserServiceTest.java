package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaAllByUserResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetKanrenshaAllByUserService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetKanrenshaAllByUserServiceTest.sql")
@Transactional
class GetKanrenshaAllByUserServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetKanrenshaAllByUserService getKanrenshaAllByUserService;

    @Test
    @Tag("TableTruncate") // NOPMD
    void test() throws Exception {

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add(UserRoleConstants.ROLE_PARTNER_PERSON);
        userDto.getListRoles().add(UserRoleConstants.ROLE_PARTNER_CORP);
        userDto.getListRoles().add(UserRoleConstants.ROLE_PARTNER_POLI_ORG);

        GetKanrenshaAllByUserResultDto resultDto = getKanrenshaAllByUserService.practice(userDto);

        // 個人
        assertEquals(825, resultDto.getMasterPersonEntity().getMasterPersonId());

        // 企業団体
        List<MasterCorporationEntity> listCorp = resultDto.getListCorpEntity();
        assertEquals(2, listCorp.size());
        MasterCorporationEntity entityCorp00 = listCorp.get(0);
        assertEquals(191, entityCorp00.getMasterCorporationId());
        MasterCorporationEntity entityCorp01 = listCorp.get(1);
        assertEquals(192, entityCorp01.getMasterCorporationId());

        // 政治団体
        List<MasterPoliticalOrganizationEntity> listPoliOrg = resultDto.getListPoliOrgEntity();
        assertEquals(2, listPoliOrg.size());
        MasterPoliticalOrganizationEntity entityPoliOrg00 = listPoliOrg.get(0);
        assertEquals(724, entityPoliOrg00.getMasterPoliticalOrganizationId());
        MasterPoliticalOrganizationEntity entityPoliOrg01 = listPoliOrg.get(1);
        assertEquals(725, entityPoliOrg01.getMasterPoliticalOrganizationId());
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testEmpty() throws Exception {

        // あえてロールを付加しない
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();

        // 例外でないが失敗で戻る
        GetKanrenshaAllByUserResultDto resultDto = getKanrenshaAllByUserService.practice(userDto);
        assertTrue(resultDto.getIsFailure());
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testCombineDuplicate() throws Exception {
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add(UserRoleConstants.ROLE_PARTNER_PERSON);

        userDto.setUserPersonCode(298);
        assertThrows(DataRetrievalFailureException.class, () -> getKanrenshaAllByUserService.practice(userDto));

        userDto.setUserPersonCode(325);
        assertThrows(DataRetrievalFailureException.class, () -> getKanrenshaAllByUserService.practice(userDto));
    }

}
