import type WkTblPartnerCorpAddMinInterface from "../../entity/wkTblPartnerCorpAddMinEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblMinCorpPagingResultInterface {

}


export default class SearchWkTblMinCorpPagingResultDto extends PagingFrameworkDto implements SearchWkTblMinCorpPagingResultInterface {

    /** 企業／団体登録候補リスト */
    listWktblCorp: WkTblPartnerCorpAddMinInterface[];

    constructor() {
        super();
        this.listWktblCorp = [];
    }

}