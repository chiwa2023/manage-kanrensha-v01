import type InputAccessInterface from "../input_access/inputAccessDto";
import InputAccessDto from "../input_access/inputAccessDto";
import InputAddressDto from "../Input_address/inputAddressDto";
import InputOrgNameInterface from "../input_org_name/inputOrgNameDto";
import InputOrgNameDto from "../input_org_name/inputOrgNameDto";
import type InputKanrenshaPersonLeastInterface from "../input_person_name/inputKanrenshaPersonLeastDto";
import InputKanrenshaPersonLeastDto from "../input_person_name/inputKanrenshaPersonLeastDto";

export default interface PoliOrgNoInterface {

}

export default class PoliOrgNoDto implements PoliOrgNoInterface {

    /** 関連者政治団体マスタテーブルId */
    masterId:number;

    /** 関連者政治団体連絡先テーブルId */
    accessId:number;

    /** 関連者政治団体住所テーブルId */
    addressId:number;

    /** 関連者政治団体基本テーブルId */
    baseId:number;

    /** 関連者政治団体属性テーブルId */
    propertyId:number;

    /** 政治団体番号 */
    poliOrgKanrenshaCode: string;

    /** 政治団体名称 */
    inputOrgNameDto: InputOrgNameInterface;

    /** 政治団体住所 */
    inputAddressDto: InputAddressDto;

    /** 連絡先Dto */
    inputAccessDto:InputAccessInterface;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 団体代表者関連者最低限Dto */
    orgDelegateLeastDto: InputKanrenshaPersonLeastInterface;

    /** 団体会計責任者関連者最低限Dto */
    accounrMgrLeastDto: InputKanrenshaPersonLeastInterface;

        /** 関連者ユーザ紐づけ該否 */
        isCombineUser:boolean;

    constructor() {

        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.poliOrgKanrenshaCode = INIT_STRING;
        this.inputOrgNameDto = new InputOrgNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        this.accounrMgrLeastDto = new InputKanrenshaPersonLeastDto();
        this.inputAccessDto = new InputAccessDto();
        this.dantaiKbn = INIT_STRING;

        this.masterId = INIT_NUMBER;
        this.accessId = INIT_NUMBER;
        this.addressId = INIT_NUMBER;
        this.baseId = INIT_NUMBER;
        this.propertyId = INIT_NUMBER;

        this.isCombineUser = INIT_BOOLEAN;
    }
}