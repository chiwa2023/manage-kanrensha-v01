import type MasterCorporationInterface from "../../entity/masterCorporationEntity";
import type MasterPersonInterface from "../../entity/masterPersonEntity";
import MasterPersonEntity from "../../entity/masterPersonEntity";
import type MasterPoliticalOrganizationInterface from "../../entity/masterPoliticalOrganizationEntity";
import FrameworkMessageAndResultInterface from "../frameworkMessageAndResultDto";

export default interface GetKanrenshaAllByUserResultInterface {

}



export default class GetKanrenshaAllByUserResultDto extends FrameworkMessageAndResultInterface
    implements GetKanrenshaAllByUserResultInterface {

    /** 関連者個人マスタ */
    masterPersonEntity: MasterPersonInterface;

    /** 関連者企業団体マスタリスト */
    listCorpEntity: MasterCorporationInterface[];

    /** 関連者政治団体マスタリスト */
    listPoliOrgEntity: MasterPoliticalOrganizationInterface[];

    constructor() {
        super();

        this.masterPersonEntity = new MasterPersonEntity();
        this.listCorpEntity = [];
        this.listPoliOrgEntity = [];
    }
}