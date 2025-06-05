import type SearchLocalGovernmentResultInterface from "../../../../dto/address_registory/searchLocalGovernmentResultDto";
import SearchLocalGovernmentResultDto from "../../../../dto/address_registory/searchLocalGovernmentResultDto";
import AddressAllCityInterface from "../../../../entity/addressAllCityEntity"
import AddressAllCityDto from "../../../../entity/addressAllCityEntity"

export default function mockGetLgList(pageNumber:number):SearchLocalGovernmentResultInterface{

    const dto:SearchLocalGovernmentResultDto = new SearchLocalGovernmentResultDto();
    
    const list:AddressAllCityInterface[] = []; 
    const data:number = pageNumber +1;
    list.push(createEntity(1*data));
    list.push(createEntity(2*data));
    list.push(createEntity(3*data));
    list.push(createEntity(4*data));

    dto.listAllCity = list;
    dto.allCount = 467;
    dto.limit = 30;
    dto.pageNumber = 0;

    return dto;
}

function createEntity(index:number):AddressAllCityInterface {

    const entity:AddressAllCityInterface = new AddressAllCityDto();

    entity.addressAllCityId = 200 +index;
    entity.addressName = "和歌山県架空市山麓町" + index;
    entity.lgCode = "12312" +index;

    return entity;
}