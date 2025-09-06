package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoTobaseEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金 太郎");
        inputPersonNameDto.setAllNameKana("うかいけんきん　たろう");
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
        MasterPersonBaseEntity baseEntity = logic.practice(kanrenshaPersonDto);

        // 個人姓名
        assertEquals(inputPersonNameDto.getLastName(), baseEntity.getLastName());
        assertEquals(inputPersonNameDto.getFirstName(), baseEntity.getFirstName());
        assertEquals(inputPersonNameDto.getMiddleName(), baseEntity.getMiddleName());
        assertEquals(inputPersonNameDto.getLastNameKana(), baseEntity.getLastNameKana());
        assertEquals(inputPersonNameDto.getFirstNameKana(), baseEntity.getFirstNameKana());
        assertEquals(inputPersonNameDto.getMiddleNameKana(), baseEntity.getMiddleNameKana());

        // 個人職業
        assertEquals(inputShokugyouDto.getGyoushu(), baseEntity.getGyoushu());
        assertEquals(inputShokugyouDto.getYakushoku(), baseEntity.getYakushoku());
        assertEquals(inputShokugyouDto.getShokugyouUserWrite(), baseEntity.getShokugyouUserWrite());
        assertEquals(inputShokugyouDto.getCorpNo(), baseEntity.getCorpNo());
        assertEquals(inputShokugyouDto.getCorpName(), baseEntity.getCorpName());
        assertEquals(inputShokugyouDto.getCorpAddress(), baseEntity.getCorpAddress());
        assertEquals(inputPersonNameDto.getAllName(), baseEntity.getPartnerName());

        // TODO 関連者個人コード
        // TODO 職業編集該否
        
    }

}
