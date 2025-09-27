import type WkTblPartnerPersonAddMinInterface from "../../entity/wkTblPartnerPersonAddMin";
import WkTblPartnerPersonAddMinEntity from "../../entity/wkTblPartnerPersonAddMin";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblMinPersonResultInterface {

}

/**
 * 個人一括登録マスタ最小ワークテーブル更新ResultDto
 */
export default class UpdateWkTblMinPersonResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblMinPersonResultInterface {

    /** 編集対象Entity */
    wkTblPartnerPersonAddMinEntity: WkTblPartnerPersonAddMinInterface;

    constructor() {
        super();
        this.wkTblPartnerPersonAddMinEntity = new WkTblPartnerPersonAddMinEntity();
    }
}
