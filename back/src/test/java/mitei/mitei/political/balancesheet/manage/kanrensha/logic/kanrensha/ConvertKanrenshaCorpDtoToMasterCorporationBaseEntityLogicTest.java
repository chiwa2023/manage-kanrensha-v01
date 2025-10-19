package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;

/**
 * ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic単体テスト
 */
class ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("超元素製造組合");
        inputOrgNameDto.setOrgNameKana("ちょうげんそせいぞうくみあい");
        
        InputKanrenshaPersonLeastDto delegateDto = new InputKanrenshaPersonLeastDto();
        delegateDto.setPersonName("代表者　太郎");
        delegateDto.setPersonKanrenshaCode("112-3344");

        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setIsShiten(true);
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);
        kanrenshaCorpDto.setOrgDelegateLeastDto(delegateDto);
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);

        ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic logic = new ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic();

        MasterCorporationBaseEntity baseEntity = logic.practice(kanrenshaCorpDto);
        
        assertEquals(kanrenshaCorpDto.getIsShiten(), baseEntity.getIsShiten());
        assertEquals(kanrenshaCorpDto.getOrgDelegateLeastDto().getPersonKanrenshaCode(), baseEntity.getOrgDelegateCode());
        assertEquals(inputOrgNameDto.getOrgNameKana(), baseEntity.getOrgNameKana());
        assertEquals(inputOrgNameDto.getOrgName(), baseEntity.getPartnerName());
    }

}
