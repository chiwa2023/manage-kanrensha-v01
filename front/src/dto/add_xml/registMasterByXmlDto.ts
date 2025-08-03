export default interface RegistMasterByXmlInterface {

}

export default class RegistMasterByXmlDto implements RegistMasterByXmlInterface {

    /** 編集対象該否 */
    isAffected: boolean;

    /** 編集対象該否 */
    isDisabled: boolean;

    /** 判定理由 */
    judgeReason: string;

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 備考 */
    bikou: string;

    /** 名称 */
    partnerName: string;

    /** 全住所 */
    allAddress: string;

    /** 団体代表者 */
    orgDelegate: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 法人番号 */
    houjinNo: string;

    /** 団体区分 */
    dantaiKbn: string;

    /** 様式区分 */
    youshikiKbn: number;

    /** 様式枝区分 */
    youshikiEdaKbn: number;

    /** 複写元名称 */
    inputSrcName: string;

    /** 複写元住所 */
    inputSrcAddress: string;

    /** 複写元認識キー */
    inputSrcKey: string;

    constructor() {
        const INIT_BOOLEAN: boolean = false;
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;

        this.isAffected = INIT_BOOLEAN;
        this.isDisabled = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
        this.kanrenshaKbn = INIT_NUMBER;
        this.bikou = INIT_STRING;
        this.partnerName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.orgDelegate = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
        this.youshikiKbn = INIT_NUMBER;
        this.youshikiEdaKbn = INIT_NUMBER;
        this.inputSrcName = INIT_STRING;
        this.inputSrcAddress = INIT_STRING;
        this.inputSrcKey = INIT_STRING;
    }
}