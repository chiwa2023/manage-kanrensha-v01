package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;

/**
 * ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic単体テスト
 */
class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() {

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("03");
        inputAccessDto.setPhon2("1234");
        inputAccessDto.setPhon3("5678");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setInputAccessDto(inputAccessDto);
        
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("ちゃらんぽらん政治団体");
        inputOrgNameDto.setOrgNameKana("ちゃらんぽらんせいじだんたい");
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);

        
        ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic logic = new ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic();
        MasterPoliticalOrganizationAccessEntity accessEntity = logic.practice(kanrenshaPoliOrgDto);

        assertEquals(inputAccessDto.getPhon1(), accessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), accessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), accessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), accessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), accessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), accessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), accessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount());

        assertEquals(inputOrgNameDto.getOrgName(), accessEntity.getPartnerName());

        // TODO 値の設定法が決まり次第修正する
        // assertEquals(265, accessEntity.getSnsServiceId());
        // assertEquals(323, accessEntity.getSnsServiceCode());
    }

}
