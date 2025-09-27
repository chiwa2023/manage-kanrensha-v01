import type MasterPoliticalOrganizationInterface from "../../entity/masterPoliticalOrganizationEntity";
import MasterPoliticalOrganizationEntity from "../../entity/masterPoliticalOrganizationEntity";

export default interface GetKanrenshaPoliOrgCapsuleInterface {

}


export default class GetKanrenshaPoliOrgCapsuleDto implements GetKanrenshaPoliOrgCapsuleInterface {


    /** 関連者政治団体マスタEntity */
    masterPoliticalOrganizationEntity: MasterPoliticalOrganizationInterface;

    constructor() {
        this.masterPoliticalOrganizationEntity = new MasterPoliticalOrganizationEntity();
    }
}