package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ConvertWkTblXmlToMasterPersonLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertWkTblXmlToMasterPersonLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertWkTblXmlToMasterPersonLogic convertWkTblXmlToMasterPersonLogic;

    /** 個人マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPersonAddMinRepository wkTblPartnerPersonAddMinRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_partner_person_add_min.sql")
    void test() {

        WkTblMasterAllByXmlEntity allByXmlEntity = new WkTblMasterAllByXmlEntity();

        allByXmlEntity.setPartnerName("超元素製造組合");
        allByXmlEntity.setAllAddress("山形県実在市湖畔町");
        allByXmlEntity.setPersonShokugyou("団体役員");

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();

        int newId = convertWkTblXmlToMasterPersonLogic.practice(allByXmlEntity, userDto);
        assertNotEquals(0, newId);

        WkTblPartnerPersonAddMinEntity minEntity = wkTblPartnerPersonAddMinRepository.findById(newId).get();

        assertEquals(allByXmlEntity.getPartnerName(), minEntity.getPartnerName());
        assertEquals(allByXmlEntity.getAllAddress(), minEntity.getAllAddress());
        assertEquals(allByXmlEntity.getPersonShokugyou(), minEntity.getPersonShokugyou());
        assertEquals(false, minEntity.getIsFinish());
    }

}
