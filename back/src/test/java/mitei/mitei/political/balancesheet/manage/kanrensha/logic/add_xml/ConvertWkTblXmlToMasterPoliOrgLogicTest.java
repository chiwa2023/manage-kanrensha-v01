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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ConvertWkTblXmlToMasterPoliOrgLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertWkTblXmlToMasterPoliOrgLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertWkTblXmlToMasterPoliOrgLogic convertWkTblXmlToMasterPoliOrgLogic;

    /** 政治団体マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinRepository wkTblPartnerPoliOrgAddMinRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_partner_poli_org_add_min.sql")
    void test() {

        WkTblMasterAllByXmlEntity allByXmlEntity = new WkTblMasterAllByXmlEntity();

        allByXmlEntity.setPartnerName("超元素製造組合");
        allByXmlEntity.setAllAddress("山形県実在市湖畔町");
        allByXmlEntity.setOrgDelegate("代表者　花子");
        allByXmlEntity.setDantaiKbn("05");

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();

        int newId = convertWkTblXmlToMasterPoliOrgLogic.practice(allByXmlEntity, userDto);
        assertNotEquals(0, newId);

        WkTblPartnerPoliOrgAddMinEntity minEntity = wkTblPartnerPoliOrgAddMinRepository.findById(newId).get();

        assertEquals(allByXmlEntity.getPartnerName(), minEntity.getPartnerName());
        assertEquals(allByXmlEntity.getAllAddress(), minEntity.getAllAddress());
        assertEquals(allByXmlEntity.getOrgDelegate(), minEntity.getPoliOrgDelegate());
        assertEquals(allByXmlEntity.getDantaiKbn(), minEntity.getDantaiKbn());
        assertEquals(false, minEntity.getIsFinish());
    }

}
