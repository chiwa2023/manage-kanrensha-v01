package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;

/**
 * ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic単体テスト
 */
class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("ちゃらんぽらん政治団体");
        inputOrgNameDto.setOrgNameKana("ちゃらんぽらんせいじだんたい");

        InputKanrenshaPersonLeastDto accountMgrDto = new InputKanrenshaPersonLeastDto();
        accountMgrDto.setPersonName("会計責任者　花子");
        accountMgrDto.setPersonKanrenshaCode("998-776");
        
        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();
        kanrenshaPoliOrgDto.setAccounrMgrLeastDto(accountMgrDto);
        kanrenshaPoliOrgDto.setInputOrgNameDto(inputOrgNameDto);

        ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic logic = new ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic();
        MasterPoliticalOrganizationPropertyEntity propertyEntity = logic.practice(kanrenshaPoliOrgDto);

        assertEquals(inputOrgNameDto.getOrgName(), propertyEntity.getPartnerName());
        assertEquals(accountMgrDto.getPersonKanrenshaCode(), propertyEntity.getAccountMgrCode());
        assertEquals(accountMgrDto.getPersonName(), propertyEntity.getAccountMgrName());
    }

}
