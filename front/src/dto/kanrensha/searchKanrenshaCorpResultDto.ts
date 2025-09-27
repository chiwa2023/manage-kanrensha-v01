import type MasterCorporationInterface from "../../entity/masterCorporationEntity";
import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchKanrenshaCorpResultInterface {

}

export default class SearchKanrenshaCorpResultDto extends PagingFrameworkInterface
    implements SearchKanrenshaCorpResultInterface {

    /** 関連者企業団体マスタリスト */
    listMasterCorp: MasterCorporationInterface[];

    constructor() {
        super();

        this.listMasterCorp = [];
    }
}