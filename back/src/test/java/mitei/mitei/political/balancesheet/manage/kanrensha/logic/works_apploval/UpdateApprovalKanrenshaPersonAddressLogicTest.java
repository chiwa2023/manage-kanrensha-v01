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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateApprovalKanrenshaPersonAddressLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("UpdateApprovalKanrenshaPersonAddressLogicTest.sql")
class UpdateApprovalKanrenshaPersonAddressLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateApprovalKanrenshaPersonAddressLogic updateApprovalKanrenshaPersonAddressLogic;

    /** 関連者個人住所Repository */
    @Autowired
    private MasterPersonAddressRepository masterPersonAddressRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MasterPersonAddressEntity entityAddress = masterPersonAddressRepository.findById(103).get();

        MasterKanrenshaAddressBaseEntity entity = new MasterKanrenshaAddressBaseEntity();
        BeanUtils.copyProperties(entityAddress, entity);
        entity.setKanrenshaAddressId(entityAddress.getMasterPersonAddressId());
        // entity.setKanrenshaCode(entityAddress.getcoPersonKanrenshaCode());

        entity.setAddressBuilding("四角マンション3F");

        Integer updateCount = updateApprovalKanrenshaPersonAddressLogic.practice(entity,
                CreateLeastUserForTestUtil.practice());

        assertEquals(1, updateCount);

        MasterPersonAddressEntity entityNew = masterPersonAddressRepository.findById(104).get();
        assertEquals(entity.getAddressBuilding(), entityNew.getAddressBuilding());
    }

}
