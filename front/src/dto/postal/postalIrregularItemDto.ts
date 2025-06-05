import InputAddressDto from "../Input_address/inputAddressDto";

export default interface PostalIrregularItemInterface {

}

export default class PostalIrregularItemDto implements PostalIrregularItemInterface {

    /** テーブルId */
    addressPostalIrregularId: number;

    /** 郵便番号1 */
    postal1: string;

    /** 地方公共団体コード */
    lgCode: string;

    /** 原文書(郵便番号csv)住所 */
    addressOrg: string;

    /** 住所名 */
    addressName: string;

    inputAddress:InputAddressDto;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.addressPostalIrregularId = INIT_NUMBER;
        this.postal1 = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.addressOrg = INIT_STRING;
        this.addressName = INIT_STRING;
        this.inputAddress = new InputAddressDto();
    }

}