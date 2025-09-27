import FrameworkCapsuleInterface from "../frameworkCapsuleDto";
import type PoliOrgNoInterface from "./poliOrgNoDto";
import PoliOrgNoDto from "./poliOrgNoDto";

export default interface SaveKanrenshaPoliOrgCapsuleInterface {


}

export default class SaveKanrenshaPoliOrgCapsuleDto extends FrameworkCapsuleInterface implements SaveKanrenshaPoliOrgCapsuleInterface {

    /** 関連者政治団体Dto */
    kanrenshaPoliOrgDto: PoliOrgNoInterface;

    constructor() {
        super();
        this.kanrenshaPoliOrgDto = new PoliOrgNoDto();
    }

}