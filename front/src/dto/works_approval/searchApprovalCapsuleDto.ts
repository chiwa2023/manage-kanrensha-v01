import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchWorksApprovalCapsuleDtoInterface {

}

export default class SearchWorksApprovalCapsuleDto extends PagingFrameworkInterface
    implements SearchWorksApprovalCapsuleDtoInterface {

    /** 検索開始日 */
    startDate: Date;

    /** 検索開始日テキスト */
    startDateText: string;

    /** 検索終了日 */
    endDate: Date;

    /** 検索終了日テキスト */
    endDateText: string;

    /** タスク検索語 */
    isExcludeFinishedTask: boolean;

    constructor() {
        super();
        
        this.startDate = new Date(2025, 1, 1);
        this.startDate.setMonth(this.startDate.getMonth() - 1); // TODO util化
        this.endDate = new Date();
        this.startDateText = this.startDate.toLocaleDateString('sv-SE');
        this.endDateText = this.endDate.toLocaleDateString('sv-SE');
        this.isExcludeFinishedTask = true; // 初期値は承認が必要なデータのみ読み込み
    }
}