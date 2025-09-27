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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaAdminDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SaveRiyoushaAdminCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminNameRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveRiyoushaAdminEntityService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveRiyoushaAdminDtoServiceTest.sql")
class SaveRiyoushaAdminEntityServiceTest {

    /** テスト対象 */
    @Autowired
    private SaveRiyoushaAdminEntityService saveRiyoushaAdminEntityService;
    
    /** APIユーザマスタrepository */
    @Autowired
    private RiyoushaAdminRepository riyoushaAdminRepository;

    /** 利用者管理者連絡先Repository */
    @Autowired
    private RiyoushaAdminAccessRepository riyoushaAdminAccessRepository;

    /** 利用者管理者住所Repository */
    @Autowired
    private RiyoushaAdminAddressRepository riyoushaAdminAddressRepository;

    /** 利用者管理者名称Repository */
    @Autowired
    private RiyoushaAdminNameRepository riyoushaAdminNameRepository;

    @Test
    @Tag("TableTruncate")
    void testInsert() throws Exception { // NOPMD
        // CHECKSTYLE:OFF MagicNumber 

        RiyoushaAdminDto adminDto = new RiyoushaAdminDto();

        adminDto.setRiyoushaAdminId(0); // 0でInsert
        adminDto.setRiyoushaAdminCode(0);
        adminDto.setRiyoushaAdminName("");
        adminDto.setAccessId(0);
        adminDto.setAddressId(0);
        adminDto.setNameId(0);

        adminDto.setIsNotOrg(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        adminDto.setInputAccessDto(inputAccessDto);

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
        adminDto.setInputAddressDto(inputAddressDto);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        adminDto.setInputOrgNameDto(inputOrgNameDto);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        adminDto.setInputPersonNameDto(inputPersonNameDto);

        SaveRiyoushaAdminCapsuleDto capsuleDto = new SaveRiyoushaAdminCapsuleDto();
        capsuleDto.setRiyoushaAdminDto(adminDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        Integer newId = saveRiyoushaAdminEntityService.practice(capsuleDto);

        Optional<RiyoushaAdminEntity> optional = riyoushaAdminRepository.findById(newId);

        assertFalse(optional.isEmpty());

        RiyoushaAdminEntity newMasterEntity = optional.get();

        assertEquals(newId, newMasterEntity.getRiyoushaAdminId());
        assertEquals(true, newMasterEntity.getIsNotOrg());
        assertEquals("迂回献金　ミカエル太郎", newMasterEntity.getRiyoushaAdminName());

        // 連絡先
        RiyoushaAdminAccessEntity newAccessEntity = riyoushaAdminAccessRepository
                .findByRiyoushaAdminIdAndIsLatest(newId, true).get(0);
        assertEquals(inputAccessDto.getPhon1(), newAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), newAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), newAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), newAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), newAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), newAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), newAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), newAccessEntity.getSnsAccount());

        // 住所
        RiyoushaAdminAddressEntity newAddressEntity = riyoushaAdminAddressRepository
                .findByRiyoushaAdminIdAndIsLatest(newId, true).get(0);

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
        RiyoushaAdminNameEntity newNameEntity = riyoushaAdminNameRepository
                .findByRiyoushaAdminIdAndIsLatest(newId, true).get(0);

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
        capsuleDto.getRiyoushaAdminDto().setIsNotOrg(false);
        Integer newId2 = saveRiyoushaAdminEntityService.practice(capsuleDto);
        RiyoushaAdminNameEntity newNameEntity2 = riyoushaAdminNameRepository
                .findByRiyoushaAdminIdAndIsLatest(newId2, true).get(0);
        assertEquals(inputOrgNameDto.getOrgName(), newNameEntity2.getOrgName());
        assertEquals(inputOrgNameDto.getOrgNameKana(), newNameEntity2.getOrgNameKana());

    }

    @Test
    @Tag("TableTruncate")
    void testUpdate() throws Exception {  // NOPMD

        RiyoushaAdminDto adminDto = new RiyoushaAdminDto();

        adminDto.setRiyoushaAdminId(467); // update
        adminDto.setRiyoushaAdminCode(0);
        adminDto.setRiyoushaAdminName("迂回献金 ミカエル太郎");
        adminDto.setAccessId(623);
        adminDto.setAddressId(826);
        adminDto.setNameId(316);

        adminDto.setIsNotOrg(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        adminDto.setInputAccessDto(inputAccessDto);

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
        adminDto.setInputAddressDto(inputAddressDto);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        adminDto.setInputOrgNameDto(inputOrgNameDto);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        adminDto.setInputPersonNameDto(inputPersonNameDto);

        SaveRiyoushaAdminCapsuleDto capsuleDto = new SaveRiyoushaAdminCapsuleDto();
        capsuleDto.setRiyoushaAdminDto(adminDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        Integer newId = saveRiyoushaAdminEntityService.practice(capsuleDto);
        
        Optional<RiyoushaAdminEntity> optional = riyoushaAdminRepository.findById(newId);

        assertFalse(optional.isEmpty());

        RiyoushaAdminEntity newMasterEntity = optional.get();

        assertEquals(467, newMasterEntity.getRiyoushaAdminCode(),"コードは引き継がれていること");
        assertEquals(newId, newMasterEntity.getRiyoushaAdminId());
        assertEquals(true, newMasterEntity.getIsNotOrg());
        assertEquals("迂回献金　ミカエル太郎", newMasterEntity.getRiyoushaAdminName());

        // 連絡先
        RiyoushaAdminAccessEntity newAccessEntity = riyoushaAdminAccessRepository
                .findByRiyoushaAdminIdAndIsLatest(newId, true).get(0);
        assertEquals(inputAccessDto.getPhon1(), newAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), newAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), newAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), newAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), newAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), newAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), newAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), newAccessEntity.getSnsAccount());

        // 住所
        RiyoushaAdminAddressEntity newAddressEntity = riyoushaAdminAddressRepository
                .findByRiyoushaAdminIdAndIsLatest(newId, true).get(0);

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
        RiyoushaAdminNameEntity newNameEntity = riyoushaAdminNameRepository
                .findByRiyoushaAdminIdAndIsLatest(newId, true).get(0);

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
        assertFalse(riyoushaAdminRepository.findById(467).get().getIsLatest(),"履歴に変更"); // NOPMD
        assertFalse(riyoushaAdminAccessRepository.findById(623).get().getIsLatest(),"履歴に変更");
        assertFalse(riyoushaAdminAddressRepository.findById(826).get().getIsLatest(),"履歴に変更");
        assertFalse(riyoushaAdminNameRepository.findById(316).get().getIsLatest(),"履歴に変更");
        
    }
}
