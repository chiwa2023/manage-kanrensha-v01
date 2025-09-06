package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;

/**
 * ConvertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic単体テスト
 */
class ConvertKanrenshaCorpDtoToMasterCorporationAccessEntityLogicTest {

    
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("03");
        inputAccessDto.setPhon2("1234");
        inputAccessDto.setPhon3("5678");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setInputAccessDto(inputAccessDto);
        
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("超元素製造組合");
        inputOrgNameDto.setOrgNameKana("ちょうげんそせいぞうくみあい");
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);


        ConvertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic logic = new ConvertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic();
        MasterCorporationAccessEntity accessEntity = logic.practice(kanrenshaCorpDto);

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
