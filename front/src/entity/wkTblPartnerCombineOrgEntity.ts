export default interface WkTblPartnerCombineOrgInterface {

}

export default class WkTblPartnerCombineOrgEntity implements WkTblPartnerCombineOrgInterface {

    /** テーブルId */
    wkTblPartnerCombineOrgId: number;

    /** 紐づけコード */
    wkTblPartnerCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 紐づけ関連者区分 */
    kanrenshaKbn: number;

    /** 個人関連者コード */
    personKanrenshaCode: string;

    /** 個人氏名 */
    personName: string;

    /** 団体関連者コード */
    orgKanrenshaCode: string;

    /** 団体代表者名称 */
    orgName: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    /** 紐づけ開始年 */
    startYear: number;

    /** 紐づけ終了年 */
    endYear: number;

    /** 登録年配列 */
    yearArrayText: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLERAN: boolean = false;

        this.wkTblPartnerCombineOrgId = INIT_NUMBER;
        this.wkTblPartnerCombineOrgCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLERAN;
        this.isFinish = INIT_BOOLERAN;
        this.kanrenshaKbn = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.personName = INIT_STRING;
        this.orgKanrenshaCode = INIT_STRING;
        this.orgName = INIT_STRING;
        this.isAffected = INIT_BOOLERAN;
        this.judgeReason = INIT_STRING;
        this.startYear = INIT_NUMBER;
        this.endYear = INIT_NUMBER;
        this.yearArrayText = INIT_STRING;
    }

}