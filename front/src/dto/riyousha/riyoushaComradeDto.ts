import type InputAccessInterface from "../input_access/inputAccessDto";
import InputAccessDto from "../input_access/inputAccessDto";
import InputAddressDto from "../Input_address/inputAddressDto";
import InputOrgNameDto from '../../dto/input_org_name/inputOrgNameDto';
import type InputPersonNameInterface from '../../dto/input_person_name/inputPersonNameDto';
import InputPersonNameDto from '../../dto/input_person_name/inputPersonNameDto';

export default interface RiyoushaComradeInerface {

}






export default class RiyoushaComradeDto implements RiyoushaComradeInerface {
    /** API接続利用者Id */
    riyoushaComradeId: number;

    /** API接続利用者コード */
    riyoushaComradeCode: number;

    /** API接続利用者名称 */
    riyoushaComradeName: string;


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

        this.riyoushaComradeId = INIT_NUMBER;
        this.riyoushaComradeCode = INIT_NUMBER;
        this.riyoushaComradeName = INIT_STRING;
        this.isNotOrg = INIT_BOOLEAN;

        this.inputOrgNameDto = new InputOrgNameDto();
        this.inputPersonNameDto = new InputPersonNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();
    }

}