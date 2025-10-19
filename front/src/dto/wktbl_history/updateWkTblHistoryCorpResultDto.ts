import type WkTblPartnerCorpHistoryInterface from "../../entity/wkTblPartnerCorpHistoryEntity";
import WkTblPartnerCorpHistoryEntity from "../../entity/wkTblPartnerCorpHistoryEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblHistoryCorpResultInterface {

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新ResultDto
 */
export default class UpdateWkTblHistoryCorpResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblHistoryCorpResultInterface {

    /** 編集対象Entity */
    wkTblPartnerCorpHistoryEntity: WkTblPartnerCorpHistoryInterface;

    constructor() {
        super();
        this.wkTblPartnerCorpHistoryEntity = new WkTblPartnerCorpHistoryEntity();
    }
}
