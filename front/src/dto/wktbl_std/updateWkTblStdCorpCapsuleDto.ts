import type WkTblMasterCorpInterface from "../../entity/wkTblMasterCorpEntity";
import WkTblMasterCorpEntity from "../../entity/wkTblMasterCorpEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblStdCorpCapsuleInterface {

}

/**
 * 企業／団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblStdCorpCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblStdCorpCapsuleInterface {

    /** 編集対象Entity */
    wkTblMasterCorpEntity: WkTblMasterCorpInterface;

    constructor() {
        super();
        this.wkTblMasterCorpEntity = new WkTblMasterCorpEntity();
    }
}
