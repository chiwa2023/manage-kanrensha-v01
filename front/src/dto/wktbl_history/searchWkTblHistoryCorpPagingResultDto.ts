import type WkTblPartnerCorpHistoryInterface from "../../entity/wkTblPartnerCorpHistoryEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblHistoryCorpPagingResultInterface {

}


export default class SearchWkTblHistoryCorpPagingResultDto extends PagingFrameworkDto implements SearchWkTblHistoryCorpPagingResultInterface {

    /** 企業／団体登録候補リスト */
    listWktblCorp: WkTblPartnerCorpHistoryInterface[];

    constructor() {
        super();
        this.listWktblCorp = [];
    }

}