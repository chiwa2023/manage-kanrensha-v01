import type TaskPlanBaseEntityInterface from "../../entity/taskPlanBaseEntity";
import PagingFrameworkInterface from "../pagingFrameworkDto";

export default interface SearchTaskPlanResultDtoInterface {

}

export default class SearchTaskPlanResultDto extends PagingFrameworkInterface implements SearchTaskPlanResultDtoInterface {

    /** タスク計画リスト */
    listTaskPlan: TaskPlanBaseEntityInterface[];

    constructor() {
        super();
        this.listTaskPlan = [];
    }
}