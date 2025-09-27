package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SaveRiyoushaManagerCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerNameRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveRiyoushaManagerEntityService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveRiyoushaManagerDtoServiceTest.sql")
class SaveRiyoushaManagerEntityServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveRiyoushaManagerEntityService saveRiyoushaManagerEntityService;
    
    /** APIユーザマスタrepository */
    @Autowired
    private RiyoushaManagerRepository riyoushaManagerRepository;

    /** 利用者管理者連絡先Repository */
    @Autowired
    private RiyoushaManagerAccessRepository riyoushaManagerAccessRepository;

    /** 利用者管理者住所Repository */
    @Autowired
    private RiyoushaManagerAddressRepository riyoushaManagerAddressRepository;

    /** 利用者管理者名称Repository */
    @Autowired
    private RiyoushaManagerNameRepository riyoushaManagerNameRepository;

    @Test
    @Tag("TableTruncate")
    void testInsert() throws Exception { // NOPMD

        RiyoushaManagerDto managerDto = new RiyoushaManagerDto();

        managerDto.setRiyoushaManagerId(0); // 0でInsert
        managerDto.setRiyoushaManagerCode(0);
        managerDto.setRiyoushaManagerName("");
        managerDto.setAccessId(0);
        managerDto.setAddressId(0);
        managerDto.setNameId(0);

        managerDto.setIsNotOrg(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        managerDto.setInputAccessDto(inputAccessDto);

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostalcode1("880");
        inputAddressDto.setPostalcode2("8501");
        inputAddressDto.setAddressAll("宮崎県架空市橘通東２丁目１０−１");
        inputAddressDto.setAddressPostal("宮崎県架空市橘通東");
        inputAddressDto.setAddressBlock("２丁目１０−１");
        inputAddressDto.setAddressBuilding("宮崎県庁");
        inputAddressDto.setLgCode("4507011");
        inputAddressDto.setMachiazaId("131016");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);
        managerDto.setInputAddressDto(inputAddressDto);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        managerDto.setInputOrgNameDto(inputOrgNameDto);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        managerDto.setInputPersonNameDto(inputPersonNameDto);

        SaveRiyoushaManagerCapsuleDto capsuleDto = new SaveRiyoushaManagerCapsuleDto();
        capsuleDto.setRiyoushaManagerDto(managerDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        Integer newId = saveRiyoushaManagerEntityService.practice(capsuleDto);

        Optional<RiyoushaManagerEntity> optional = riyoushaManagerRepository.findById(newId);

        assertFalse(optional.isEmpty());

        RiyoushaManagerEntity newMasterEntity = optional.get();

        assertEquals(newId, newMasterEntity.getRiyoushaManagerId());
        assertEquals(true, newMasterEntity.getIsNotOrg());
        assertEquals("迂回献金　ミカエル太郎", newMasterEntity.getRiyoushaManagerName());

        // 連絡先
        RiyoushaManagerAccessEntity newAccessEntity = riyoushaManagerAccessRepository
                .findByRiyoushaManagerIdAndIsLatest(newId, true).get(0);
        assertEquals(inputAccessDto.getPhon1(), newAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), newAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), newAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), newAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), newAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), newAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), newAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), newAccessEntity.getSnsAccount());

        // 住所
        RiyoushaManagerAddressEntity newAddressEntity = riyoushaManagerAddressRepository
                .findByRiyoushaManagerIdAndIsLatest(newId, true).get(0);

        assertEquals(inputAddressDto.getPostalcode1(), newAddressEntity.getPostal1());
        assertEquals(inputAddressDto.getPostalcode2(), newAddressEntity.getPostal2());
        // assertEquals(inputAddressDto.getAddressAll(), newAddressEntity.getPostal1());
        assertEquals(inputAddressDto.getAddressPostal(), newAddressEntity.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), newAddressEntity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), newAddressEntity.getAddressBuilding());
        assertEquals(inputAddressDto.getLgCode(), newAddressEntity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), newAddressEntity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), newAddressEntity.getBlkId());
        assertEquals(inputAddressDto.getRsdtId(), newAddressEntity.getRsdtId());
        // assertEquals(inputAddressDto.getRsdtId(), newAddressEntity.getRsdt2Id());

        assertEquals(inputAddressDto.getIsPostalEdit(), newAddressEntity.getIsPostalEdit());
        assertEquals(inputAddressDto.getIsBlockEdit(), newAddressEntity.getIsBlockEdit());
        assertEquals(inputAddressDto.getIsBuildingEdit(), newAddressEntity.getIsBuildingEdit());

        // 名称
        RiyoushaManagerNameEntity newNameEntity = riyoushaManagerNameRepository
                .findByRiyoushaManagerIdAndIsLatest(newId, true).get(0);

        // assertEquals(inputOrgNameDto.getOrgName(), newNameEntity.getOrgName());
        // assertEquals(inputOrgNameDto.getOrgNameKana(), newNameEntity.getOrgName());

        // assertEquals(inputPersonNameDto.getAllName(), newNameEntity.getOrgName());
        assertEquals(inputPersonNameDto.getFirstName(), newNameEntity.getFirstName());
        assertEquals(inputPersonNameDto.getLastName(), newNameEntity.getLastName());
        assertEquals(inputPersonNameDto.getMiddleName(), newNameEntity.getMiddleName());
        assertEquals(inputPersonNameDto.getFirstNameKana(), newNameEntity.getFirstNameKana());
        assertEquals(inputPersonNameDto.getLastNameKana(), newNameEntity.getLastNameKana());
        assertEquals(inputPersonNameDto.getMiddleNameKana(), newNameEntity.getMiddleNameKana());

        // 組織
        capsuleDto.getRiyoushaManagerDto().setIsNotOrg(false);
        Integer newId2 = saveRiyoushaManagerEntityService.practice(capsuleDto);
        RiyoushaManagerNameEntity newNameEntity2 = riyoushaManagerNameRepository
                .findByRiyoushaManagerIdAndIsLatest(newId2, true).get(0);
        assertEquals(inputOrgNameDto.getOrgName(), newNameEntity2.getOrgName());
        assertEquals(inputOrgNameDto.getOrgNameKana(), newNameEntity2.getOrgNameKana());

    }

    @Test
    @Tag("TableTruncate")
    void testUpdate() throws Exception { // NOPMD

        RiyoushaManagerDto managerDto = new RiyoushaManagerDto();

        managerDto.setRiyoushaManagerId(467); // update
        managerDto.setRiyoushaManagerCode(0);
        managerDto.setRiyoushaManagerName("迂回献金 ミカエル太郎");
        managerDto.setAccessId(623);
        managerDto.setAddressId(826);
        managerDto.setNameId(316);

        managerDto.setIsNotOrg(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        managerDto.setInputAccessDto(inputAccessDto);

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostalcode1("880");
        inputAddressDto.setPostalcode2("8501");
        inputAddressDto.setAddressAll("宮崎県架空市橘通東２丁目１０−１");
        inputAddressDto.setAddressPostal("宮崎県架空市橘通東");
        inputAddressDto.setAddressBlock("２丁目１０−１");
        inputAddressDto.setAddressBuilding("宮崎県庁");
        inputAddressDto.setLgCode("4507011");
        inputAddressDto.setMachiazaId("131016");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);
        managerDto.setInputAddressDto(inputAddressDto);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        managerDto.setInputOrgNameDto(inputOrgNameDto);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        managerDto.setInputPersonNameDto(inputPersonNameDto);

        SaveRiyoushaManagerCapsuleDto capsuleDto = new SaveRiyoushaManagerCapsuleDto();
        capsuleDto.setRiyoushaManagerDto(managerDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        Integer newId = saveRiyoushaManagerEntityService.practice(capsuleDto);
        
        Optional<RiyoushaManagerEntity> optional = riyoushaManagerRepository.findById(newId);

        assertFalse(optional.isEmpty());

        RiyoushaManagerEntity newMasterEntity = optional.get();

        assertEquals(467, newMasterEntity.getRiyoushaManagerCode(),"コードは引き継がれていること");
        assertEquals(newId, newMasterEntity.getRiyoushaManagerId());
        assertEquals(true, newMasterEntity.getIsNotOrg());
        assertEquals("迂回献金　ミカエル太郎", newMasterEntity.getRiyoushaManagerName());

        // 連絡先
        RiyoushaManagerAccessEntity newAccessEntity = riyoushaManagerAccessRepository
                .findByRiyoushaManagerIdAndIsLatest(newId, true).get(0);
        assertEquals(inputAccessDto.getPhon1(), newAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), newAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), newAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), newAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), newAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), newAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), newAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), newAccessEntity.getSnsAccount());

        // 住所
        RiyoushaManagerAddressEntity newAddressEntity = riyoushaManagerAddressRepository
                .findByRiyoushaManagerIdAndIsLatest(newId, true).get(0);

        assertEquals(inputAddressDto.getPostalcode1(), newAddressEntity.getPostal1());
        assertEquals(inputAddressDto.getPostalcode2(), newAddressEntity.getPostal2());
        // assertEquals(inputAddressDto.getAddressAll(), newAddressEntity.getPostal1());
        assertEquals(inputAddressDto.getAddressPostal(), newAddressEntity.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), newAddressEntity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), newAddressEntity.getAddressBuilding());
        assertEquals(inputAddressDto.getLgCode(), newAddressEntity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), newAddressEntity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), newAddressEntity.getBlkId());
        assertEquals(inputAddressDto.getRsdtId(), newAddressEntity.getRsdtId());
        // assertEquals(inputAddressDto.getRsdtId(), newAddressEntity.getRsdt2Id());

        assertEquals(inputAddressDto.getIsPostalEdit(), newAddressEntity.getIsPostalEdit());
        assertEquals(inputAddressDto.getIsBlockEdit(), newAddressEntity.getIsBlockEdit());
        assertEquals(inputAddressDto.getIsBuildingEdit(), newAddressEntity.getIsBuildingEdit());

        // 名称
        RiyoushaManagerNameEntity newNameEntity = riyoushaManagerNameRepository
                .findByRiyoushaManagerIdAndIsLatest(newId, true).get(0);

        // assertEquals(inputOrgNameDto.getOrgName(), newNameEntity.getOrgName());
        // assertEquals(inputOrgNameDto.getOrgNameKana(), newNameEntity.getOrgName());

        // assertEquals(inputPersonNameDto.getAllName(), newNameEntity.getOrgName());
        assertEquals(inputPersonNameDto.getFirstName(), newNameEntity.getFirstName());
        assertEquals(inputPersonNameDto.getLastName(), newNameEntity.getLastName());
        assertEquals(inputPersonNameDto.getMiddleName(), newNameEntity.getMiddleName());
        assertEquals(inputPersonNameDto.getFirstNameKana(), newNameEntity.getFirstNameKana());
        assertEquals(inputPersonNameDto.getLastNameKana(), newNameEntity.getLastNameKana());
        assertEquals(inputPersonNameDto.getMiddleNameKana(), newNameEntity.getMiddleNameKana());

        // 元データは履歴に変更
        assertFalse(riyoushaManagerRepository.findById(467).get().getIsLatest(),"履歴に変更"); // NOPMD
        assertFalse(riyoushaManagerAccessRepository.findById(623).get().getIsLatest(),"履歴に変更");
        assertFalse(riyoushaManagerAddressRepository.findById(826).get().getIsLatest(),"履歴に変更");
        assertFalse(riyoushaManagerNameRepository.findById(316).get().getIsLatest(),"履歴に変更");
        
    }
}
