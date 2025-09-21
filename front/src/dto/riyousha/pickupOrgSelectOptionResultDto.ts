import type RiyoushaComradeInterface from "../../entity/riyoushaComradeEntity";
import RiyoushaComradeEntity from "../../entity/riyoushaComradeEntity";
import FrameworkMessageAndResultInterface from "../frameworkMessageAndResultDto";
import type SelectOptionNumberInterface from "../selectOptionNumberDto";

export default interface PickupOrgSelectOptionResultInterface {

}

export default class PickupOrgSelectOptionResultDto extends FrameworkMessageAndResultInterface
    implements PickupOrgSelectOptionResultInterface {

    /** 組織選択肢 */
    listOrgOptions: SelectOptionNumberInterface[];


    /** 呼び出し個人APIユーザEntity */
    riyoushaComradeEntity:RiyoushaComradeInterface;

    constructor() {
        super();
        this.listOrgOptions = [];
        this.riyoushaComradeEntity = new RiyoushaComradeEntity();
    }
}