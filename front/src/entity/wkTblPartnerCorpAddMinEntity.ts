export default interface WkTblPartnerCorpAddMinInterface {

}


export default class WkTblPartnerCorpAddMinEntity implements WkTblPartnerCorpAddMinInterface {

    /** テーブルId */
    wkTblPartnerCorpAddMinId: number;

    /** 関連者企業・団体コード */
    wkTblPartnerCorpAddMinCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 企業・団体名 */
    partnerName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    corpDelegate: string;

    /** 法人番号 */
    houjinNo: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLERAN: boolean = false;

        this.wkTblPartnerCorpAddMinId = INIT_NUMBER;
        this.wkTblPartnerCorpAddMinCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLERAN;
        this.isFinish = INIT_BOOLERAN;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.corpDelegate = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.isAffected = INIT_BOOLERAN;
        this.judgeReason = INIT_STRING;
    }

}