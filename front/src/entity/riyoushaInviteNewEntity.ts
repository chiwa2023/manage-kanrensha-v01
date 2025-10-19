

export default interface RiyoushaInviteNewInterface {

}




export default class RiyoushaInviteNewEntity implements RiyoushaInviteNewInterface {

    /** テーブルId */
    riyoushaInviteNewId: number;

    /** 最新該否 */
    isLatest: boolean;

    /** メールアドレス */
    mailAddress: string;

    /** 発行コード */
    registCode: string;

    /** 紐づけ予定個人ユーザId */
    personUserId: number;

    /** 紐づけ予定個人ユーザId */
    personUserCode: number;

    /** 紐づけ予定個人ユーザ姓名 */
    personUserName: string;

    /** 組織権限 */
    dantaiRole: string;

    /** 紐づけ予定利用者組織Id */
    riyoushaDantaiId: number;

    /** 紐づけ予定利用者組織Id */
    riyoushaDantaiCode: number;

    /** 紐づけ予定利用者組織Id姓名 */
    riyoushaDantaiName: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaInviteNewId = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.mailAddress = INIT_STRING;
        this.registCode = INIT_STRING;
        this.personUserId = INIT_NUMBER;
        this.personUserCode = INIT_NUMBER;
        this.personUserName = INIT_STRING;
        this.dantaiRole = INIT_STRING;
        this.riyoushaDantaiId = INIT_NUMBER;
        this.riyoushaDantaiCode = INIT_NUMBER;
        this.riyoushaDantaiName = INIT_STRING;

    }

}