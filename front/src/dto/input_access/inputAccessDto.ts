export default interface InputAccessInterface {

}

export default class InputAccessDto implements InputAccessInterface {

    /** 電話番号1 */
    phon1: string;

    /** 電話番号2 */
    phon2: string;

    /** 電話番号3 */
    phon3: string;

    /** 電子メール */
    email: string;

    /** 代表(公式)サイトurl */
    myPortalUrl: string;

    /** SNSサービスコード */
    snsServiceId: string;

    /** SNSサービスコード */
    snsServiceCode: string;

    /** SNSサービス名称 */
    snsServiceName: string;

    /** SNS玄関url */
    snsPortalUrl: string;

    /** SNSサービスアカウント */
    snsAccount: string;

    constructor() {

        const INIT_STRING = "";

        this.phon1 = INIT_STRING;
        this.phon2 = INIT_STRING;
        this.phon3 = INIT_STRING;
        this.email = INIT_STRING;
        this.myPortalUrl = INIT_STRING;
        this.snsServiceId = INIT_STRING;
        this.snsServiceCode = INIT_STRING;
        this.snsServiceName = INIT_STRING;
        this.snsPortalUrl = INIT_STRING;
        this.snsAccount = INIT_STRING;


    }

}