import type AddressPostalInterface from "../../entity/addressPostalEntity";
import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchPostalCodeResultInterface extends PagingFrameworkInterface {

}

export default class SearchPostalCodeResultDto extends PagingFrameworkDto
    implements SearchPostalCodeResultInterface {

    /** 郵便番号不規則リスト */
    listItem: AddressPostalInterface[];

    constructor() {
        super();
        this.listItem = [];
    }
}