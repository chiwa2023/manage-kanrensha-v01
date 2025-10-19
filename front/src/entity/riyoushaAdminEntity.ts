export default interface RiyoushaAdminInterface {

}


export default class RiyoushaAdminEntity implements RiyoushaAdminInterface {

    /** 利用者管理者Id */
    riyoushaAdminId: number;

    /** 利用者管理者コード */
    riyoushaAdminCode: number;

    /** 利用者管理者名称 */
    riyoushaAdminName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 組織非該当 */
    isNotOrg: boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;


        this.riyoushaAdminId = INIT_NUMBER;
        this.riyoushaAdminCode = INIT_NUMBER;
        this.riyoushaAdminName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.isNotOrg = INIT_BOOLEAN;
    }

}