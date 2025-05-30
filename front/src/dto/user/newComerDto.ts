export default interface NewComerInterface {

}

export default class NewComerDto implements NewComerInterface {

    /** メールアドレス */
    mailAddress: string;

    /** ニックネーム */
    nickName: string;

    /** パスワード */
    password: string;

    /** 登録用コード */
    registCode: string;

    /** 有効期限 */
    limitDateTime: Date;

    /** ユーザ区分(ロール・権限) */
    role: string;

    /** メッセージ */
    message: string;

    /** 処理成功該否 */
    isSuccess: boolean;

    constructor() {
        const INIT_STRING: string = "";
        this.mailAddress = INIT_STRING;
        this.nickName = INIT_STRING;
        this.password = INIT_STRING;
        this.registCode = INIT_STRING;
        this.limitDateTime = new Date(1948, 7, 28);
        this.role = INIT_STRING;
        this.message = INIT_STRING;
        this.isSuccess = false;
    }
}