package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogicTest {

    @Test
    void test() throws Exception {

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setIsForeign(true);

        ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic = new ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic();

        MasterPersonPropertyEntity propertyEntity = convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic
                .practice(kanrenshaPersonDto);

        assertEquals(kanrenshaPersonDto.getIsForeign(), propertyEntity.getIsForeign());
    }

}
