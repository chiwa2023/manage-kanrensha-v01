import type SearchPostalCodeResultInterface from "../../../../dto/postal/searchPostalCodeResultDto";
import SearchPostalCodeResultDto from "../../../../dto/postal/searchPostalCodeResultDto";
import type AddressPostalInterface from "../../../../entity/addressPostalEntity";
import AddressPostalDto from "../../../../entity/addressPostalEntity";

export default function mockGetPostalCodeList():SearchPostalCodeResultInterface{

    const dto:SearchPostalCodeResultInterface = new SearchPostalCodeResultDto();

    const list:AddressPostalInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));
    list.push(createEntity(5));
    
    dto.listItem = list;
    dto.allCount = 146;
    dto.limit = 30;
    dto.pageNumber = 0;

    return dto;
}


function createEntity(index:number){

    const entity:AddressPostalInterface = new AddressPostalDto();

    entity.addressPostalId = 300 + index;
    entity.addressOrg ="湖畔町"+index +"丁目";
    entity.addressName ="架空県実在町" + "湖畔町"+index +"丁目";
    entity.lgCode = "9133"+index;
    entity.postal1 = "246801" + index;
    entity.postal2 = "801" + index;

    return entity;
}