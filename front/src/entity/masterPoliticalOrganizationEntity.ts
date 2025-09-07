export default interface MasterPoliticalOrganizationInterface {

}


export default class MasterPoliticalOrganizationEntity implements MasterPoliticalOrganizationInterface {

    /** テーブルId */
    masterPoliticalOrganizationId: number;

    /** 政治団体関連者コード */
    poliOrgKanrenshaCode: string;

    /** 企業・団体名 */
    partnerName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    poliOrgDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.masterPoliticalOrganizationId = INIT_NUMBER;
        this.poliOrgKanrenshaCode = INIT_STRING;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.poliOrgDelegate = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
    }

}