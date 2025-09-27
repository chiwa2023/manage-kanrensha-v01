export default interface MasterCorporationInterface {

}


export default class MasterCorporationEntity implements MasterCorporationInterface {

    /** テーブルId */
    masterCorporationId: number;

    /** 企業・団体関連者コード */
    corpKanrenshaCode: string;

    /** 法人番号 */
    houjinNo: string;

    /** 企業・団体名 */
    partnerName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    corpDelegate: string;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.masterCorporationId = INIT_NUMBER;
        this.corpKanrenshaCode = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.corpDelegate = INIT_STRING;
    }
}

