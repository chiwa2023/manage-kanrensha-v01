import type AddressRsdtTemplateInterface from "../../entity/addressRsdtTemplateEntity";
import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchAddressRegistoryResultInterface extends PagingFrameworkInterface {

}

export default class SearchAddressRegistoryResultDto extends PagingFrameworkDto
    implements SearchAddressRegistoryResultInterface {

    /** 郵便番号不規則リスト */
    listRsdt: AddressRsdtTemplateInterface[];

    constructor() {
        super();
        this.listRsdt = [];
    }
}