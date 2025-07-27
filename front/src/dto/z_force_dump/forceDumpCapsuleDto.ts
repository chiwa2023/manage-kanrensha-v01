import FrameworkCapsuleDto from "../frameworkCapsuleDto";

export default interface ForceDumpCapsuleInterface {

}

export default class ForceDumpCapsuleDto extends FrameworkCapsuleDto implements ForceDumpCapsuleInterface {

    /** 企業団体処理該否 */
    isExecuteCorp: boolean;
    /** 個人処理該否 */
    isExecutePerson: boolean;
    /** 政治団体処理該否 */
    isExecutePoliOrg: boolean;

    /** 開始日時 */
    dateStart: Date;
    /** 終了日時 */
    dateEnd: Date;

    constructor() {
        super();

        const INIT_BOOLEAN: boolean = true;
        const INIT_DATE: Date = new Date();

        this.isExecuteCorp = INIT_BOOLEAN;
        this.isExecutePerson = INIT_BOOLEAN;
        this.isExecutePoliOrg = INIT_BOOLEAN;

        this.dateStart = INIT_DATE;
        this.dateEnd = INIT_DATE;

    }
}