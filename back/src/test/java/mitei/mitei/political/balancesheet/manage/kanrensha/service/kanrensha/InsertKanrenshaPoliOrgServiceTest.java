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

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.PoliOrgDantaiKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertKanrenshaPoliOrgService単体テスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertKanrenshaPoliOrgServiceTest {

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaPoliOrgService insertKanrenshaPoliOrgService;

    /** 政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 政治団体マスタ連絡先Repository */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository masterPoliticalOrganizationAccessRepository;

    /** 政治団体マスタ住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /** 政治団体マスタ基本Repository */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository masterPoliticalOrganizationBaseRepository;

    /** 政治団体マスタ属性Repository */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository masterPoliticalOrganizationPropertyRepository;

    /** 政治団体履歴マスタRepository */
    @Autowired
    private PartnerPoliOrgHistory45Repository partnerPoliOrgHistory45Repository;

    @Test
    @Tag("TableTruncate")
    @Sql({ "delete_master_political_organizatin.sql", "delete_master_political_organization_address.sql",
            "delete_master_political_organization_access.sql", "delete_master_political_organization_base.sql",
            "delete_master_political_organization_property.sql", "delete_history_poli_org.sql" })
    void test() throws Exception { // NOPMD

        SaveKanrenshaPoliOrgCapsuleDto capsuleDto = new SaveKanrenshaPoliOrgCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        // DTOの準備
        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setDantaiKbn(PoliOrgDantaiKbnConstants.DANTAI_KBN_04);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostalcode1("880");
        inputAddressDto.setPostalcode2("8501");
        inputAddressDto.setAddressAll("宮崎県架空市橘通東２丁目１０−１");
        inputAddressDto.setAddressPostal("宮崎県架空市橘通東");
        inputAddressDto.setAddressBlock("２丁目１０−１");
        inputAddressDto.setAddressBuilding("宮崎県庁");
        inputAddressDto.setLgCode("4507011");
        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("131016");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        kanrenshaPoliOrgDto.setInputAddressDto(inputAddressDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");
        kanrenshaPoliOrgDto.setInputAccessDto(inputAccessDto);

        InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        orgDelegateLeastDto.setPersonKanrenshaCode("P00001");
        orgDelegateLeastDto.setPersonName("代表者　太郎");
        kanrenshaPoliOrgDto.setOrgDelegateLeastDto(orgDelegateLeastDto);

        InputKanrenshaPersonLeastDto accounrMgrLeastDto = new InputKanrenshaPersonLeastDto();
        accounrMgrLeastDto.setPersonKanrenshaCode("P00002");
        accounrMgrLeastDto.setPersonName("会計責任者　花子");
        kanrenshaPoliOrgDto.setAccounrMgrLeastDto(accounrMgrLeastDto);

        capsuleDto.setKanrenshaPoliOrgDto(kanrenshaPoliOrgDto);

        // 実行
        Integer newId = insertKanrenshaPoliOrgService.practice(capsuleDto);
        assertNotEquals(0, newId);

        // --- 検証 ---

        // MasterPoliticalOrganizationEntity
        List<MasterPoliticalOrganizationEntity> listMaster = masterPoliticalOrganizationRepository.findAll();
        assertEquals(1, listMaster.size());
        MasterPoliticalOrganizationEntity masterEntity = listMaster.get(0);
        assertEquals(inputOrgNameDto.getOrgName(), masterEntity.getPartnerName());
        assertEquals(inputAddressDto.getAddressAll(), masterEntity.getAllAddress());
        assertEquals(orgDelegateLeastDto.getPersonName(), masterEntity.getPoliOrgDelegate());
        assertEquals(kanrenshaPoliOrgDto.getDantaiKbn(), masterEntity.getDantaiKbn());

        String newCode = masterEntity.getPoliOrgKanrenshaCode();

        // MasterPoliticalOrganizationAddressEntity
        List<MasterPoliticalOrganizationAddressEntity> listAddress = masterPoliticalOrganizationAddressRepository
                .findAll();
        assertEquals(1, listAddress.size());
        MasterPoliticalOrganizationAddressEntity addressEntity = listAddress.get(0);
        assertEquals(newCode, addressEntity.getPoliOrgKanrenshaCode());
        assertEquals(inputOrgNameDto.getOrgName(), addressEntity.getPartnerName());
        assertEquals(inputAddressDto.getPostalcode1(), addressEntity.getPostal1());
        assertEquals(inputAddressDto.getPostalcode2(), addressEntity.getPostal2());
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

        // MasterPoliticalOrganizationAccessEntity
        List<MasterPoliticalOrganizationAccessEntity> listAccess = masterPoliticalOrganizationAccessRepository
                .findAll();
        assertEquals(1, listAccess.size());
        MasterPoliticalOrganizationAccessEntity accessEntity = listAccess.get(0);
        assertEquals(newCode, accessEntity.getPoliOrgKanrenshaCode());
        assertEquals(inputOrgNameDto.getOrgName(), accessEntity.getPartnerName());
        assertEquals(inputAccessDto.getPhon1(), accessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), accessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), accessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), accessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), accessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), accessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), accessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount());

        // MasterPoliticalOrganizationBaseEntity
        List<MasterPoliticalOrganizationBaseEntity> listBase = masterPoliticalOrganizationBaseRepository.findAll();
        assertEquals(1, listBase.size());
        MasterPoliticalOrganizationBaseEntity baseEntity = listBase.get(0);
        assertEquals(newCode, baseEntity.getPoliOrgKanrenshaCode());
        assertEquals(inputOrgNameDto.getOrgName(), baseEntity.getPartnerName());
        assertEquals(inputOrgNameDto.getOrgNameKana(), baseEntity.getOrgNameKana());
        assertEquals(orgDelegateLeastDto.getPersonKanrenshaCode(), baseEntity.getOrgDelegateCode());

        // MasterPoliticalOrganizationPropertyEntity
        List<MasterPoliticalOrganizationPropertyEntity> listProperty = masterPoliticalOrganizationPropertyRepository
                .findAll();
        assertEquals(1, listProperty.size());
        MasterPoliticalOrganizationPropertyEntity propertyEntity = listProperty.get(0);
        assertEquals(newCode, propertyEntity.getPoliOrgKanrenshaCode());
        assertEquals(accounrMgrLeastDto.getPersonKanrenshaCode(), propertyEntity.getAccountMgrCode());
        assertEquals(accounrMgrLeastDto.getPersonName(), propertyEntity.getAccountMgrName());
        assertEquals(inputOrgNameDto.getOrgName(), propertyEntity.getPartnerName());

        // PartnerPoliOrgHistory45Entity (宮崎県のため)
        List<PartnerPoliOrgHistory45Entity> listHistory = partnerPoliOrgHistory45Repository.findAll();
        assertEquals(1, listHistory.size());
        PartnerPoliOrgHistory45Entity historyEntity = listHistory.get(0);
        assertEquals(newCode, historyEntity.getPoliOrgKanrenshaCode());
        assertEquals(masterEntity.getPartnerName(), historyEntity.getPartnerName());
    }
}
