import type WkTblMasterAllByXmlInterface from "../../entity/wkTblMasterAllByXmlEntity";
import PagingFrameworkDto from "../pagingFrameworkDto";


export default interface SearchWkTblAddByXmlPagingResultInterface {

}


export default class SearchWkTblAddByXmlPagingResultDto extends PagingFrameworkDto implements SearchWkTblAddByXmlPagingResultInterface {

    /** 全XML登録リスト */
    listXmlEntity: WkTblMasterAllByXmlInterface[];

    constructor() {
        super();
        this.listXmlEntity = [];
    }
}