import type WkTblMasterCorpInterface from "../../entity/wkTblMasterCorpEntity";
import WkTblMasterCorpEntity from "../../entity/wkTblMasterCorpEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblStdCorpResultInterface {

}

/**
 * 企業／団体一括登録マスタ標準ワークテーブル更新ResultDto
 */
export default class UpdateWkTblStdCorpResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblStdCorpResultInterface {

    /** 編集対象Entity */
    wkTblMasterCorpEntity: WkTblMasterCorpInterface;

    constructor() {
        super();
        this.wkTblMasterCorpEntity = new WkTblMasterCorpEntity();
    }
}
