import type MasterPersonInterface from "../../entity/masterPersonEntity";
import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchKanrenshaPersonResultInterface {

}

export default class SearchKanrenshaPersonResultDto extends PagingFrameworkInterface
    implements SearchKanrenshaPersonResultInterface {

    /** 関連者個人マスタリスト */
    listMasterPerson: MasterPersonInterface[];

    constructor() {
        super();
        this.listMasterPerson = [];

    }
}