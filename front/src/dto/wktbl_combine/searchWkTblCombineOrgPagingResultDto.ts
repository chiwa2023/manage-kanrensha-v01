import type WkTblPartnerCombineOrgInterface from "../../entity/wkTblPartnerCombineOrgEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblCombineOrgPagingResultInterface {

}


export default class SearchWkTblCombineOrgPagingResultDto extends PagingFrameworkDto implements SearchWkTblCombineOrgPagingResultInterface {

    /** 企業／団体登録候補リスト */
    listCombineOrg: WkTblPartnerCombineOrgInterface[];

    constructor() {
        super();
        this.listCombineOrg = [];
    }

}