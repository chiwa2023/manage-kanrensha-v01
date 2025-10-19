import FrameworkResultDto from "../frameworkResultDto";
import type StorageFileInterface from "./storageFileDto";
import StorageFileDto from "./storageFileDto";

export default interface LookAheadPublishXmlResultInterface {

}


export default class LookAheadPublishXmlResultDto extends FrameworkResultDto implements LookAheadPublishXmlResultInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileInterface;

    /** 作成アプリ名 */
    app: string;

    /** 作成アプリバージョン */
    version: string;

    /** 団体名称 */
    dantaiName: string;

    /** 報告年 */
    houkokuNen: number;


    constructor() {
        super();
        const INIT_STRING: string = "";

        this.storageFileDto = new StorageFileDto();
        this.app = INIT_STRING;
        this.version = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.houkokuNen = 0;

    }

}