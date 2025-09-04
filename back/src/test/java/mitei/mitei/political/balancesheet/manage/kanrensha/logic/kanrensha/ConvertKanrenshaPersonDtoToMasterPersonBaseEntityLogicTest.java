package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogicTest {

    @Test
    void test() throws Exception {

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setFirstName("太郎");
        inputPersonNameDto.setLastName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setFirstNameKana("たろう");
        inputPersonNameDto.setLastNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);

        InputShokugyouDto inputShokugyouDto = new InputShokugyouDto();
        inputShokugyouDto.setGyoushu("水産業");
        inputShokugyouDto.setYakushoku("役職者");
        inputShokugyouDto.setShokugyouUserWrite("水産業団体役員");
        inputShokugyouDto.setCorpNo("9876543210987");
        inputShokugyouDto.setCorpName("大漁水産");
        inputShokugyouDto.setCorpAddress("山形県架空市海辺町");

        kanrenshaPersonDto.setInputShokugyouDto(inputShokugyouDto);

        ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogic logic = new ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogic();
        MasterPersonBaseEntity masterPersonBaseEntity = logic.practice(kanrenshaPersonDto);

        // 個人姓名
        assertEquals(inputPersonNameDto.getLastName(), masterPersonBaseEntity.getLastName());
        assertEquals(inputPersonNameDto.getFirstName(), masterPersonBaseEntity.getFirstName());
        assertEquals(inputPersonNameDto.getMiddleName(), masterPersonBaseEntity.getMiddleName());
        assertEquals(inputPersonNameDto.getLastNameKana(), masterPersonBaseEntity.getLastNameKana());
        assertEquals(inputPersonNameDto.getFirstNameKana(), masterPersonBaseEntity.getFirstNameKana());
        assertEquals(inputPersonNameDto.getMiddleNameKana(), masterPersonBaseEntity.getMiddleNameKana());

        // 個人職業
        assertEquals(inputShokugyouDto.getGyoushu(), masterPersonBaseEntity.getGyoushu());
        assertEquals(inputShokugyouDto.getYakushoku(), masterPersonBaseEntity.getYakushoku());
        assertEquals(inputShokugyouDto.getShokugyouUserWrite(), masterPersonBaseEntity.getShokugyouUserWrite());
        assertEquals(inputShokugyouDto.getCorpNo(), masterPersonBaseEntity.getCorpNo());
        assertEquals(inputShokugyouDto.getCorpName(), masterPersonBaseEntity.getCorpName());
        assertEquals(inputShokugyouDto.getCorpAddress(), masterPersonBaseEntity.getCorpAddress());

        // TODO 関連者個人氏名
        // TODO 関連者個人コード
        // TODO 職業編集該否
        
        fail("Not yet implemented");
    }

}
