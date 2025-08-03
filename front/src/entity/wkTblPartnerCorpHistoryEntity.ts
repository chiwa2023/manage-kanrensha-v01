export default interface WkTblPartnerCorpHistoryInterface {

}

export default class WkTblPartnerCorpHistoryEntity implements WkTblPartnerCorpHistoryInterface {

    /** テーブルId */
    wkPartnerCorpHistoryId: number;

    /** 関連者企業・団体コード */
    wkPartnerCorpHistoryCode: number;

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

    /** 企業・団体関連者コード */
    corpKanrenshaCode: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLERAN: boolean = false;

        this.wkPartnerCorpHistoryId = INIT_NUMBER;
        this.wkPartnerCorpHistoryCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLERAN;
        this.isFinish = INIT_BOOLERAN;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.corpDelegate = INIT_STRING;
        this.corpKanrenshaCode = INIT_STRING;
        this.isAffected = INIT_BOOLERAN;
        this.judgeReason = INIT_STRING;

    }


}
