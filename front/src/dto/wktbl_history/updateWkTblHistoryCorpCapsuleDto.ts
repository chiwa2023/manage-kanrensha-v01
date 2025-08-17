import type WkTblPartnerCorpHistoryInterface from "../../entity/wkTblPartnerCorpHistoryEntity";
import WkTblPartnerCorpHistoryEntity from "../../entity/wkTblPartnerCorpHistoryEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblHistoryCorpCapsuleInterface {

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblHistoryCorpCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblHistoryCorpCapsuleInterface {

    /** 編集対象Entity */
    wkTblPartnerCorpHistoryEntity: WkTblPartnerCorpHistoryInterface;

    constructor() {
        super();
        this.wkTblPartnerCorpHistoryEntity = new WkTblPartnerCorpHistoryEntity();
    }
}
