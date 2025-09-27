import type WkTblMasterAllByXmlInterface from "../../entity/wkTblMasterAllByXmlEntity";
import WkTblMasterAllByXmlEntity from "../../entity/wkTblMasterAllByXmlEntity";
import FrameworkCapsuleDto from "../frameworkCapsuleDto";

export default interface UpdateWkTblAddByXmlCapsuleInterface {

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblAddByXmlCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblAddByXmlCapsuleInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlInterface;

    constructor() {
        super();
        this.wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity()
    }

}
