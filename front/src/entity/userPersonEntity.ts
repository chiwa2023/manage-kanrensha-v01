export default interface UserPersonInterface {

}

export default class UserPersonEntity implements UserPersonInterface {


    /** テーブルId */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ名称 */
    userPersonName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** email */
    email: string;

    constructor() {
        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.userPersonId = INIT_NUMBER;
        this.userPersonCode = INIT_NUMBER;
        this.userPersonName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.email = INIT_STRING;
    }

}