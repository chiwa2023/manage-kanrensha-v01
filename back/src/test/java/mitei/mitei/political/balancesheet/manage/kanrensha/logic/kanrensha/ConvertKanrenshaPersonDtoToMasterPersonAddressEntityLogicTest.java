package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;

/**
 * ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogicTest {

    @Test
    void test() throws Exception {

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostal1("100");
        inputAddressDto.setPostal2("0001");
        inputAddressDto.setAddressAll("宮崎県実在市山麓町");
        inputAddressDto.setOrginAddressAll("宮崎県実在市");
        inputAddressDto.setAddressPostal("宮崎県実在市山麓町");
        inputAddressDto.setAddressBlock("1丁目75番地");
        inputAddressDto.setAddressBuilding("四角ビル3F");

        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("131016");
        inputAddressDto.setBlkId("131016");
        inputAddressDto.setRsdtId("131016");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setInputAddressDto(inputAddressDto);

        ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic logic = new ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic();
        MasterPersonAddressEntity masterPersonAddressEntity = logic.practice(kanrenshaPersonDto);

        assertEquals(inputAddressDto.getPostal1(), masterPersonAddressEntity.getPostal1());
        assertEquals(inputAddressDto.getPostal2(), masterPersonAddressEntity.getPostal2());
        assertEquals(inputAddressDto.getAddressPostal(), masterPersonAddressEntity.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), masterPersonAddressEntity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), masterPersonAddressEntity.getAddressBuilding());
        assertEquals(inputAddressDto.getLgCode(), masterPersonAddressEntity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), masterPersonAddressEntity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), masterPersonAddressEntity.getBlkId());
        assertEquals(inputAddressDto.getRsdtId(), masterPersonAddressEntity.getRsdtId());
        assertEquals(inputAddressDto.getIsPostalEdit(), masterPersonAddressEntity.getIsPostalEdit());
        assertEquals(inputAddressDto.getIsBlockEdit(), masterPersonAddressEntity.getIsBlockEdit());
        assertEquals(inputAddressDto.getIsBuildingEdit(), masterPersonAddressEntity.getIsBlockEdit());

        // TODO 値の設定法が決まり次第修正する
        assertEquals("rsdt2", masterPersonAddressEntity.getRsdt2Id());

        fail("Not yet implemented");
    }

}
