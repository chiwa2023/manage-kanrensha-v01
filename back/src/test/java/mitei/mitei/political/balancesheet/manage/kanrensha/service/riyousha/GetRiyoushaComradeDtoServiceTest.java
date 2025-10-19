package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeRepository;

/**
 * GetRiyoushaComradeDtoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRiyoushaComradeDtoServiceTest.sql")
class GetRiyoushaComradeDtoServiceTest {

    /** テスト対象 */
    @Autowired
    private GetRiyoushaComradeDtoService getRiyoushaComradeDtoService;

    /** APIユーザマスタrepository */
    @Autowired
    private RiyoushaComradeRepository riyoushaComradeRepository;

    @Test
    @Tag("TableTruncate")
    void testPerson() throws Exception {

        final Integer callId = 467;

        RiyoushaComradeEntity comradeEntity = riyoushaComradeRepository.findById(callId).get();

        RiyoushaComradeDto riyoushaComradeDto = getRiyoushaComradeDtoService.practice(comradeEntity);
        assertEquals(callId, riyoushaComradeDto.getRiyoushaComradeId());

        InputAccessDto inputAccessDto = riyoushaComradeDto.getInputAccessDto();
        assertEquals("012", inputAccessDto.getPhon1());
        assertEquals("345", inputAccessDto.getPhon2());
        assertEquals("678", inputAccessDto.getPhon3());
        assertEquals("aaa@seijishikin.jp", inputAccessDto.getEmail());
        assertEquals("https://blog.com/", inputAccessDto.getMyPortalUrl());
        assertEquals("弱小SNS", inputAccessDto.getSnsServiceName());
        assertEquals("https://jyakushou.sns.com/?acount=1234", inputAccessDto.getSnsPortalUrl());
        assertEquals("@hanako", inputAccessDto.getSnsAccount());

        InputAddressDto inputAddressDto = riyoushaComradeDto.getInputAddressDto();
        assertEquals("山梨県実在市湖畔町202番地1　四角ビル", inputAddressDto.getAddressAll());
        // assertEquals("", inputAddressDto.getOrginAddressAll());
        assertEquals("123", inputAddressDto.getPostalcode1());
        assertEquals("4567", inputAddressDto.getPostalcode2());
        assertEquals("山梨県実在市湖畔町", inputAddressDto.getAddressPostal());
        assertEquals("202番地1", inputAddressDto.getAddressBlock());
        assertEquals("四角ビル", inputAddressDto.getAddressBuilding());
        assertEquals("212134", inputAddressDto.getLgCode());
        assertEquals("111", inputAddressDto.getMachiazaId());
        assertEquals("222", inputAddressDto.getBlkId());
        assertEquals("333", inputAddressDto.getRsdtId());
        assertEquals(true, inputAddressDto.getIsPostalEdit());
        assertEquals(true, inputAddressDto.getIsBlockEdit());
        assertEquals(true, inputAddressDto.getIsBuildingEdit());

        InputPersonNameDto inputPersonNameDto = riyoushaComradeDto.getInputPersonNameDto();
        assertEquals("APIユーザ　マリア花子", inputPersonNameDto.getAllName());
        assertEquals("えーぴーあいゆーざ　まりあはなこ", inputPersonNameDto.getAllNameKana());
        assertEquals("APIユーザ", inputPersonNameDto.getLastName());
        assertEquals("花子", inputPersonNameDto.getFirstName());
        assertEquals("マリア", inputPersonNameDto.getMiddleName());
        assertEquals("えーぴーあいゆーざ", inputPersonNameDto.getLastNameKana());
        assertEquals("はなこ", inputPersonNameDto.getFirstNameKana());
        assertEquals("まりあ", inputPersonNameDto.getMiddleNameKana());
    }

    @Test
    @Tag("TableTruncate")
    void testOrg() throws Exception {

        final Integer callId = 467;

        RiyoushaComradeEntity comradeEntity = riyoushaComradeRepository.findById(callId).get();
        comradeEntity.setIsNotOrg(false);

        RiyoushaComradeDto riyoushaComradeDto = getRiyoushaComradeDtoService.practice(comradeEntity);

        assertEquals(callId, riyoushaComradeDto.getRiyoushaComradeId());

        InputAccessDto inputAccessDto = riyoushaComradeDto.getInputAccessDto();
        assertEquals("012", inputAccessDto.getPhon1());
        assertEquals("345", inputAccessDto.getPhon2());
        assertEquals("678", inputAccessDto.getPhon3());
        assertEquals("aaa@seijishikin.jp", inputAccessDto.getEmail());
        assertEquals("https://blog.com/", inputAccessDto.getMyPortalUrl());
        assertEquals("弱小SNS", inputAccessDto.getSnsServiceName());
        assertEquals("https://jyakushou.sns.com/?acount=1234", inputAccessDto.getSnsPortalUrl());
        assertEquals("@hanako", inputAccessDto.getSnsAccount());

        InputAddressDto inputAddressDto = riyoushaComradeDto.getInputAddressDto();
        assertEquals("山梨県実在市湖畔町202番地1　四角ビル", inputAddressDto.getAddressAll());
        // assertEquals("", inputAddressDto.getOrginAddressAll());
        assertEquals("123", inputAddressDto.getPostalcode1());
        assertEquals("4567", inputAddressDto.getPostalcode2());
        assertEquals("山梨県実在市湖畔町", inputAddressDto.getAddressPostal());
        assertEquals("202番地1", inputAddressDto.getAddressBlock());
        assertEquals("四角ビル", inputAddressDto.getAddressBuilding());
        assertEquals("212134", inputAddressDto.getLgCode());
        assertEquals("111", inputAddressDto.getMachiazaId());
        assertEquals("222", inputAddressDto.getBlkId());
        assertEquals("333", inputAddressDto.getRsdtId());
        assertEquals(true, inputAddressDto.getIsPostalEdit());
        assertEquals(true, inputAddressDto.getIsBlockEdit());
        assertEquals(true, inputAddressDto.getIsBuildingEdit());

        InputOrgNameDto inputOrgNameDto = riyoushaComradeDto.getInputOrgNameDto();
        assertEquals("例外システム株式会社", inputOrgNameDto.getOrgName());
        assertEquals("れいがいしすてむかぶしきかいしゃ", inputOrgNameDto.getOrgNameKana());
    }

}
