package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.HoujinShubetsuConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;

/**
 * ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic単体テスト
 */
class ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("超元素製造組合");
        inputOrgNameDto.setOrgNameKana("ちょうげんそせいぞうくみあい");

        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);
        kanrenshaCorpDto.setHoujinSbts(HoujinShubetsuConstants.GAIKOKU_KAISHA);
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);

        ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic logic = new ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic();
        MasterCorporationPropertyEntity propertyEntity = logic.practice(kanrenshaCorpDto);

        assertEquals(inputOrgNameDto.getOrgName(), propertyEntity.getPartnerName());
        assertEquals(kanrenshaCorpDto.getHoujinSbts(), propertyEntity.getHoujinSbts());
        assertEquals(true, propertyEntity.getIsForeign());
    }

}
