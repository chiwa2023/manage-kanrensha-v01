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

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.HoujinShubetsuConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertKanrenshaCorpService単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertKanrenshaCorpServiceTest {

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaCorpService insertKanrenshaCorpService;

    /** 企業団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterCorporationAccessRepository masterCorporationAccessRepository;

    /** 企業団体マスタ住所Repository */
    @Autowired
    private MasterCorporationAddressRepository masterCorporationAddressRepository;

    /** 企業団体マスタ基本Repository */
    @Autowired
    private MasterCorporationBaseRepository masterCorporationBaseRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private MasterCorporationPropertyRepository masterCorporationPropertyRepository;

    /** 企業団体履歴マスタRepository */
    @Autowired
    private PartnerCorpHistory45Repository partnerCorpHistory45Repository;

    @Test
    @Tag("TableTruncate")
    @Sql({ "delete_master_corporation.sql", "delete_master_corporation_address.sql",
            "delete_master_corporation_access.sql", "delete_master_corporation_base.sql",
            "delete_master_corporation_property.sql", "delete_history_corp.sql" })
    void test() throws Exception { // NOPMD

        SaveKanrenshaCorpCapsuleDto capsuleDto = new SaveKanrenshaCorpCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        // DTOの準備
        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setHoujinNo("1234567");
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト株式会社");
        inputOrgNameDto.setOrgNameKana("テストカブシキガイシャ");
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostal1("880");
        inputAddressDto.setPostal2("8501");
        inputAddressDto.setAddressAll("宮崎県架空市橘通東２丁目１０−１");
        inputAddressDto.setAddressPostal("宮崎県架空市橘通東");
        inputAddressDto.setAddressBlock("２丁目１０−１");
        inputAddressDto.setAddressBuilding("宮崎県庁");
        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("324");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        kanrenshaCorpDto.setInputAddressDto(inputAddressDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("test@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");
        kanrenshaCorpDto.setInputAccessDto(inputAccessDto);

        InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        orgDelegateLeastDto.setPersonKanrenshaCode("P12345");
        orgDelegateLeastDto.setPersonName("代表者　太郎");
        kanrenshaCorpDto.setOrgDelegateLeastDto(orgDelegateLeastDto);

        kanrenshaCorpDto.setHoujinSbts(HoujinShubetsuConstants.GAIKOKU_KAISHA); // 外国籍
        kanrenshaCorpDto.setIsShiten(false);

        capsuleDto.setKanrenshaCorpDto(kanrenshaCorpDto);

        // 実行
        Integer newId = insertKanrenshaCorpService.practice(capsuleDto);
        assertNotEquals(0, newId);

        // --- 検証 ---

        // MasterCorporationEntity
        List<MasterCorporationEntity> listMaster = masterCorporationRepository.findAll();
        assertEquals(1, listMaster.size());
        MasterCorporationEntity masterEntity = listMaster.get(0);
        assertEquals(inputOrgNameDto.getOrgName(), masterEntity.getPartnerName());
        assertEquals(inputAddressDto.getAddressAll(), masterEntity.getAllAddress());
        assertEquals(kanrenshaCorpDto.getHoujinNo(), masterEntity.getHoujinNo());
        assertEquals(kanrenshaCorpDto.getOrgDelegateLeastDto().getPersonName(), masterEntity.getCorpDelegate());
        String newCode = masterEntity.getCorpKanrenshaCode();

        // MasterCorporationAddressEntity
        List<MasterCorporationAddressEntity> listAddress = masterCorporationAddressRepository.findAll();
        assertEquals(1, listAddress.size());
        MasterCorporationAddressEntity addressEntity = listAddress.get(0);
        assertEquals(newCode, addressEntity.getCorpKanrenshaCode());
        assertEquals(inputAddressDto.getPostal1(), addressEntity.getPostal1());
        assertEquals(inputAddressDto.getPostal2(), addressEntity.getPostal2());
        assertEquals(inputAddressDto.getAddressPostal(), addressEntity.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), addressEntity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), addressEntity.getAddressBuilding());
        assertEquals(inputAddressDto.getLgCode(), addressEntity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), addressEntity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), addressEntity.getBlkId());
        assertEquals(inputAddressDto.getRsdtId(), addressEntity.getRsdtId());
        assertEquals(inputAddressDto.getIsPostalEdit(), addressEntity.getIsPostalEdit());
        assertEquals(inputAddressDto.getIsBlockEdit(), addressEntity.getIsBlockEdit());
        assertEquals(inputAddressDto.getIsBuildingEdit(), addressEntity.getIsBlockEdit());

        // MasterCorporationAccessEntity
        List<MasterCorporationAccessEntity> listAccess = masterCorporationAccessRepository.findAll();
        assertEquals(1, listAccess.size());
        MasterCorporationAccessEntity accessEntity = listAccess.get(0);
        assertEquals(newCode, accessEntity.getCorpKanrenshaCode());
        assertEquals(inputAccessDto.getPhon1(), accessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), accessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), accessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), accessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), accessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), accessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), accessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount());

        // MasterCorporationBaseEntity
        List<MasterCorporationBaseEntity> listBase = masterCorporationBaseRepository.findAll();
        assertEquals(1, listBase.size());
        MasterCorporationBaseEntity baseEntity = listBase.get(0);
        assertEquals(newCode, baseEntity.getCorpKanrenshaCode());
        assertEquals(inputOrgNameDto.getOrgNameKana(), baseEntity.getOrgNameKana());
        assertEquals(kanrenshaCorpDto.getIsShiten(), baseEntity.getIsShiten());
        assertEquals(orgDelegateLeastDto.getPersonKanrenshaCode(), baseEntity.getOrgDelegateCode());

        // MasterCorporationPropertyEntity
        List<MasterCorporationPropertyEntity> listProperty = masterCorporationPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        MasterCorporationPropertyEntity propertyEntity = listProperty.get(0);
        assertEquals(newCode, propertyEntity.getCorpKanrenshaCode());
        assertEquals(kanrenshaCorpDto.getHoujinSbts(), propertyEntity.getHoujinSbts());
        assertEquals(true, propertyEntity.getIsForeign()); // 法人種別選択による自動修正

        // PartnerCorpHistory45Entity (宮崎県のlgCodeのため)
        List<PartnerCorpHistory45Entity> listHistory = partnerCorpHistory45Repository.findAll();
        assertEquals(1, listHistory.size());
        PartnerCorpHistory45Entity historyEntity = listHistory.get(0);
        assertEquals(newCode, historyEntity.getCorpKanrenshaCode());
        assertEquals(masterEntity.getPartnerName(), historyEntity.getPartnerName());
        assertEquals(masterEntity.getAllAddress(), historyEntity.getAllAddress());
    }
}
