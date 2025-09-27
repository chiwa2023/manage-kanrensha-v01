import type WkTblPartnerCorpAddMinInterface from "../../entity/wkTblPartnerCorpAddMinEntity";
import WkTblPartnerCorpAddMinEntity from "../../entity/wkTblPartnerCorpAddMinEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblMinCorpResultInterface {

}


/**
 * 企業／団体一括登録マスタ最小ワークテーブル更新ResultDto
 */
export default class UpdateWkTblMinCorpResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblMinCorpResultInterface {

    /** 編集対象Entity */
    wkTblPartnerCorpAddMinEntity: WkTblPartnerCorpAddMinInterface;

    constructor() {
        super();
        this.wkTblPartnerCorpAddMinEntity = new WkTblPartnerCorpAddMinEntity();
    }

}
