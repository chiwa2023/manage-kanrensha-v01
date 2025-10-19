import type WkTblPartnerPersonAddMinInterface from "../../entity/wkTblPartnerPersonAddMin";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchWkTblMinPersonPagingResultInterface {

}

export default class SearchWkTblMinPersonPagingResultDto extends PagingFrameworkDto implements SearchWkTblMinPersonPagingResultInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblPartnerPersonAddMinInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}