import InputAddressDto from "../Input_address/inputAddressDto";

export default interface PoliOrgNoInterface {

}

export default class PoliOrgNoDto implements PoliOrgNoInterface {

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 政治団体名称 */
    poliOrgName: string;

    /** 政治団体住所 */
    inputAddress: InputAddressDto;

    /** 代表者番号 */
    delegateNo: string;

    /** 代表者氏名 */
    delegateName: string;

    /** 会計責任者番号 */
    accountMgrNo: string;

    /** 会計責任者氏名 */
    accountMgrName: string;

    constructor() {

        const INIT_STRING: string = "";

        this.poliOrgNo = INIT_STRING;
        this.poliOrgName = INIT_STRING;
        this.inputAddress = new InputAddressDto();
        this.delegateNo = INIT_STRING;
        this.delegateName = INIT_STRING;
        this.accountMgrNo = INIT_STRING;
        this.accountMgrName = INIT_STRING;

    }
}