import type AddressPostalIrregularInterface from "../../entity/addressPostalIrregularEntity";
import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface GetDetailPostalIllegularResultInterface extends PagingFrameworkInterface {

}

export default class GetDetailPostalIllegularResultDto extends PagingFrameworkDto
    implements GetDetailPostalIllegularResultInterface {

    /** 郵便番号不規則リスト */
    listIrregular: AddressPostalIrregularInterface[];

    constructor() {
        super();
        this.listIrregular = [];
    }
}