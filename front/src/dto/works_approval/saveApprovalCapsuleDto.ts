import type MasterKanrenshaAddressBaseEntityInterface from "../../entity/masterKanrenshaAddressBaseEntity";
import type MasterPersonBaseEntityInterface from "../../entity/masterPersonBaseEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface SaveApprovalCapsuleDtoInterface {

}
export default class SaveApprovalCapsuleDto extends FrameworkCapsuleInterface
    implements SaveApprovalCapsuleDtoInterface {

    /** 住所承認作業リスト */
    listAddress: MasterKanrenshaAddressBaseEntityInterface[];

    /** 承認作業用職業リスト */
    listShokugyou: MasterPersonBaseEntityInterface[];


    constructor() {
        super();
        
        this.listAddress = [];
        this.listShokugyou = [];
    }
}