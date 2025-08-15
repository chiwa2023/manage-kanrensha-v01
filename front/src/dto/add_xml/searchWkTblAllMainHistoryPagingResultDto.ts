import PagingFrameworkDto from "../pagingFrameworkDto";
import type RegistMasterByXmlInterface from "./registMasterByXmlDto";

export default interface SearchWkTblAllMainHistoryPagingResultInterface {

}



export default class SearchWkTblAllMainHistoryPagingResultDto extends PagingFrameworkDto implements SearchWkTblAllMainHistoryPagingResultInterface {

    /** 全XML登録リスト */
    listRegistDto: RegistMasterByXmlInterface[];

    constructor() {
        super();

        this.listRegistDto = [];
    }
}