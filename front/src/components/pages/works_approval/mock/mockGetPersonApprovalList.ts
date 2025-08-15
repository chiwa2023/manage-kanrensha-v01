import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import InputPersonNameInterface from "../../../../dto/input_person_name/inputPersonNameDto";
import InputPersonNameDto from "../../../../dto/input_person_name/inputPersonNameDto";
import PersonNoApprovalInterface from "../../../../dto/partner_person/personNoApprovalDto";
import PersonNoApprovalDto from "../../../../dto/partner_person/personNoApprovalDto";

export default function mockGetPersonApprovalList(): PersonNoApprovalInterface[] {

    const list: PersonNoApprovalInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    list[0].inputAddress.isEditAddressPostal = true;
    list[0].inputAddress.addressPostal = "和歌山県実在市架空町1丁目";
    list[0].rsdtAddressPostl = "和歌山県実在市架空町一丁目";

    list[1].inputAddress.isEditAddressBlock = true;
    list[1].inputAddress.addressBlock = "４４４番地－1";
    list[1].rsdtAddressBlock = "444番地1号";

    list[2].inputAddress.isEditAddressBuilding = true;
    list[2].inputAddress.addressBuilding = "四角ビル109";
    list[2].rsdtAddressBuilding = "";

    list[3].inputShokugyou.gyoushu = "林業";
    list[3].inputShokugyou.yakushoku = "一般職員";
    list[3].allShokugyou = "林業社員・職員";
    list[3].inputShokugyou.shokugyouUserWrite = "";

    return list;
}


function createDto(index: number): PersonNoApprovalInterface {
    const dto: PersonNoApprovalInterface = new PersonNoApprovalDto();
    dto.personNo = "11-333" + index;
    dto.nameAll = "迂回　献金太郎" + index;
    dto.juushoAll = "山梨県架空市湖畔町" + index;
    dto.shokugyou = "建設業従事者・職員" + index;

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

    const nameDto: InputPersonNameInterface = new InputPersonNameDto();

    nameDto.lastName = "迂回";
    nameDto.middleName = "ミカエル";
    nameDto.firstName = "献金太郎";

    nameDto.lastNameKana = "うかい";
    nameDto.middleNameKana = "みかえる";
    nameDto.firstNameKana = "けんきんたろう";

    nameDto.allNameKana = nameDto.lastNameKana + "　" + nameDto.middleNameKana + nameDto.firstNameKana;
    nameDto.allName = nameDto.lastName + "　" + nameDto.middleName + nameDto.firstName;

    dto.inputName = nameDto;

    dto.inputShokugyou.gyoushu = "建設";
    dto.inputShokugyou.yakushoku = "一般職員";
    dto.inputShokugyou.shokugyouUserWrite = "申告職業" + index;
    dto.shokugyou = dto.inputShokugyou.shokugyouUserWrite;

    // 初期値がfalseだが明示
    dto.isApproval = false;

    // 住所比較部分は後で直すのでいったん同じに
    dto.inputAddress.rsdtAddressPostl = dto.inputAddress.addressPostal;
    dto.inputAddress.rsdtAddressBlock = dto.inputAddress.addressBlock;
    dto.inputAddress.rsdtAddressBuilding = dto.inputAddress.addressBuilding;

    return dto;
}