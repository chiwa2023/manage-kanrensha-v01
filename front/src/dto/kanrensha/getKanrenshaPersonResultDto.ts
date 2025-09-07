import FrameworkMessageAndResultInterface from "../frameworkMessageAndResultDto";
import type PersonNoInterface from "../partner_person/personNoDto";
import PersonNoDto from "../partner_person/personNoDto";

export default interface GetKanrenshaPersonResultInterface {

}


export default class GetKanrenshaPersonResultDto extends FrameworkMessageAndResultInterface
    implements GetKanrenshaPersonResultInterface {

    /** 関連者個人Dto */
    kanrenshaPersonDto: PersonNoInterface;

    constructor() {
        super();
        this.kanrenshaPersonDto = new PersonNoDto();
    }
}