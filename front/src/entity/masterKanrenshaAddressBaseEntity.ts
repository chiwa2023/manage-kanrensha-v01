export default interface MasterKanrenshaAddressBaseEntityInterface {

}


export default class MasterKanrenshaAddressBaseEntity implements MasterKanrenshaAddressBaseEntityInterface {



    /** テーブルId */
    kanrenshaAddressId: number;

    /** 関連者個人Id */
    kanrenshaMasterId: number;

    /** 関連者個人コード */
    kanrenshaCode: number;

    /** 関連者個人名称 */
    partnerName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 住所郵便番号 */
    addressPostal: string;

    /** 住所番地 */
    addressBlock: string;

    /** 住所建物 */
    addressBuilding: string;

    /** 郵便番号1 */
    postal1: string;

    /** 郵便番号2 */
    postal2: string;

    /** 地方自治体コード */
    lgCode: string;

    /** 町字コード */
    machiazaId: string;

    /** 街区コード */
    blkId: string;

    /** 住居コード */
    rsdtId: string;

    /** 住居2コード */
    rsdt2Id: string;

    /** 住所郵便番号編集該否 */
    isPostalEdit: boolean;

    /** 住所番地編集該否 */
    isBlockEdit: boolean;

    /** 住所建物編集該否 */
    isBuildingEdit: boolean;

    /** 住所郵便番号承認該否 */
    isPostalAccept: boolean;

    /** 住所番地承認該否 */
    isBlockAccept: boolean;

    /** 住所建物承認該否 */
    isBuildingAccept: boolean;

    constructor() {
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.kanrenshaAddressId = INIT_NUMBER;
        this.kanrenshaMasterId = INIT_NUMBER;
        this.kanrenshaCode = INIT_NUMBER;
        this.partnerName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.addressPostal = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.addressBuilding = INIT_STRING;
        this.postal1 = INIT_STRING;
        this.postal2 = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.machiazaId = INIT_STRING;
        this.blkId = INIT_STRING;
        this.rsdtId = INIT_STRING;
        this.rsdt2Id = INIT_STRING;
        this.isPostalEdit = INIT_BOOLEAN;
        this.isBlockEdit = INIT_BOOLEAN;
        this.isBuildingEdit = INIT_BOOLEAN;
        this.isPostalAccept = INIT_BOOLEAN;
        this.isBlockAccept = INIT_BOOLEAN;
        this.isBuildingAccept = INIT_BOOLEAN;
    }

}