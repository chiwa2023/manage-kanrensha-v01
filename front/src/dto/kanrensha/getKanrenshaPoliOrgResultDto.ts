import FrameworkMessageAndResultInterface from "../frameworkMessageAndResultDto";
import type PoliOrgNoInterface from "../partner_poli_org/poliOrgNoDto";
import PoliOrgNoDto from "../partner_poli_org/poliOrgNoDto";

export default interface GetKanrenshaPoliOrgResultInterface {

}


export default class GetKanrenshaPoliOrgResultDto extends FrameworkMessageAndResultInterface
    implements GetKanrenshaPoliOrgResultInterface {

    /** 関連者政治団体Dto */
    kanrenshaPoliOrgDto: PoliOrgNoInterface;

    constructor() {
        super();
        this.kanrenshaPoliOrgDto = new PoliOrgNoDto();
    }
}