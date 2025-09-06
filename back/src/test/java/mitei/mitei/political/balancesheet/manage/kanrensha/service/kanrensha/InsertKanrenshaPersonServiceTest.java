package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertKanrenshaPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertKanrenshaPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaPersonService insertKanrenshaPersonService;

    /** 企業団体マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonAccessRepository masterPersonAccessRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonAddressRepository masterPersonAddressRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private MasterPersonPropertyRepository masterPersonPropertyRepository;

    /** 企業団体マスタRepository */
    @Autowired
    private PartnerPersonHistory45Repository partnerPersonHistory45Repository;

    @Test
    @Tag("TableTruncate")
    @Sql({ "delete_master_person.sql", "delete_master_person_address.sql", "delete_master_person_access.sql",
            "delete_master_person_address.sql", "delete_master_person_base.sql", "delete_master_person_property.sql",
            "delete_hsitory_person.sql" })
    void test() throws Exception { // NOPMD 

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostal1("100");
        inputAddressDto.setPostal2("0001");
        inputAddressDto.setAddressAll("宮崎県実在市山麓町");
        inputAddressDto.setOrginAddressAll("宮崎県実在市");
        inputAddressDto.setAddressPostal("宮崎県実在市山麓町");
        inputAddressDto.setAddressBlock("1丁目75番地");
        inputAddressDto.setAddressBuilding("四角ビル3F");

        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("131016");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("03");
        inputAccessDto.setPhon2("1234");
        inputAccessDto.setPhon3("5678");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        // 基本
        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");

        InputShokugyouDto inputShokugyouDto = new InputShokugyouDto();
        inputShokugyouDto.setAllShokugyou("水産業団体役員");
        inputShokugyouDto.setGyoushu("水産業");
        inputShokugyouDto.setYakushoku("役職者");
        inputShokugyouDto.setShokugyouUserWrite("水産業団体役員");
        inputShokugyouDto.setCorpNo("9876543210987");
        inputShokugyouDto.setCorpName("大漁水産");
        inputShokugyouDto.setCorpAddress("山形県架空市海辺町");

        // 属性
        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setInputAddressDto(inputAddressDto);
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);
        kanrenshaPersonDto.setInputShokugyouDto(inputShokugyouDto);
        kanrenshaPersonDto.setIsForeign(true);

        capsuleDto.setKanrenshaPersonDto(kanrenshaPersonDto);

        Integer newId = insertKanrenshaPersonService.practice(capsuleDto);
        assertNotEquals(0, newId);

        // 0件から1件登録
        List<MasterPersonEntity> listMaster = masterPersonRepository.findAll();
        assertEquals(1, listMaster.size());
        MasterPersonEntity masterPersonEntity = listMaster.get(0);
        assertEquals(inputPersonNameDto.getAllName(), masterPersonEntity.getPartnerName());
        assertEquals(inputAddressDto.getAddressAll(), masterPersonEntity.getAllAddress());
        assertEquals(inputShokugyouDto.getAllShokugyou(), masterPersonEntity.getPersonShokugyou());
        String newCode = masterPersonEntity.getPersonKanrenshaCode();

        List<MasterPersonAddressEntity> listAddress = masterPersonAddressRepository.findAll();
        assertEquals(1, listAddress.size());

        MasterPersonAddressEntity masterPersonAddressEntity = listAddress.get(0);

        assertEquals(newCode, masterPersonAddressEntity.getPersonKanrenshaCode());
        assertEquals(inputAddressDto.getPostal1(), masterPersonAddressEntity.getPostal1());
        assertEquals(inputAddressDto.getPostal2(), masterPersonAddressEntity.getPostal2());
        assertEquals(inputAddressDto.getAddressPostal(), masterPersonAddressEntity.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), masterPersonAddressEntity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), masterPersonAddressEntity.getAddressBuilding());
        assertEquals(inputAddressDto.getLgCode(), masterPersonAddressEntity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), masterPersonAddressEntity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), masterPersonAddressEntity.getBlkId());
        assertEquals(inputAddressDto.getRsdtId(), masterPersonAddressEntity.getRsdtId());
        assertEquals(inputAddressDto.getIsPostalEdit(), masterPersonAddressEntity.getIsPostalEdit());
        assertEquals(inputAddressDto.getIsBlockEdit(), masterPersonAddressEntity.getIsBlockEdit());
        assertEquals(inputAddressDto.getIsBuildingEdit(), masterPersonAddressEntity.getIsBlockEdit());

        List<MasterPersonAccessEntity> listAccess = masterPersonAccessRepository.findAll();
        assertEquals(1, listAccess.size());

        MasterPersonAccessEntity masterPersonAccessEntity = listAccess.get(0);

        assertEquals(newCode, masterPersonAccessEntity.getPersonKanrenshaCode());
        assertEquals(inputAccessDto.getPhon1(), masterPersonAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), masterPersonAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), masterPersonAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), masterPersonAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), masterPersonAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), masterPersonAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), masterPersonAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), masterPersonAccessEntity.getSnsAccount());

        List<MasterPersonBaseEntity> listBase = masterPersonBaseRepository.findAll();
        assertEquals(1, listBase.size());
        MasterPersonBaseEntity masterPersonBaseEntity = listBase.get(0);

        // 個人姓名
        assertEquals(newCode, masterPersonBaseEntity.getPersonKanrenshaCode());
        assertEquals(inputPersonNameDto.getLastName(), masterPersonBaseEntity.getLastName());
        assertEquals(inputPersonNameDto.getFirstName(), masterPersonBaseEntity.getFirstName());
        assertEquals(inputPersonNameDto.getMiddleName(), masterPersonBaseEntity.getMiddleName());
        assertEquals(inputPersonNameDto.getLastNameKana(), masterPersonBaseEntity.getLastNameKana());
        assertEquals(inputPersonNameDto.getFirstNameKana(), masterPersonBaseEntity.getFirstNameKana());
        assertEquals(inputPersonNameDto.getMiddleNameKana(), masterPersonBaseEntity.getMiddleNameKana());

        // 個人職業
        assertEquals(inputShokugyouDto.getGyoushu(), masterPersonBaseEntity.getGyoushu());
        assertEquals(inputShokugyouDto.getYakushoku(), masterPersonBaseEntity.getYakushoku());
        assertEquals(inputShokugyouDto.getShokugyouUserWrite(), masterPersonBaseEntity.getShokugyouUserWrite());
        assertEquals(inputShokugyouDto.getCorpNo(), masterPersonBaseEntity.getCorpNo());
        assertEquals(inputShokugyouDto.getCorpName(), masterPersonBaseEntity.getCorpName());
        assertEquals(inputShokugyouDto.getCorpAddress(), masterPersonBaseEntity.getCorpAddress());

        // 属性
        List<MasterPersonPropertyEntity> listProperty = masterPersonPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        MasterPersonPropertyEntity propertyEntity = listProperty.get(0);
        assertEquals(newCode, propertyEntity.getPersonKanrenshaCode());
        assertEquals(kanrenshaPersonDto.getIsForeign(), propertyEntity.getIsForeign());

        // 履歴(サンプル住所が宮崎なので45)
        List<PartnerPersonHistory45Entity> listHistory = partnerPersonHistory45Repository.findAll();
        assertEquals(1, listProperty.size());
        PartnerPersonHistory45Entity history45Entity = listHistory.get(0);
        assertEquals(masterPersonEntity.getPartnerName(), history45Entity.getPartnerName());
        assertEquals(masterPersonEntity.getAllAddress(), history45Entity.getAllAddress());
        assertEquals(masterPersonEntity.getPersonShokugyou(), history45Entity.getPersonShokugyou());
        assertEquals(newCode, history45Entity.getPersonKanrenshaCode());

    }

}
