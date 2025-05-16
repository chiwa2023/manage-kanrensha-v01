import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import type CorpNoApprovalInterface from "../../../../dto/partner_corp/corpNoApprovalDto";
import CorpNoApprovalDto from "../../../../dto/partner_corp/corpNoApprovalDto";

export default function mockGetCorpApprovalList(): CorpNoApprovalInterface[] {
    const list: CorpNoApprovalInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(3));
    list.push(createDto(5));
    list.push(createDto(7));

    list[0].inputAddress.isEditAddressPostal = true;
    list[0].inputAddress.addressPostal = "和歌山県実在市架空町1丁目";
    list[0].rsdtAddressPostl = "和歌山県実在市架空町一丁目";

    list[1].inputAddress.isEditAddressBlock = true;
    list[1].inputAddress.addressBlock = "４４４番地－1";
    list[1].rsdtAddressBlock = "444番地1号";

    list[2].inputAddress.isEditAddressBuilding = true;
    list[2].inputAddress.addressBuilding = "四角ビル109";
    list[2].rsdtAddressBuilding = "";

    list[3].isShiten = true;
    list[3].inputAddress.isEditAddressPostal = false;
    list[3].inputAddress.isEditAddressBlock = false;
    list[3].inputAddress.isEditAddressBuilding = false;
    list[3].corpName = "ABCD企業　三重支店";
    list[3].corpNameKana = "えーびーしーでぃーきぎょう　みえしてん";
    list[3].inputAddress.addressPostal = "三重県山麓市湖畔町";
    list[3].inputAddress.addressBlock = "100番地1000号";
    list[3].inputAddress.addressBuilding = "適当マンション3F";

    list[3].rsdtAddressPostl = "三重県山麓市湖畔町";
    list[3].rsdtAddressBlock = "100番地1000号";
    list[3].rsdtAddressBuilding = "適当マンション3F";
    list[3].corpNo = list[3].corpNo + "-qwerty";

    return list;
}

function createDto(index: number): CorpNoApprovalInterface {
    const dto: CorpNoApprovalInterface = new CorpNoApprovalDto();

    dto.houjinNo = "1234" + index;
    dto.corpNo = "1234" + index + "-abcde";

    dto.corpName = "ABCD企業" + index;
    dto.corpNameKana = "えーびーしーでぃーきぎょう" + index;

    dto.isShiten = false;
    const addressDto: InputAddressDto = new InputAddressDto();
    addressDto.addressPostal = "宮崎県架空市実在町" + index;
    addressDto.addressBlock = "6丁目" + index;
    addressDto.addressBuilding = "五角ビル" + index + "階";
    addressDto.postalcode1 = "12" + index;
    addressDto.postalcode2 = "345" + index;
    addressDto.addressAll = addressDto.addressPostal + addressDto.addressBlock + addressDto.addressBuilding;
    dto.inputAddress = addressDto;

    addressDto.tel1 = "11" + index;
    addressDto.tel2 = "22" + index;
    addressDto.tel3 = "333" + index;

    addressDto.lgCode = "1" + index;
    addressDto.blkId = "3" + index;
    addressDto.rsdtId = "4" + index;
    addressDto.machiazaId = "2" + index;

    dto.orgDelegateCode = "39-1244" + index;
    dto.orgDelegate = "代表者　太郎" + index;


    // 初期値がfalseだが明示
    dto.isApproval = false;

    // 住所比較部分は後で直すのでいったん同じに
    dto.inputAddress.rsdtAddressPostl = dto.inputAddress.addressPostal;
    dto.inputAddress.rsdtAddressBlock = dto.inputAddress.addressBlock;
    dto.inputAddress.rsdtAddressBuilding = dto.inputAddress.addressBuilding;

    return dto;
}