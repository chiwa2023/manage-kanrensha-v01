import type WkTblPartnerPersonHistoryInterface from "../../entity/wkTblPartnerPersonHistoryEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblHistoryPersonPagingResultInterface {

}

export default class SearchWkTblHistoryPersonPagingResultDto extends PagingFrameworkDto implements SearchWkTblHistoryPersonPagingResultInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblPartnerPersonHistoryInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}