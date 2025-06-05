import type SearchAddressRegistoryResultInterface from "../../../../dto/address_registory/searchAddressRegistoryResultDto";
import SearchAddressRegistoryResultDto from "../../../../dto/address_registory/searchAddressRegistoryResultDto";
import type AddressRsdtTemplateInterface from "../../../../entity/addressRsdtTemplateEntity";
import AddressRsdtTemplateEntity from "../../../../entity/addressRsdtTemplateEntity";

export default function mockGetAddressRsdtList(pageNumber: number, llgCode: string): SearchAddressRegistoryResultInterface {

    const dto:SearchAddressRegistoryResultInterface = new SearchAddressRegistoryResultDto();
    
    const list: AddressRsdtTemplateInterface[] = [];
    const data: number = pageNumber + 1;
    list.push(createEntity(1 * data, llgCode));
    list.push(createEntity(2 * data, llgCode));
    list.push(createEntity(3 * data, llgCode));
    list.push(createEntity(4 * data, llgCode));
    list.push(createEntity(5 * data, llgCode));

    dto.listRsdt = list;
    dto.allCount = 366;
    dto.limit = 30;
    dto.pageNumber = pageNumber;

    return dto;
}


function createEntity(index: number, llgCode: string): AddressRsdtTemplateInterface {

    const entity: AddressRsdtTemplateInterface = new AddressRsdtTemplateEntity();

    entity.addressRsdtId = 1000 + index;
    entity.addressBlock = "和歌山県架空市山麓町2丁目" + index;
    entity.addressBuilding = "三角ビル" + index + "号室";
    entity.lgCode = llgCode;
    entity.postalCode = "9898986";
    entity.machiazaId = index + "100";
    entity.parcelRsdtId = index + "30";


    return entity;
}