import type WkTblPartnerPoliOrgAddMinInterface from "../../entity/wkTblPartnerPoliOrgAddMin";
import WkTblPartnerPoliOrgAddMinEntity from "../../entity/wkTblPartnerPoliOrgAddMin";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblMinPoliOrgCapsuleInterface {

}
/**
 * 政治団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblMinPoliOrgCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblMinPoliOrgCapsuleInterface {

    /** 編集対象Entity */
    wkTblPartnerPoliOrgAddMinEntity: WkTblPartnerPoliOrgAddMinInterface;

    constructor() {
        super();
        this.wkTblPartnerPoliOrgAddMinEntity = new WkTblPartnerPoliOrgAddMinEntity();
    }
}
