import type WkTblPartnerPoliOrgAddMinInterface from "../../entity/wkTblPartnerPoliOrgAddMin";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblMinPoliOrgPagingResultInterface {

}

export default class SearchWkTblMinPoliOrgPagingResultDto extends PagingFrameworkDto implements SearchWkTblMinPoliOrgPagingResultInterface {

    /** 政治団体登録候補リスト */
    listWktblPoliOrg: WkTblPartnerPoliOrgAddMinInterface[];

    constructor() {
        super();
        this.listWktblPoliOrg = [];
    }

}