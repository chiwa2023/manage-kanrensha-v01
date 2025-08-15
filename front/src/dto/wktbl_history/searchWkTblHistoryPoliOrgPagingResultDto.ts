import type WkTblPartnerPoliOrgHistoryInterface from "../../entity/wkTblPartnerPoliOrgHistoryEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblHistoryPoliOrgPagingResultInterface {

}

export default class SearchWkTblHistoryPoliOrgPagingResultDto extends PagingFrameworkDto implements SearchWkTblHistoryPoliOrgPagingResultInterface {

    /** 政治団体登録候補リスト */
    listWktblPoliOrg: WkTblPartnerPoliOrgHistoryInterface[];

    constructor() {
        super();
        this.listWktblPoliOrg = [];
    }

}