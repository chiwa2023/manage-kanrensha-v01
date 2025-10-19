import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";

export default interface RefleshPasswordCapsuleInterface {

}

export default class RefleshPasswordCapsuleDto implements RefleshPasswordCapsuleInterface {


    /** 最小限UserDto */
    userPersonLeastDto: UserPersonLeastInterface;

    /** 新パスワード */
    newPassword: string;

    /** 旧パスワード */
    oldPassword: string;


    constructor() {
        const INIT_STRING: string = "";

        this.userPersonLeastDto = new UserPersonLeastDto();
        this.newPassword = INIT_STRING;
        this.oldPassword = INIT_STRING;
    }
}