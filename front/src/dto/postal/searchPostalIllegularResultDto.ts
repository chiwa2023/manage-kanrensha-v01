import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";
import type PostalIrregularItemInterface from "./postalIrregularItemDto";

export default interface SearchPostalIllegularResultInterface extends PagingFrameworkInterface {

}

export default class SearchPostalIllegularResultDto extends PagingFrameworkDto
    implements SearchPostalIllegularResultInterface {

    /** 郵便番号不規則リスト */
    listItem: PostalIrregularItemInterface[];

    constructor() {
        super();
        this.listItem = [];
    }
}