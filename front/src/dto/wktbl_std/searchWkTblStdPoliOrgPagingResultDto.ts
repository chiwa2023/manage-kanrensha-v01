import type WkTblMasterPoliOrgInterface from "../../entity/wkTblMasterPoliOrgEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblStdPoliOrgPagingResultInterface {

}

export default class SearchWkTblStdPoliOrgPagingResultDto extends PagingFrameworkDto implements SearchWkTblStdPoliOrgPagingResultInterface {

    /** 政治団体登録候補リスト */
    listWktblPoliOrg: WkTblMasterPoliOrgInterface[];

    constructor() {
        super();
        this.listWktblPoliOrg = [];
    }

}