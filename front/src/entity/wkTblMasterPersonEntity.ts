export default interface WkTblMasterPersonInterface {

}

export default class WkTblMasterPersonEntity implements WkTblMasterPersonInterface {

    /** テーブルId */
    wkTblMasterPersonId: number;

    /** ワークテーブルコード */
    wkTblMasterPersonCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 個人名 */
    partnerName: string;

    /** 個人全住所 */
    allAddress: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 住所郵便番号 */
    addressPostal: string;

    /** 住所番地 */
    addressBlock: string;

    /** 住所建物 */
    addressBuilding: string;

    /** 郵便番号1 */
    postal1: string;

    /** 郵便番号2 */
    postal2: string;

    /** 地方自治体コード */
    lgCode: string;

    /** 町字コード */
    machiazaId: string;

    /** 街区コード */
    blkId: string;

    /** 住居コード */
    rsdtId: string;

    /** 住居2コード */
    rsdt2Id: string;

    /** 電話番号1 */
    phon1: string;

    /** 電話番号2 */
    phon2: string;

    /** 電話番号3 */
    phon3: string;

    /** 電子メール */
    email: string;

    /** 代表(公式)url */
    myPortalUrl: string;

    /** SNSサービス名称 */
    snsServiceName: string;

    /** SNSサービスアカウント */
    snsAccount: string;

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

    /** 外国籍該否 */
    isForeign: boolean;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLERAN: boolean = false;

        this.wkTblMasterPersonId = INIT_NUMBER;
        this.wkTblMasterPersonCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLERAN;
        this.isFinish = INIT_BOOLERAN;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.addressPostal = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.addressBuilding = INIT_STRING;
        this.postal1 = INIT_STRING;
        this.postal2 = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.machiazaId = INIT_STRING;
        this.blkId = INIT_STRING;
        this.rsdtId = INIT_STRING;
        this.rsdt2Id = INIT_STRING;
        this.phon1 = INIT_STRING;
        this.phon2 = INIT_STRING;
        this.phon3 = INIT_STRING;
        this.email = INIT_STRING;
        this.myPortalUrl = INIT_STRING;
        this.snsServiceName = INIT_STRING;
        this.snsAccount = INIT_STRING;
        this.lastName = INIT_STRING;
        this.firstName = INIT_STRING;
        this.middleName = INIT_STRING;
        this.lastNameKana = INIT_STRING;
        this.firstNameKana = INIT_STRING;
        this.middleNameKana = INIT_STRING;
        this.gyoushu = INIT_STRING;
        this.yakushoku = INIT_STRING;
        this.shokugyouUserWrite = INIT_STRING;
        this.corpNo = INIT_STRING;
        this.corpAddress = INIT_STRING;
        this.corpName = INIT_STRING;
        this.isForeign = INIT_BOOLERAN;
        this.isAffected = INIT_BOOLERAN;
        this.judgeReason = INIT_STRING;
    }

}
