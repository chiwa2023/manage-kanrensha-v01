export default interface WkTblPartnerPoliOrgHistoryInterface {

}

export default class WkTblPartnerPoliOrgHistoryEntity implements WkTblPartnerPoliOrgHistoryInterface {

    /** テーブルId */
    wkPartnerPoliOrgHistoryId: number;

    /** 関連者企業・団体コード */
    wkPartnerPoliOrgHistoryCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 企業・団体名 */
    partnerName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    poliOrgDelegate: string;

    /** 企業・団体関連者コード */
    poliOrgKanrenshaCode: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;


    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLERAN: boolean = false;

        this.wkPartnerPoliOrgHistoryId = INIT_NUMBER;
        this.wkPartnerPoliOrgHistoryCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLERAN;
        this.isFinish = INIT_BOOLERAN;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.poliOrgDelegate = INIT_STRING;
        this.poliOrgKanrenshaCode = INIT_STRING;
        this.isAffected = INIT_BOOLERAN;
        this.judgeReason = INIT_STRING;
    }


}
