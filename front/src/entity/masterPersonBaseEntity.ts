
export default interface MasterPersonBaseEntityInterface {

}

export default class MasterPersonBaseEntity implements MasterPersonBaseEntityInterface {

    /** テーブルId */
    masterPersonBaseId: number;

    /** 関連者個人Id */
    masterPersonId: number;

    /** 関連者個人コード */
    personKanrenshaCode: string;

    /** 関連者個人名称 */
    partnerName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 姓名の姓 */
    lastName: string;

    /** 姓名の名 */
    firstName: string;

    /** 姓名のミドルネーム */
    middleName: string;

    /** 姓名の姓かな */
    lastNameKana: string;

    /** 姓名の名かな */
    firstNameKana: string;

    /** 姓名のミドルネームかな */
    middleNameKana: string;

    /** 職業名称 */
    personShokugyou: string;

    /** 職業の業種 */
    gyoushu: string;

    /** 職業の役職 */
    yakushoku: string;

    /** ユーザ記述の職業 */
    shokugyouUserWrite: string;

    /** 企業番号 */
    corpNo: string;

    /** 企業所在地 */
    corpAddress: string;

    /** 企業名 */
    corpName: string;

    /** 職業編集該否 */
    isShokyouEdit: boolean;

    /** 職業編集承認該否 */
    isShokyouAccept: boolean;

    constructor() {

        const INIT_STRING:string = "";
        const INIT_NUMBER:number = 0;
        const INIT_BOOLEAN:boolean = false;

        this.masterPersonBaseId = INIT_NUMBER;
        this.masterPersonId = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.partnerName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.lastName = INIT_STRING;
        this.firstName = INIT_STRING;
        this.middleName = INIT_STRING;
        this.lastNameKana = INIT_STRING;
        this.firstNameKana = INIT_STRING;
        this.middleNameKana = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.gyoushu = INIT_STRING;
        this.yakushoku = INIT_STRING;
        this.shokugyouUserWrite = INIT_STRING;
        this.corpNo = INIT_STRING;
        this.corpAddress = INIT_STRING;
        this.corpName = INIT_STRING;
        this.isShokyouEdit = INIT_BOOLEAN;
        this.isShokyouAccept = INIT_BOOLEAN;
    }
}