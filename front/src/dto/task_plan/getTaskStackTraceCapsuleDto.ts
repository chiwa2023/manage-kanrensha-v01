import FrameworkCapsuleInterface from "../frameworkCapsuleDto";
import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";

export default interface GetTaskStackTraceCapsuleDtoInterface {

}


export default class GetTaskStackTraceCapsuleDto extends FrameworkCapsuleInterface
    implements GetTaskStackTraceCapsuleDtoInterface {

    /** ユーザ最小限Dto */
    userDto: UserPersonLeastInterface;

    /** タスク計画登録年 */
    taskYear: number;

    /** タスク計画Dto */
    taskPlanCode: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.userDto = new UserPersonLeastDto();
        this.taskPlanCode = INIT_NUMBER;
        this.taskYear = INIT_NUMBER;
    }
}