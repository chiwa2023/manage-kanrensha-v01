import type WkTblPartnerPersonHistoryInterface from "../../entity/wkTblPartnerPersonHistoryEntity";
import WkTblPartnerPersonHistoryEntity from "../../entity/wkTblPartnerPersonHistoryEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblHistoryPersonResultInterface {

}

/**
 * 個人一括登録履歴ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblHistoryPersonResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblHistoryPersonResultInterface {

    /** 編集対象Entity */
    wkTblPartnerPersonHistoryEntity: WkTblPartnerPersonHistoryInterface;

    constructor() {
        super();
        this.wkTblPartnerPersonHistoryEntity = new WkTblPartnerPersonHistoryEntity();
    }

}
