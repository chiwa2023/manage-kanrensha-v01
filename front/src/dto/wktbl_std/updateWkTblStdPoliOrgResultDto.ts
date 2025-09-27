import type WkTblMasterPoliOrgInterface from "../../entity/wkTblMasterPoliOrgEntity";
import WkTblMasterPoliOrgEntity from "../../entity/wkTblMasterPoliOrgEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblStdPoliOrgResultInterface {

}
/**
 * 政治団体一括登録マスタ標準ワークテーブル更新ResultDto
 */
export default class UpdateWkTblStdPoliOrgResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblStdPoliOrgResultInterface {


    /** 編集対象Entity */
    wkTblMasterPoliOrgEntity: WkTblMasterPoliOrgInterface;

    constructor() {
        super();
        this.wkTblMasterPoliOrgEntity = new WkTblMasterPoliOrgEntity();
    }
}
