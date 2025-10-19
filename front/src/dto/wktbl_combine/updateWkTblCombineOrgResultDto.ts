import type WkTblPartnerCombineOrgInterface from "../../entity/wkTblPartnerCombineOrgEntity";
import WkTblPartnerCombineOrgEntity from "../../entity/wkTblPartnerCombineOrgEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";


export default interface UpdateWkTblCombineOrgResultInterface {

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新ResultDto
 */
export default class UpdateWkTblCombineOrgResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblCombineOrgResultInterface {

    /** 編集対象Entity */
    wkTblPartnerCombineOrgEntity: WkTblPartnerCombineOrgInterface;

    constructor() {
        super();
        this.wkTblPartnerCombineOrgEntity = new WkTblPartnerCombineOrgEntity();
    }

}
