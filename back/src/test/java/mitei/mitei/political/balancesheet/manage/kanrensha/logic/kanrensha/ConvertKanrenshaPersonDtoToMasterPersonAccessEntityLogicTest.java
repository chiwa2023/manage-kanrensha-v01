package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogicTest {

    @Test
    void test() throws Exception {

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("03");
        inputAccessDto.setPhon2("1234");
        inputAccessDto.setPhon3("5678");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);

        ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic logic = new ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic();
        MasterPersonAccessEntity masterPersonAccessEntity = logic.practice(kanrenshaPersonDto);

        assertEquals(inputAccessDto.getPhon1(), masterPersonAccessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), masterPersonAccessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), masterPersonAccessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), masterPersonAccessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), masterPersonAccessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceName(), masterPersonAccessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), masterPersonAccessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), masterPersonAccessEntity.getSnsAccount());
        
        // TODO 値の設定法が決まり次第修正する
        // assertEquals(265, masterPersonAccessEntity.getSnsServiceId());
        // assertEquals(323, masterPersonAccessEntity.getSnsServiceCode());

        fail("Not yet implemented");
    }

}
