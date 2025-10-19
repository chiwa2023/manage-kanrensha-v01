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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ConvertWkTblXmlToMasterCorpLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertWkTblXmlToMasterCorpLogicTest {

    /** テスト対象 */
    @Autowired
    private ConvertWkTblXmlToMasterCorpLogic convertWkTblXmlToMasterCorpLogic;

    /** 企業マスタ最小登録ワークテーブルRepository */
    @Autowired
    private WkTblPartnerCorpAddMinRepository wkTblPartnerCorpAddMinRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_partner_corp_add_min.sql")
    void test() {

        WkTblMasterAllByXmlEntity allByXmlEntity = new WkTblMasterAllByXmlEntity();

        allByXmlEntity.setPartnerName("超元素製造組合");
        allByXmlEntity.setAllAddress("山形県実在市湖畔町");
        allByXmlEntity.setOrgDelegate("代表者　花子");
        allByXmlEntity.setHoujinNo("12345");

        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();

        int newId = convertWkTblXmlToMasterCorpLogic.practice(allByXmlEntity, userDto);
        assertNotEquals(0, newId);

        WkTblPartnerCorpAddMinEntity minEntity = wkTblPartnerCorpAddMinRepository.findById(newId).get();

        assertEquals(allByXmlEntity.getPartnerName(), minEntity.getPartnerName());
        assertEquals(allByXmlEntity.getAllAddress(), minEntity.getAllAddress());
        assertEquals(allByXmlEntity.getOrgDelegate(), minEntity.getCorpDelegate());
        assertEquals(allByXmlEntity.getHoujinNo(), minEntity.getHoujinNo());
        assertEquals(false, minEntity.getIsFinish());
    }

}
