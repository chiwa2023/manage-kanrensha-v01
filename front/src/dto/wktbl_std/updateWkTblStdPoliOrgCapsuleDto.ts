import type WkTblMasterPoliOrgInterface from "../../entity/wkTblMasterPoliOrgEntity";
import WkTblMasterPoliOrgEntity from "../../entity/wkTblMasterPoliOrgEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblStdPoliOrgCapsuleInterface {

}
/**
 * 政治団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblStdPoliOrgCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblStdPoliOrgCapsuleInterface {


    /** 編集対象Entity */
    wkTblMasterPoliOrgEntity: WkTblMasterPoliOrgInterface;

    constructor() {
        super();
        this.wkTblMasterPoliOrgEntity = new WkTblMasterPoliOrgEntity();
    }
}
