import type MasterPersonBaseEntityInterface from "../../entity/masterPersonBaseEntity";
import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchWorksApprovalShokugyouResultDtoInterface {

}

export default class SearchWorksApprovalShokugyouResultDto extends PagingFrameworkInterface
    implements SearchWorksApprovalShokugyouResultDtoInterface {

    /** 承認作業用職業リスト */
    listShokugyou: MasterPersonBaseEntityInterface[];

    constructor() {
        super();
        this.listShokugyou = [];
    }

}