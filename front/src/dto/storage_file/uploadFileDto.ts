export default interface UploadFileInterface {

}


export default class UploadFileDto implements UploadFileInterface {

    /** ファイル名 */
    fileName: string;

    /** 文字コード */
    charset: string;

    /** ファイル内容(Base64) */
    fileContent: string;

    constructor() {
        const INIT_STRING: string = "";

        this.fileName = INIT_STRING;
        this.charset = INIT_STRING;
        this.fileContent = INIT_STRING;
    }
}