import type WkTblPartnerPersonHistoryInterface from "../../entity/wkTblPartnerPersonHistoryEntity";
import WkTblPartnerPersonHistoryEntity from "../../entity/wkTblPartnerPersonHistoryEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblHistoryPersonCapsuleInterface {

}

/**
 * 個人一括登録履歴ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblHistoryPersonCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblHistoryPersonCapsuleInterface {

    /** 編集対象Entity */
    wkTblPartnerPersonHistoryEntity: WkTblPartnerPersonHistoryInterface;

    constructor() {
        super();
        this.wkTblPartnerPersonHistoryEntity = new WkTblPartnerPersonHistoryEntity();
    }

}
