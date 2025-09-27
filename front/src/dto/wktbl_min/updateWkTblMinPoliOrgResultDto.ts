import type WkTblPartnerPoliOrgAddMinInterface from "../../entity/wkTblPartnerPoliOrgAddMin";
import WkTblPartnerPoliOrgAddMinEntity from "../../entity/wkTblPartnerPoliOrgAddMin";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblMinPoliOrgResultInterface {

}
/**
 * 政治団体一括登録マスタ最小ワークテーブル更新ResultDto
 */
export default class UpdateWkTblMinPoliOrgResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblMinPoliOrgResultInterface {

    /** 編集対象Entity */
    wkTblPartnerPoliOrgAddMinEntity: WkTblPartnerPoliOrgAddMinInterface;

    constructor() {
        super();
        this.wkTblPartnerPoliOrgAddMinEntity = new WkTblPartnerPoliOrgAddMinEntity();
    }
}
