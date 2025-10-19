import type MasterPoliticalOrganizationInterface from "../../entity/masterPoliticalOrganizationEntity";
import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchKanrenshaPoliOrgResultInterface {

}

export default class SearchKanrenshaPoliOrgResultDto extends PagingFrameworkInterface
    implements SearchKanrenshaPoliOrgResultInterface {

    /** 関連者政治団体マスタリスト */
    listMasterPoliOrg: MasterPoliticalOrganizationInterface[];

    constructor() {
        super();

        this.listMasterPoliOrg = [];

    }
}