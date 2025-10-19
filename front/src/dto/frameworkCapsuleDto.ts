import type UserPersonLeastInterface from "./user/userPersonLeastDto";
import UserPersonLeastDto from "./user/userPersonLeastDto";

export default interface FrameworkCapsuleInterface {

}


export default class FrameworkCapsuleDto implements FrameworkCapsuleInterface {

    /** 最小限ユーザDto */
    userPersonLeastDto: UserPersonLeastInterface;
    
   constructor() {
        this.userPersonLeastDto = new UserPersonLeastDto();
    }
}
