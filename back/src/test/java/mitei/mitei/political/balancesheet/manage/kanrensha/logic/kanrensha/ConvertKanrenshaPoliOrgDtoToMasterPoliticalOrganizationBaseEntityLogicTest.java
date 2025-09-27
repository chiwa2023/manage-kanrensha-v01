package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;

/**
 * ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic単体テスト
 */
class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("ちゃらんぽらん政治団体");
        inputOrgNameDto.setOrgNameKana("ちゃらんぽらんせいじだんたい");

        InputKanrenshaPersonLeastDto delegateDto = new InputKanrenshaPersonLeastDto();
        delegateDto.setPersonName("代表者　太郎");
        delegateDto.setPersonKanrenshaCode("112-3344");
        
        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);
        kanrenshaPoliOrgDto.setOrgDelegateLeastDto(delegateDto);

        ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic logic = new ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic();

        MasterPoliticalOrganizationBaseEntity baseEntity = logic.practice(kanrenshaPoliOrgDto);

        assertEquals(kanrenshaPoliOrgDto.getOrgDelegateLeastDto().getPersonKanrenshaCode(), baseEntity.getOrgDelegateCode());
        assertEquals(inputOrgNameDto.getOrgNameKana(), baseEntity.getOrgNameKana());
        assertEquals(inputOrgNameDto.getOrgName(), baseEntity.getPartnerName());

    }

}
