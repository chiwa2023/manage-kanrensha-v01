import FrameworkResultDto from "../frameworkResultDto";
import type StorageFileInterface from "./storageFileDto";
import StorageFileDto from "./storageFileDto";

export default interface LookAheadCsvResultInterface {

}


export default class LookAheadCsvResultDto extends FrameworkResultDto implements LookAheadCsvResultInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileInterface;

    /** 頭出しcsvデータ */
    tableData: string[][];

    constructor() {
        super();
        this.storageFileDto = new StorageFileDto();
        this.tableData = [[]];
    }
}