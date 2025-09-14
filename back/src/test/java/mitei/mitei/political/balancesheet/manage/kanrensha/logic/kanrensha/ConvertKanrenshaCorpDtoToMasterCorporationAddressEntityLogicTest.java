package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;

/**
 * ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic単体テスト
 */
class ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogicTest {

    @Test
    @Tag("TableTruncate")
    void test() {

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
        inputAddressDto.setBlkId("331016");
        inputAddressDto.setRsdtId("431016");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();
        kanrenshaCorpDto.setInputAddressDto(inputAddressDto);

        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("超元素製造組合");
        inputOrgNameDto.setOrgNameKana("ちょうげんそせいぞうくみあい");
        kanrenshaCorpDto.setInputOrgNameDto(inputOrgNameDto);

        ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic logic = new ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic();
        MasterCorporationAddressEntity addressEntity = logic.practice(kanrenshaCorpDto);

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

        assertEquals(inputOrgNameDto.getOrgName(), addressEntity.getPartnerName());

        // TODO 値の設定法が決まり次第修正する
        // assertEquals("rsdt2", addressEntity.getRsdt2Id());
    }

}
