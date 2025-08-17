import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";

export default interface RetryWktblBatchCapsuleInterface {

}


export default class RetryWktblBatchCapsuleDto implements RetryWktblBatchCapsuleInterface {

    /** 最小限ユーザDto */
    userDto: UserPersonLeastInterface;

    constructor() {
        this.userDto = new UserPersonLeastDto();
    }

}