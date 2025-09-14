export default interface PartnerCombineOrgBaseInterface {

}

export default class PartnerCombineOrgBaseEntity implements PartnerCombineOrgBaseInterface {

    /** テーブルId */
    partnerCombineOrgId: number;

    /** 紐づけコード */
    partnerCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

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


    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.partnerCombineOrgId = INIT_NUMBER;
        this.partnerCombineOrgCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.kanrenshaKbn = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.personName = INIT_STRING;
        this.orgKanrenshaCode = INIT_STRING;
        this.orgName = INIT_STRING;
    }


}