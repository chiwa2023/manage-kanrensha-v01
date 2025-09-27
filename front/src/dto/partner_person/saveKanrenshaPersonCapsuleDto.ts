import FrameworkCapsuleInterface from "../frameworkCapsuleDto";
import type PersonNoInterface from "./personNoDto";
import PersonNoDto from "./personNoDto";

export default interface SaveKanrenshaPersonCapsuleInterface {

}


export default class SaveKanrenshaPersonCapsuleDto extends FrameworkCapsuleInterface
    implements SaveKanrenshaPersonCapsuleInterface {

    /** 関連者個人Dto */
    kanrenshaPersonDto: PersonNoInterface;

    constructor() {
        super();
        this.kanrenshaPersonDto = new PersonNoDto();
    }

}