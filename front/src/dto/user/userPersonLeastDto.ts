export default interface UserPersonLeastInterface {

}

export default class UserPersonLeastDto implements UserPersonLeastInterface {

    /** ユーザId  */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ姓名 */
    userPersonName: string;


    /** 権限リスト */
    listRoles: string[];

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.userPersonId = INIT_NUMBER;
        this.userPersonCode = INIT_NUMBER;
        this.userPersonName = INIT_STRING;
        this.listRoles = [];
    }

}