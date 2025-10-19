import type MasterCorporationInterface from "../../entity/masterCorporationEntity";
import MasterCorporationEntity from "../../entity/masterCorporationEntity";

export default interface GetKanrenshaCorpCapsuleInterface {

}


export default class GetKanrenshaCorpCapsuleDto implements GetKanrenshaCorpCapsuleInterface {

    /** 関連者企業団体マスタEntity */
    masterCorporationEntity: MasterCorporationInterface;

    constructor() {
        this.masterCorporationEntity = new MasterCorporationEntity();
    }
}