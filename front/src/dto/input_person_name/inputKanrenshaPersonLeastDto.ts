export default interface InputKanrenshaPersonLeastInterface {

}



export default class InputKanrenshaPersonLeastDto implements InputKanrenshaPersonLeastInterface {

    /** 関連者個人姓名 */
    personName: string;

    /** 関連者個人コード */
    personKanrenshaCode: string;

    constructor() {
        const INIT_STRING: string = "";

        this.personName = INIT_STRING;
        this.personKanrenshaCode = INIT_STRING;
    }

}