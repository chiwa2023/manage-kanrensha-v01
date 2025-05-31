import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";
import type JwtTokenInterface from "./jwtTokenDto";
import JwtTokenDto from "./jwtTokenDto";

export default interface LoginUserResultInterface {

}

export default class LoginUserResultDto implements LoginUserResultInterface {

    /** トークンDto  */
    jwtTokenDto: JwtTokenInterface;

    /** ユーザ最低限Dto */
    userPersonLeastDto: UserPersonLeastInterface;

    constructor() {
        this.jwtTokenDto = new JwtTokenDto();
        this.userPersonLeastDto = new UserPersonLeastDto();
    }

}