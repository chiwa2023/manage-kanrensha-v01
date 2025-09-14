import type InputAccessInterface from "../input_access/inputAccessDto";
import InputAccessDto from "../input_access/inputAccessDto";
import InputAddressDto from "../Input_address/inputAddressDto";
import InputOrgNameDto from '../../dto/input_org_name/inputOrgNameDto';
import type InputPersonNameInterface from '../../dto/input_person_name/inputPersonNameDto';
import InputPersonNameDto from '../../dto/input_person_name/inputPersonNameDto';

export default interface RiyoushaAdminInterface {
}

export default class RiyoushaAdminDto implements RiyoushaAdminInterface {

    /** 利用者管理者Id */
    riyoushaAdminId: number;

    /** 利用者管理者コード */
    riyoushaAdminCode: number;

    /** 利用者管理者名称 */
    riyoushaAdminName: string;

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

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaAdminId = INIT_NUMBER;
        this.riyoushaAdminCode = INIT_NUMBER;
        this.riyoushaAdminName = INIT_STRING;
        this.isNotOrg = INIT_BOOLEAN;

        this.inputOrgNameDto = new InputOrgNameDto();
        this.inputPersonNameDto = new InputPersonNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();

    }

}

