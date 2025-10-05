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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateApprovalKanrenshaCorpAddressLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("UpdateApprovalKanrenshaCorpAddressLogicTest.sql")
class UpdateApprovalKanrenshaCorpAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateApprovalKanrenshaCorpAddressLogic updateApprovalKanrenshaCorpAddressLogic;

    /** 関連者個人住所Repository */
    @Autowired
    private MasterCorporationAddressRepository masterCorporationAddressRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MasterCorporationAddressEntity entityAddress = masterCorporationAddressRepository.findById(2203).get();

        MasterKanrenshaAddressBaseEntity entity = new MasterKanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddress, entity);
        entity.setKanrenshaAddressId(entityAddress.getMasterCorporationAddressId());
        // entity.setKanrenshaCode(entityAddress.getcoCorporationKanrenshaCode());

        entity.setAddressBuilding("四角マンション3F");

        Integer updateCount = updateApprovalKanrenshaCorpAddressLogic.practice(entity,
                CreateLeastUserForTestUtil.practice());

        assertEquals(1, updateCount);

        MasterCorporationAddressEntity entityNew = masterCorporationAddressRepository.findById(2204).get();
        assertEquals(entity.getAddressBuilding(), entityNew.getAddressBuilding());
    }
}
