import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";
import type UploadFileInterface from "./uploadFileDto";
import UploadFileDto from "./uploadFileDto";

export default interface UploadContentCapsuleInterface {

}

export default class UploadContentCapsuleDto implements UploadContentCapsuleInterface {

    /** ユーザ最低限Dto */
    userDto: UserPersonLeastInterface;

    /** アップロードファイルDto */
    uploadFileDto: UploadFileInterface;


    constructor() {

        this.userDto = new UserPersonLeastDto();
        this.uploadFileDto = new UploadFileDto();
    }

}