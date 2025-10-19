export default interface WkTblPartnerPoliOrgAddMinInterface {

}

export default class WkTblPartnerPoliOrgAddMinEntity implements WkTblPartnerPoliOrgAddMinInterface {

    /** テーブルId */
    wkTblPartnerPoliOrgAddMinId: number;

    /** 関連者政治団体コード */
    wkTblPartnerPoliOrgAddMinCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 政治団体名 */
    partnerName: string;

    /** 政治団体全住所 */
    allAddress: string;

    /** 政治団体代表者 */
    poliOrgDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.wkTblPartnerPoliOrgAddMinId = INIT_NUMBER;
        this.wkTblPartnerPoliOrgAddMinCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.poliOrgDelegate = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
        this.isAffected = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
    }

}