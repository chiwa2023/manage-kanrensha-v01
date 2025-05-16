import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import type PoliOrgNoApprovalInterface from "../../../../dto/partner_poli_org/poliOrgNoApprovalDto";
import PoliOrgNoApprovalDto from "../../../../dto/partner_poli_org/poliOrgNoApprovalDto";

export default function mockGetPoliOrgNoApprovalList(): PoliOrgNoApprovalInterface[] {

    const list: PoliOrgNoApprovalInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    list[0].inputAddress.isEditAddressPostal = true;
    list[0].inputAddress.addressPostal = "和歌山県実在市架空町1丁目";
    list[0].inputAddress.rsdtAddressPostl = "和歌山県実在市架空町一丁目";

    list[1].inputAddress.isEditAddressBlock = true;
    list[1].inputAddress.addressBlock = "４４４番地－1";
    list[1].inputAddress.rsdtAddressBlock = "444番地1号";

    list[2].inputAddress.isEditAddressBuilding = true;
    list[2].inputAddress.addressBuilding = "四角ビル109";
    list[2].inputAddress.rsdtAddressBuilding = "";

    return list;
}

function createDto(index: number): PoliOrgNoApprovalInterface {

    const dto: PoliOrgNoApprovalInterface = new PoliOrgNoApprovalDto();
    dto.poliOrgNo = "124-3455" + index;

    dto.inputName.orgName = "ちゃらんぽらん政治団体" + index;
    dto.inputName.orgNameKana = "ちゃらんぽらんせいじだんたい" + index;

    dto.delegateNo = "980987" + index;
    dto.delegateName = "代表者　太郎" + index;

    const addressDto: InputAddressDto = new InputAddressDto();
    addressDto.addressPostal = "和歌山県実在市" + index;
    addressDto.addressBlock = "山麓町" + index;
    addressDto.addressBuilding = "四角ビル" + index;
    addressDto.addressAll = addressDto.addressPostal + addressDto.addressBlock + addressDto.addressBuilding;
    addressDto.tel1 = "11" + index;
    addressDto.tel2 = "22" + index;
    addressDto.tel3 = "333" + index;

    addressDto.postalcode1 = "56" + index;
    addressDto.postalcode2 = "789" + index;

    addressDto.lgCode = "1" + index;
    addressDto.blkId = "3" + index;
    addressDto.rsdtId = "4" + index;
    addressDto.machiazaId = "2" + index;

    dto.inputAddress = addressDto;


    // 初期値がfalseだが明示
    dto.isApproval = false;

    // 住所比較部分は後で直すのでいったん同じに
    dto.inputAddress.rsdtAddressPostl = dto.inputAddress.addressPostal;
    dto.inputAddress.rsdtAddressBlock = dto.inputAddress.addressBlock;
    dto.inputAddress.rsdtAddressBuilding = dto.inputAddress.addressBuilding;

    return dto;
}
