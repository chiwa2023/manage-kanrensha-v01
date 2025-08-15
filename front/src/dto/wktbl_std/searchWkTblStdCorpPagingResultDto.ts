import type WkTblMasterCorpInterface from "../../entity/wkTblMasterCorpEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblStdCorpPagingResultInterface {

}


export default class SearchWkTblStdCorpPagingResultDto extends PagingFrameworkDto implements SearchWkTblStdCorpPagingResultInterface {

    /** 企業／団体登録候補リスト */
    listWktblCorp: WkTblMasterCorpInterface[];

    constructor() {
        super();
        this.listWktblCorp = [];
    }

}