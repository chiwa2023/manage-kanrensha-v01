import type InputAccessInterface from "../input_access/inputAccessDto";
import InputAccessDto from "../input_access/inputAccessDto";
import InputAddressDto from "../Input_address/inputAddressDto";
import InputOrgNameDto from '../../dto/input_org_name/inputOrgNameDto';
import type InputPersonNameInterface from '../../dto/input_person_name/inputPersonNameDto';
import InputPersonNameDto from '../../dto/input_person_name/inputPersonNameDto';
import type RiyoushaManagerEntityInterface from "../../entity/riyoushaManagerEntity";

export default interface RiyoushaManagerInterface {

}


export default class RiyoushaManagerDto implements RiyoushaManagerInterface {


    /** 利用者運営者Id */
    riyoushaManagerId: number;

    /** 利用者運営者コード */
    riyoushaManagerCode: number;

    /** 利用者運営者名称 */
    riyoushaManagerName: string;

    /** 組織非該当 */
    isNotOrg: boolean;


    /** 個人姓名入力 */
    inputPersonNameDto: InputPersonNameInterface;

    /** 団体名称入力 */
    inputOrgNameDto: InputOrgNameDto;

    /** 住所入力 */
    inputAddressDto: InputAddressDto;

    /** 連絡先入力 */
    inputAccessDto: InputAccessInterface;

    /** 連絡先テーブルId */
    accessId: number;

    /** 住所テーブルId */
    addressId: number;

    /** 名称テーブルId */
    nameId: number;

    /** 組織所属員リスト */
    listPerson: RiyoushaManagerEntityInterface[];

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaManagerId = INIT_NUMBER;
        this.riyoushaManagerCode = INIT_NUMBER;
        this.riyoushaManagerName = INIT_STRING;
        this.isNotOrg = INIT_BOOLEAN;

        this.inputOrgNameDto = new InputOrgNameDto();
        this.inputPersonNameDto = new InputPersonNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();

        this.accessId = INIT_NUMBER;
        this.addressId = INIT_NUMBER;
        this.nameId = INIT_NUMBER;

        this.listPerson = [];
    }

}