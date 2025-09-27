export default interface MasterPersonInterface {

}

export default class MasterPersonEntity implements MasterPersonInterface {

    /** テーブルId */
    masterPersonId: number;

    /** 企業・団体関連者コード */
    personKanrenshaCode: string;

    /** 企業・団体名 */
    partnerName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 個人職業 */
    personShokugyou: string;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.masterPersonId = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.personShokugyou = INIT_STRING;
    }
}

