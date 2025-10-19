import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchPostalCodeCapsuleInterface extends PagingFrameworkInterface {

}


export default class SearchPostalCodeCapsuleDto extends PagingFrameworkDto
    implements SearchPostalCodeCapsuleInterface {

    /** 住所検索語 */
    addressWords: string;

    constructor() {
        super();
        this.addressWords = "";
    }
}