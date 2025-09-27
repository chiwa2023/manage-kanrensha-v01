import type WkTblPartnerPoliOrgHistoryInterface from "../../entity/wkTblPartnerPoliOrgHistoryEntity";
import WkTblPartnerPoliOrgHistoryEntity from "../../entity/wkTblPartnerPoliOrgHistoryEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";


export default interface UpdateWkTblHistoryPoliOrgResultInterface {

}

/**
 * 政治団体一括登録履歴ワークテーブル更新ResultDto
 */
export default class UpdateWkTblHistoryPoliOrgResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblHistoryPoliOrgResultInterface {

    /** 編集対象Entity */
    wkTblPartnerPoliOrgHistoryEntity: WkTblPartnerPoliOrgHistoryInterface;

    constructor() {
        super();
        this.wkTblPartnerPoliOrgHistoryEntity = new WkTblPartnerPoliOrgHistoryEntity();
    }

}
