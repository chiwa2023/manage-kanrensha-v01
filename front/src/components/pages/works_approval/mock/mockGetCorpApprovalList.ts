import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import type CorpNoApprovalInterface from "../../../../dto/partner_corp/corpNoApprovalDto";
import CorpNoApprovalDto from "../../../../dto/partner_corp/corpNoApprovalDto";

export default function mockGetCorpApprovalList(): CorpNoApprovalInterface[] {
    const list: CorpNoApprovalInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(3));
    list.push(createDto(5));
    list.push(createDto(7));

    list[0].inputAddressDto.isPostalEdit = true;
    list[0].inputAddressDto.addressPostal = "和歌山県実在市架空町1丁目";
    list[0].rsdtAddressPostl = "和歌山県実在市架空町一丁目";

    list[1].inputAddressDto.isBlockEdit = true;
    list[1].inputAddressDto.addressBlock = "４４４番地－1";
    list[1].rsdtAddressBlock = "444番地1号";

    list[2].inputAddressDto.isBuildingEdit = true;
    list[2].inputAddressDto.addressBuilding = "四角ビル109";
    list[2].rsdtAddressBuilding = "";

    list[3].isShiten = true;
    list[3].inputAddressDto.isPostalEdit = false;
    list[3].inputAddressDto.isBlockEdit = false;
    list[3].inputAddressDto.isBuildingEdit = false;
    list[3].inputOrgNameDto.orgName = "ABCD企業　三重支店";
    list[3].inputOrgNameDto.orgNameKana = "えーびーしーでぃーきぎょう　みえしてん";
    list[3].inputAddressDto.addressPostal = "三重県山麓市湖畔町";
    list[3].inputAddressDto.addressBlock = "100番地1000号";
    list[3].inputAddressDto.addressBuilding = "適当マンション3F";

    list[3].rsdtAddressPostl = "三重県山麓市湖畔町";
    list[3].rsdtAddressBlock = "100番地1000号";
    list[3].rsdtAddressBuilding = "適当マンション3F";
    list[3].corpKanrenshaCode = list[3].corpKanrenshaCode + "-qwerty";

    return list;
}

function createDto(index: number): CorpNoApprovalInterface {
    const dto: CorpNoApprovalInterface = new CorpNoApprovalDto();

    dto.houjinNo = "1234" + index;
    dto.corpKanrenshaCode = "1234" + index + "-abcde";

    dto.inputOrgNameDto.orgName = "ABCD企業" + index;
    dto.inputOrgNameDto.orgNameKana = "えーびーしーでぃーきぎょう" + index;

    dto.isShiten = false;
    const addressDto: InputAddressDto = new InputAddressDto();
    addressDto.addressPostal = "宮崎県架空市実在町" + index;
    addressDto.addressBlock = "6丁目" + index;
    addressDto.addressBuilding = "五角ビル" + index + "階";
    addressDto.postalcode1 = "12" + index;
    addressDto.postalcode2 = "345" + index;
    addressDto.addressAll = addressDto.addressPostal + addressDto.addressBlock + addressDto.addressBuilding;
    dto.inputAddressDto = addressDto;

    addressDto.tel1 = "11" + index;
    addressDto.tel2 = "22" + index;
    addressDto.tel3 = "333" + index;

    addressDto.lgCode = "1" + index;
    addressDto.blkId = "3" + index;
    addressDto.rsdtId = "4" + index;
    addressDto.machiazaId = "2" + index;

    dto.orgDelegateLeastDto.personKanrenshaCode = "39-1244" + index;
    dto.orgDelegateLeastDto.personName = "代表者　太郎" + index;


    // 初期値がfalseだが明示
    //dto.isApproval = false;

    // 住所比較部分は後で直すのでいったん同じに
    dto.inputAddressDto.rsdtAddressPostl = dto.inputAddressDto.addressPostal;
    dto.inputAddressDto.rsdtAddressBlock = dto.inputAddressDto.addressBlock;
    dto.inputAddressDto.rsdtAddressBuilding = dto.inputAddressDto.addressBuilding;

    return dto;
}