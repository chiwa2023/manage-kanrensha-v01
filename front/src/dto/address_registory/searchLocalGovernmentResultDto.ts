import type AddressAllCityInterface from "../../entity/addressAllCityEntity";
import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchLocalGovernmentResultInterface extends PagingFrameworkInterface {

}

export default class SearchLocalGovernmentResultDto extends PagingFrameworkDto
    implements SearchLocalGovernmentResultInterface {

    /** 郵便番号不規則リスト */
    listAllCity: AddressAllCityInterface[];

    constructor() {
        super();
        this.listAllCity = [];
    }
}