import type WkTblMasterPersonInterface from "../../entity/wkTblMasterPersonEntity";
import WkTblMasterPersonEntity from "../../entity/wkTblMasterPersonEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblStdPersonResultInterface {

}

/**
 * 個人一括登録マスタ標準ワークテーブル更新ResultDto
 */
export default class UpdateWkTblStdPersonResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblStdPersonResultInterface {


    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblMasterPersonInterface;
    constructor() {
        super();
        this.wkTblMasterPersonEntity = new WkTblMasterPersonEntity();
    }
}
