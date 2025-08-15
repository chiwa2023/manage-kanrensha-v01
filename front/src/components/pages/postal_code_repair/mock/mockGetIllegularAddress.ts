import type GetDetailPostalIllegularResultInterface from "../../../../dto/postal/getDetailPostalIllegularResultDto";
import GetDetailPostalIllegularResultDto from "../../../../dto/postal/getDetailPostalIllegularResultDto";
import type AddressPostalIrregularInterface from "../../../../entity/addressPostalIrregularEntity";
import AddressPostalIrregularDto from "../../../../entity/addressPostalIrregularEntity";

export default function mockGetIllegularAddress(address: string): GetDetailPostalIllegularResultInterface{


    const dto:GetDetailPostalIllegularResultInterface = new GetDetailPostalIllegularResultDto();

    const list: AddressPostalIrregularInterface[] = [];

    list.push(createEntity(1, address));
    list.push(createEntity(2, address));
    list.push(createEntity(3, address));
    list.push(createEntity(4, address));
    list.push(createEntity(5, address));
    list.push(createEntity(6, address));

    dto.listIrregular = list;
    dto.allCount = 88;
    dto.limit = 30;
    dto.pageNumber = 0;

    return dto;
}


function createEntity(index: number, address: string): AddressPostalIrregularInterface {

    const entity: AddressPostalIrregularInterface = new AddressPostalIrregularDto();

    const INIT_STRING: string = "";

    entity.addressPostalIrregularId = index;
    entity.postal1 = "12323" + index;
    entity.lgCode = INIT_STRING;
    entity.addressOrg = address + index + "階";
    entity.addressName = address;
    entity.addressPostal = INIT_STRING;
    entity.addressBlock = INIT_STRING;
    // entity.isAddPostal = INIT_BOOLERAN;
    // entity.isRepairRsdt = INIT_BOOLERAN;

    return entity;

}