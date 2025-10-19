import type WkTblMasterPersonInterface from "../../entity/wkTblMasterPersonEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblStdPersonPagingResultInterface {

}

export default class SearchWkTblStdPersonPagingResultDto extends PagingFrameworkDto implements SearchWkTblStdPersonPagingResultInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblMasterPersonInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}