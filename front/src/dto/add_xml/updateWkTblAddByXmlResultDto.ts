import type WkTblMasterAllByXmlInterface from "../../entity/wkTblMasterAllByXmlEntity";
import WkTblMasterAllByXmlEntity from "../../entity/wkTblMasterAllByXmlEntity";
import FrameworkMessageAndResultDto from "../frameworkMessageAndResultDto";

export default interface UpdateWkTblAddByXmlResultInterface {

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblAddByXmlResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblAddByXmlResultInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlInterface;

    constructor() {
        super();
        this.wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity()
    }
}
