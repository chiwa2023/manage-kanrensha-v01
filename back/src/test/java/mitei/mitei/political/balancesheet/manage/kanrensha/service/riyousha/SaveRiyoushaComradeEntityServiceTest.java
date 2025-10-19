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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaComradeDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SaveRiyoushaComradeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeNameRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveRiyoushaComradeEntityService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRiyoushaComradeDtoServiceTest.sql")
class SaveRiyoushaComradeEntityServiceTest {
    // CHECKSTYLE:OFF MAgicNumber

    /** テスト対象 */
    @Autowired
    private SaveRiyoushaComradeEntityService saveRiyoushaComradeEntityService;

    /** APIユーザマスタrepository */
    @Autowired
    private RiyoushaComradeRepository riyoushaComradeRepository;

    /** 利用者管理者連絡先Repository */
    @Autowired
    private RiyoushaComradeAccessRepository riyoushaComradeAccessRepository;

    /** 利用者管理者住所Repository */
    @Autowired
    private RiyoushaComradeAddressRepository riyoushaComradeAddressRepository;

    /** 利用者管理者名称Repository */
    @Autowired
    private RiyoushaComradeNameRepository riyoushaComradeNameRepository;

    @Test
    @Tag("TableTruncate")
    void testInsert() throws Exception { // NOPMD

        RiyoushaComradeDto comradeDto = new RiyoushaComradeDto();

        comradeDto.setRiyoushaComradeId(0); // 0でInsert
        comradeDto.setRiyoushaComradeCode(0);
        comradeDto.setRiyoushaComradeName("");
        comradeDto.setAccessId(0);
        comradeDto.setAddressId(0);
        comradeDto.setNameId(0);

        comradeDto.setIsNotOrg(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        comradeDto.setInputAccessDto(inputAccessDto);

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
        comradeDto.setInputAddressDto(inputAddressDto);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        comradeDto.setInputOrgNameDto(inputOrgNameDto);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        comradeDto.setInputPersonNameDto(inputPersonNameDto);

        SaveRiyoushaComradeCapsuleDto capsuleDto = new SaveRiyoushaComradeCapsuleDto();
        capsuleDto.setRiyoushaComradeDto(comradeDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        Integer newId = saveRiyoushaComradeEntityService.practice(capsuleDto);

        Optional<RiyoushaComradeEntity> optional = riyoushaComradeRepository.findById(newId);

        assertFalse(optional.isEmpty());

        RiyoushaComradeEntity newMasterEntity = optional.get();

        assertEquals(newId, newMasterEntity.getRiyoushaComradeId());
        assertEquals(true, newMasterEntity.getIsNotOrg());
        assertEquals("迂回献金　ミカエル太郎", newMasterEntity.getRiyoushaComradeName());

        // 連絡先
        RiyoushaComradeAccessEntity newAccessEntity = riyoushaComradeAccessRepository
                .findByRiyoushaComradeIdAndIsLatest(newId, true).get(0);
        assertEquals(inputAccessDto.getPhon1(), newAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), newAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), newAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), newAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), newAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), newAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), newAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), newAccessEntity.getSnsAccount());

        // 住所
        RiyoushaComradeAddressEntity newAddressEntity = riyoushaComradeAddressRepository
                .findByRiyoushaComradeIdAndIsLatest(newId, true).get(0);

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
        RiyoushaComradeNameEntity newNameEntity = riyoushaComradeNameRepository
                .findByRiyoushaComradeIdAndIsLatest(newId, true).get(0);

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
        capsuleDto.getRiyoushaComradeDto().setIsNotOrg(false);
        Integer newId2 = saveRiyoushaComradeEntityService.practice(capsuleDto);
        RiyoushaComradeNameEntity newNameEntity2 = riyoushaComradeNameRepository
                .findByRiyoushaComradeIdAndIsLatest(newId2, true).get(0);
        assertEquals(inputOrgNameDto.getOrgName(), newNameEntity2.getOrgName());
        assertEquals(inputOrgNameDto.getOrgNameKana(), newNameEntity2.getOrgNameKana());

    }

    @Test
    @Tag("TableTruncate")
    void testUpdate() throws Exception { // NOPMD

        RiyoushaComradeDto comradeDto = new RiyoushaComradeDto();

        comradeDto.setRiyoushaComradeId(467); // update
        comradeDto.setRiyoushaComradeCode(0);
        comradeDto.setRiyoushaComradeName("迂回献金 ミカエル太郎");
        comradeDto.setAccessId(623);
        comradeDto.setAddressId(826);
        comradeDto.setNameId(316);

        comradeDto.setIsNotOrg(true);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("poli_org@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        comradeDto.setInputAccessDto(inputAccessDto);

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
        comradeDto.setInputAddressDto(inputAddressDto);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("日本未来党");
        inputOrgNameDto.setOrgNameKana("にほんみらいとう");
        comradeDto.setInputOrgNameDto(inputOrgNameDto);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        comradeDto.setInputPersonNameDto(inputPersonNameDto);

        SaveRiyoushaComradeCapsuleDto capsuleDto = new SaveRiyoushaComradeCapsuleDto();
        capsuleDto.setRiyoushaComradeDto(comradeDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        Integer newId = saveRiyoushaComradeEntityService.practice(capsuleDto);
        
        Optional<RiyoushaComradeEntity> optional = riyoushaComradeRepository.findById(newId);

        assertFalse(optional.isEmpty());

        RiyoushaComradeEntity newMasterEntity = optional.get();

        assertEquals(467, newMasterEntity.getRiyoushaComradeCode(),"コードは引き継がれていること");
        assertEquals(newId, newMasterEntity.getRiyoushaComradeId());
        assertEquals(true, newMasterEntity.getIsNotOrg());
        assertEquals("迂回献金　ミカエル太郎", newMasterEntity.getRiyoushaComradeName());

        // 連絡先
        RiyoushaComradeAccessEntity newAccessEntity = riyoushaComradeAccessRepository
                .findByRiyoushaComradeIdAndIsLatest(newId, true).get(0);
        assertEquals(inputAccessDto.getPhon1(), newAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), newAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), newAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), newAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), newAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), newAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), newAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), newAccessEntity.getSnsAccount());

        // 住所
        RiyoushaComradeAddressEntity newAddressEntity = riyoushaComradeAddressRepository
                .findByRiyoushaComradeIdAndIsLatest(newId, true).get(0);

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
        RiyoushaComradeNameEntity newNameEntity = riyoushaComradeNameRepository
                .findByRiyoushaComradeIdAndIsLatest(newId, true).get(0);

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
        assertFalse(riyoushaComradeRepository.findById(467).get().getIsLatest(),"履歴に変更"); // NOPMD
        assertFalse(riyoushaComradeAccessRepository.findById(623).get().getIsLatest(),"履歴に変更");
        assertFalse(riyoushaComradeAddressRepository.findById(826).get().getIsLatest(),"履歴に変更");
        assertFalse(riyoushaComradeNameRepository.findById(316).get().getIsLatest(),"履歴に変更");
        
    }
    
}
