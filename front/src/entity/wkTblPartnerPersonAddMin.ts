export default interface WkTblPartnerPersonAddMinInterface {

}

export default class WkTblPartnerPersonAddMinEntity implements WkTblPartnerPersonAddMinInterface {

    /** テーブルId */
    wkTblPartnerPersonAddMinId: number;

    /** 関連者個人コード */
    wkTblPartnerPersonAddMinCode: number;

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

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.wkTblPartnerPersonAddMinId = INIT_NUMBER;
        this.wkTblPartnerPersonAddMinCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.isAffected = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
    }
}