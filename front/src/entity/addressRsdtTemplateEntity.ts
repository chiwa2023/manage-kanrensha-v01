export default interface AddressRsdtTemplateInterface {

}

export default class AddressRsdtTemplateEntity implements AddressRsdtTemplateInterface {

    /** テーブルId */
    addressRsdtId: number;

    /** 地方自治体コード */
    lgCode: string;

    /** 郵便番号 */
    postalCode: string;

    /** 町字コード */
    machiazaId: string;

    /** 地番・住居コード */
    parcelRsdtId: string;

    /** 街区住所 */
    addressBlock: string;

    /** 住所建物 */
    addressBuilding: string;

    /** 適用開始日 */
    effectDate: Date;


    constructor() {

        const INIT_STRING: string = "";

        this.addressRsdtId = 0;
        this.lgCode = INIT_STRING;
        this.postalCode = INIT_STRING;
        this.machiazaId = INIT_STRING;
        this.parcelRsdtId = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.addressBuilding = INIT_STRING;
        this.effectDate = new Date();
    }
}