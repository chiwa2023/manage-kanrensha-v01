import type WkTblMasterPersonInterface from "../../entity/wkTblMasterPersonEntity";
import WkTblMasterPersonEntity from "../../entity/wkTblMasterPersonEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblStdPersonCapsuleInterface {

}

/**
 * 個人一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblStdPersonCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblStdPersonCapsuleInterface {


    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblMasterPersonInterface;
    constructor() {
        super();
        this.wkTblMasterPersonEntity = new WkTblMasterPersonEntity();
    }
}
