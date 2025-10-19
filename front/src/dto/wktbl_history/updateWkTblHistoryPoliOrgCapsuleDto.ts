import type WkTblPartnerPoliOrgHistoryInterface from "../../entity/wkTblPartnerPoliOrgHistoryEntity";
import WkTblPartnerPoliOrgHistoryEntity from "../../entity/wkTblPartnerPoliOrgHistoryEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";


export default interface UpdateWkTblHistoryPoliOrgCapsuleInterface {

}

/**
 * 政治団体一括登録履歴ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblHistoryPoliOrgCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblHistoryPoliOrgCapsuleInterface {

    /** 編集対象Entity */
    wkTblPartnerPoliOrgHistoryEntity: WkTblPartnerPoliOrgHistoryInterface;

    constructor() {
        super();
        this.wkTblPartnerPoliOrgHistoryEntity = new WkTblPartnerPoliOrgHistoryEntity();
    }

}
