export default interface LoginUserCapsuleInterface {

}

export default class LoginUserCapsuleDto implements LoginUserCapsuleInterface {

    /** ユーザId  */
    userId: string;

    /** パスワード */
    password: string;

    constructor() {
        const INIT_STRING: string = "";

        this.userId = INIT_STRING;
        this.password = INIT_STRING;
    }
}


