import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchAddressRegistoryCapsuleInterface extends PagingFrameworkInterface {

}


export default class SearchAddressRegistoryCapsuleDto extends PagingFrameworkDto
    implements SearchAddressRegistoryCapsuleInterface {

    /** 住所検索語 */
    addressWords: string;

    constructor() {
        super();
        this.addressWords = "";
    }
}