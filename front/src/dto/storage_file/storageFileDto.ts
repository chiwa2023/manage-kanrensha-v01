export default interface StorageFileInterface {

}

export default class StorageFileDto implements StorageFileInterface {

    /** 保存ディレクトリ */
    savedDir: string;

    /** ファイル名 */
    fileName: string;

    constructor() {
        const INIT_STRING: string = "";

        this.savedDir = INIT_STRING;
        this.fileName = INIT_STRING;
    }

}