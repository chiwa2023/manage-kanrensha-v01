package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostalcode1("100");
        inputAddressDto.setPostalcode2("0001");
        inputAddressDto.setAddressAll("宮崎県実在市山麓町");
        inputAddressDto.setOrginAddressAll("宮崎県実在市");
        inputAddressDto.setAddressPostal("宮崎県実在市山麓町");
        inputAddressDto.setAddressBlock("1丁目75番地");
        inputAddressDto.setAddressBuilding("四角ビル3F");

        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("231016");
        inputAddressDto.setBlkId("31016");
        inputAddressDto.setRsdtId("41016");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setInputAddressDto(inputAddressDto);
        
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


        ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic logic = new ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic();
        MasterPersonAddressEntity addressEntity = logic.practice(kanrenshaPersonDto);

        assertEquals(inputAddressDto.getPostalcode1(), addressEntity.getPostal1());
        assertEquals(inputAddressDto.getPostalcode2(), addressEntity.getPostal2());
        assertEquals(inputAddressDto.getAddressPostal(), addressEntity.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), addressEntity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), addressEntity.getAddressBuilding());
        assertEquals(inputAddressDto.getLgCode(), addressEntity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), addressEntity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), addressEntity.getBlkId());
        assertEquals(inputAddressDto.getRsdtId(), addressEntity.getRsdtId());
        assertEquals(inputAddressDto.getIsPostalEdit(), addressEntity.getIsPostalEdit());
        assertEquals(inputAddressDto.getIsBlockEdit(), addressEntity.getIsBlockEdit());
        assertEquals(inputAddressDto.getIsBuildingEdit(), addressEntity.getIsBlockEdit());
        assertEquals(inputPersonNameDto.getAllName(), addressEntity.getPartnerName());

        // TODO 値の設定法が決まり次第修正する
        // assertEquals("rsdt2", addressEntity.getRsdt2Id());
    }

}
