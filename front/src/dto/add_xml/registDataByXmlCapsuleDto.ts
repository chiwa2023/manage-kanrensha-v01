import type StorageFileInterface from "../storage_file/storageFileDto";
import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import StorageFileDto from "../storage_file/storageFileDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";

export default interface RegistDataByXmlCapsuleInterface {

}

export default class RegistDataByXmlCapsuleDto implements RegistDataByXmlCapsuleInterface {

    /** ユーザ最低限Dto */
    userPersonLeastDto: UserPersonLeastInterface;

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileInterface;

    /** 備考1項目解析該否 */
    isNotBiko: boolean;

    /** 名前住所2項目解析該否 */
    isNotNameAddress: boolean;

    constructor() {
        const INI_BOOLEAN: boolean = false;
        this.userPersonLeastDto = new UserPersonLeastDto();
        this.storageFileDto = new StorageFileDto();
        this.isNotBiko = INI_BOOLEAN;
        this.isNotNameAddress = INI_BOOLEAN;
    }

}