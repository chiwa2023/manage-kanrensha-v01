package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.InsertAddressByComponentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertAddressByComponentService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_011002.sql")
class InsertAddressByComponentServiceTest {

    /** テスト対象 */
    @Autowired
    private InsertAddressByComponentService insertAddressByComponentService;

    /** アドレス・ベース・レジストリコード抽出Service */
    @Autowired
    private ChoiceAddressRegistoryByCodeService choiceAddressRegistoryByCodeService;

    @Test
    @Tag("TableTruncate")
    void testInsertSuccess() throws Exception {

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setLgCode("011002");
        inputAddressDto.setMachiazaId("0101");
        inputAddressDto.setBlkId("022");
        inputAddressDto.setRsdtId("033");
        inputAddressDto.setAddressPostal("北海道架空市");
        inputAddressDto.setAddressBlock("山麓町3丁目8番地6");
        inputAddressDto.setAddressBuilding("未入力アパート606");

        InsertAddressByComponentCapsuleDto capsuleDto = new InsertAddressByComponentCapsuleDto();
        capsuleDto.setInputAddressDto(inputAddressDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        FrameworkMessageAndResultDto resultDto = insertAddressByComponentService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        AddressRsdtTemplateEntity entitySearch = new AddressRsdtTemplateEntity();
        BeanUtils.copyProperties(inputAddressDto, entitySearch);
        // TODO 追加でフィールドコピーをしなくてよいようにフィールド名を調整する
        entitySearch.setPostalCode(inputAddressDto.getPostalcode1() + inputAddressDto.getPostalcode2());
        entitySearch.setAddressBlock(inputAddressDto.getAddressPostal() + inputAddressDto.getAddressBlock());
        entitySearch.setParcelRsdtId(inputAddressDto.getBlkId() + inputAddressDto.getRsdtId());

        List<AddressRsdtTemplateEntity> listEntity = choiceAddressRegistoryByCodeService.practice(entitySearch);

        AddressRsdtTemplateEntity entity = listEntity.get(0);

        assertEquals(inputAddressDto.getPostalcode1() + inputAddressDto.getPostalcode2(), entity.getPostalCode());
        assertEquals(inputAddressDto.getAddressPostal() + inputAddressDto.getAddressBlock(), entity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), entity.getAddressBuilding());
        assertEquals(inputAddressDto.getLgCode(), entity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), entity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId() + inputAddressDto.getRsdtId(), entity.getParcelRsdtId());
    }

    @Test
    @Tag("TableTruncate")
    void testSameCode() throws Exception {

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setLgCode("011002");
        inputAddressDto.setMachiazaId("987");
        inputAddressDto.setBlkId("54");
        inputAddressDto.setRsdtId("31");
        inputAddressDto.setAddressPostal("北海道架空市");
        inputAddressDto.setAddressBlock("山麓町3丁目8番地6");
        inputAddressDto.setAddressBuilding("未入力アパート606");

        InsertAddressByComponentCapsuleDto capsuleDto = new InsertAddressByComponentCapsuleDto();
        capsuleDto.setInputAddressDto(inputAddressDto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        FrameworkMessageAndResultDto resultDto = insertAddressByComponentService.practice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("同一コードの住所が存在したので保存を中断しました。", resultDto.getMessage());
    }

}
