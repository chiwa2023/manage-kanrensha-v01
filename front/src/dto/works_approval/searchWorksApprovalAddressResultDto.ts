import type MasterKanrenshaAddressBaseEntityInterface from "../../entity/masterKanrenshaAddressBaseEntity";
import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchWorksApprovalAddressResultDtoInterface {

}

export default class SearchWorksApprovalAddressResultDto extends PagingFrameworkInterface
    implements SearchWorksApprovalAddressResultDtoInterface {

    /** 住所承認作業リスト */
    listAddress: MasterKanrenshaAddressBaseEntityInterface[];

    constructor() {
        super();
        this.listAddress = [];
    }

}