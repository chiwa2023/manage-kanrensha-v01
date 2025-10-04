import type TaskPlanBaseEntityInterface from "../../entity/taskPlanBaseEntity";
import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchTaskHistoryResultDtoInterface {

}

export default class SearchTaskHistoryResultDto extends PagingFrameworkInterface
    implements SearchTaskHistoryResultDtoInterface {

    /** タスク計画リスト */
    listTaskHistory: TaskPlanBaseEntityInterface[];

    constructor() {
        super();
        this.listTaskHistory = [];
    }
}