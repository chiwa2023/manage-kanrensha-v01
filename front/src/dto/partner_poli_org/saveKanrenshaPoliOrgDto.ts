import FrameworkCapsuleInterface from "../frameworkCapsuleDto";
import type PoliOrgNoInterface from "./poliOrgNoDto";
import PoliOrgNoDto from "./poliOrgNoDto";

export default interface SaveKanrenshaPoliOrgInterface {


}

export default class SaveKanrenshaPoliOrgDto extends FrameworkCapsuleInterface implements SaveKanrenshaPoliOrgInterface {

    /** 関連者政治団体Dto */
    kanrenshaPoliOrgDto: PoliOrgNoInterface;

    constructor() {
        super();
        this.kanrenshaPoliOrgDto = new PoliOrgNoDto();
    }

}