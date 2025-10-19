export default interface RiyoushaComradeInterface {

}


export default class RiyoushaComradeEntity implements RiyoushaComradeInterface {

    /** API接続利用者Id */
    riyoushaComradeId: number;

    /** API接続利用者コード */
    riyoushaComradeCode: number;

    /** API接続利用者名称 */
    riyoushaComradeName: string;

    /** 最新該否 */
    isLatest: boolean;

    
    /** 組織非該当 */
    isNotOrg:boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaComradeId = INIT_NUMBER;
        this.riyoushaComradeCode = INIT_NUMBER;
        this.riyoushaComradeName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.isNotOrg = INIT_BOOLEAN;
    }
}