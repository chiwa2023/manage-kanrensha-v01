import type WkTblPartnerPersonAddMinInterface from "../../entity/wkTblPartnerPersonAddMin";
import WkTblPartnerPersonAddMinEntity from "../../entity/wkTblPartnerPersonAddMin";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface UpdateWkTblMinPersonCapsuleInterface {

}

/**
 * 個人一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
export default class UpdateWkTblMinPersonCapsuleDto extends FrameworkCapsuleInterface
    implements UpdateWkTblMinPersonCapsuleInterface {

    /** 編集対象Entity */
    wkTblPartnerPersonAddMinEntity: WkTblPartnerPersonAddMinInterface;

    constructor() {
        super();
        this.wkTblPartnerPersonAddMinEntity = new WkTblPartnerPersonAddMinEntity();
    }
}
