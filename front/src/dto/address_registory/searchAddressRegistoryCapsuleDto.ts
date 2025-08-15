import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchAddressRegistoryCapsuleInterface extends PagingFrameworkInterface {

}


export default class SearchAddressRegistoryCapsuleDto extends PagingFrameworkDto
    implements SearchAddressRegistoryCapsuleInterface {

    /** 自治体コード */
    lgCode: string;

    constructor() {
        super();
        this.lgCode = "";
    }
}