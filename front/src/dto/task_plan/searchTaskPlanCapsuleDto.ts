import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";

interface SearchTaskPlanCapsuleDtoInterface {
    /** ユーザ最小限Dto */
    userDto: UserPersonLeastInterface;

    /** 検索開始日 */
    startDate: Date;

    /** 検索開始日テキスト */
    startDateText: string;

    /** 検索終了日 */
    endDate: Date;

    /** 検索終了日テキスト */
    endDateText: string;

    /** タスク検索語 */
    searchTaskWord: string;

}

class SearchTaskPlanCapsuleDto implements SearchTaskPlanCapsuleDtoInterface {

    /** ユーザ最小限Dto */
    userDto: UserPersonLeastInterface;

    /** 検索開始日 */
    startDate: Date;

    /** 検索開始日テキスト */
    startDateText: string;

    /** 検索終了日 */
    endDate: Date;

    /** 検索終了日テキスト */
    endDateText: string;

    /** タスク検索語 */
    searchTaskWord: string;

    constructor() {

        this.userDto = new UserPersonLeastDto();
        this.startDate = new Date(2025, 1, 1);
        this.startDate.setMonth(this.startDate.getMonth() - 1); // TODO util化
        this.endDate = new Date();
        this.startDateText = this.startDate.toLocaleDateString('sv-SE');
        this.endDateText = this.endDate.toLocaleDateString('sv-SE');
        this.searchTaskWord = ""
    }
}

export { SearchTaskPlanCapsuleDto }
export type { SearchTaskPlanCapsuleDtoInterface } 