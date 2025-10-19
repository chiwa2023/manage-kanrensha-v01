import type WkTblPartnerCombineOrgInterface from "../../entity/wkTblPartnerCombineOrgEntity";
import WkTblPartnerCombineOrgEntity from "../../entity/wkTblPartnerCombineOrgEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto"


export default interface UpdateWkTblCombineOrgCapsuleInterface {

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblCombineOrgCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblCombineOrgCapsuleInterface {

    /** 編集対象Entity */
    wkTblPartnerCombineOrgEntity: WkTblPartnerCombineOrgInterface;

    constructor() {
        super();
        this.wkTblPartnerCombineOrgEntity = new WkTblPartnerCombineOrgEntity();
    }

}
