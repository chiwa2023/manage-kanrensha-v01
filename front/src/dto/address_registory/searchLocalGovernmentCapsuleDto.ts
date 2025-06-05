import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchLocalGovernmentCapsuleInterface extends PagingFrameworkInterface {

}


export default class SearchLocalGovernmentCapsuleDto extends PagingFrameworkDto
    implements SearchLocalGovernmentCapsuleInterface {

    /** 住所検索語 */
    addressWords: string;

    constructor() {
        super();
        this.addressWords = "";
    }
}