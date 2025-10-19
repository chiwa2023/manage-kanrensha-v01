package mitei.mitei.political.balancesheet.manage.kanrensha.logic.works_apploval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateApprovalKanrenshaPoliOrgAddressLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("UpdateApprovalKanrenshaPoliOrgAddressLogicTest.sql")
class UpdateApprovalKanrenshaPoliOrgAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateApprovalKanrenshaPoliOrgAddressLogic updateApprovalKanrenshaPoliOrgAddressLogic;

    /** 関連者政治団体住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MasterPoliticalOrganizationAddressEntity entityAddress = masterPoliticalOrganizationAddressRepository
                .findById(703).get();

        MasterKanrenshaAddressBaseEntity entity = new MasterKanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddress, entity);
        entity.setKanrenshaAddressId(entityAddress.getMasterPoliticalOrganizationAddressId());
        // entity.setKanrenshaCode(entityAddress.getcoPoliticalOrganizationKanrenshaCode());

        entity.setAddressBuilding("四角マンション3F");

        Integer updateCount = updateApprovalKanrenshaPoliOrgAddressLogic.practice(entity,
                CreateLeastUserForTestUtil.practice());

        assertEquals(1, updateCount);

        MasterPoliticalOrganizationAddressEntity entityNew = masterPoliticalOrganizationAddressRepository.findById(704)
                .get();
        assertEquals(entity.getAddressBuilding(), entityNew.getAddressBuilding());
    }

}
