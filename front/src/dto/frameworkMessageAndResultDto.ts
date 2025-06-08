export default interface FrameworkMessageAndResultInterface {

}

export default class FrameworkMessageAndResultDto implements FrameworkMessageAndResultInterface {

    /** 出力メッセージ */
    message: string;

    /** 処理失敗フラグ */
    isFailure: boolean;

    constructor() {
        this.message = "";
        this.isFailure = false;
    }
}