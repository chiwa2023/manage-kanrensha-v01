import FrameworkMessageAndResultInterface from "../frameworkMessageAndResultDto";
import type CorpNoInterface from "../partner_corp/corpNoDto";
import CorpNoDto from "../partner_corp/corpNoDto";

export default interface GetKanrenshaCorpResultInterface {

}



export default class GetKanrenshaCorpResultDto extends FrameworkMessageAndResultInterface
    implements GetKanrenshaCorpResultInterface {


    /** 関連者企業団体Dto */
    kanrenshaCorpDto: CorpNoInterface;

    constructor() {
        super();
        this.kanrenshaCorpDto = new CorpNoDto();
    }

}