import InputAddressDto from "../Input_address/inputAddressDto"

export default interface PersonNoInterface {

}

export default class PersonNoDto implements PersonNoInterface {

    /** 関連者個人番号 */
    personNo: string

    /** 表示住所 */
    juushoAll: string

    /** 氏名 */
    nameAll: string

    /** 職業 */
    shokugyou: string

    /** 入力職業 */
    inputAddress: InputAddressDto;

    /** 姓名の名 */
    firstName: string;
    /** 姓名のミドルネーム */
    middleName: string;
    /** 姓名の姓 */
    lastName: string;

    /** 職業の業種 */
    gyoushu: string;
    /** 職業の役職 */
    yakushoku: string;
    /** ユーザ記述の職業 */
    shokugyouUserWrite: string;

    constructor() {
        const INIT_STRING: string = "";

        this.personNo = INIT_STRING;
        this.juushoAll = INIT_STRING;
        this.nameAll = INIT_STRING;
        this.shokugyou = INIT_STRING;

        this.inputAddress = new InputAddressDto();
        this.firstName = INIT_STRING;
        this.middleName = INIT_STRING;
        this.lastName = INIT_STRING;

        this.gyoushu = INIT_STRING;
        this.yakushoku = INIT_STRING;
        this.shokugyouUserWrite = INIT_STRING;

    }
}