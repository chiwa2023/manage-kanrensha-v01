import type WkTblPartnerCorpAddMinInterface from "../../entity/wkTblPartnerCorpAddMinEntity";
import WkTblPartnerCorpAddMinEntity from "../../entity/wkTblPartnerCorpAddMinEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblMinCorpCapsuleInterface {

}


/**
 * 企業／団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblMinCorpCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblMinCorpCapsuleInterface {

    /** 編集対象Entity */
    wkTblPartnerCorpAddMinEntity: WkTblPartnerCorpAddMinInterface;

    constructor() {
        super();
        this.wkTblPartnerCorpAddMinEntity = new WkTblPartnerCorpAddMinEntity();
    }

}
