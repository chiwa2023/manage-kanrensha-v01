import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";
import type StorageFileInterface from "./storageFileDto";
import StorageFileDto from "./storageFileDto";

export default interface RegistDataByCsvFileCapsuleInterface {

}

export default class RegistDataByCsvFileCapsuleDto implements RegistDataByCsvFileCapsuleInterface {

    /** ユーザ最低限Dto */
    userPersonLeastDto: UserPersonLeastInterface;

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileInterface;

    constructor() {

        this.userPersonLeastDto = new UserPersonLeastDto();
        this.storageFileDto = new StorageFileDto();

    }

}