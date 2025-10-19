package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setIsForeign(true);

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setAllNameKana("うかいけんきん　たろう");
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);

        ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic = new ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic();

        MasterPersonPropertyEntity propertyEntity = convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic
                .practice(kanrenshaPersonDto);

        assertEquals(inputPersonNameDto.getAllName(), propertyEntity.getPartnerName());
        assertEquals(kanrenshaPersonDto.getIsForeign(), propertyEntity.getIsForeign());
    }

}
