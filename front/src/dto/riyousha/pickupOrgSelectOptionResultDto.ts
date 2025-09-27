import type RiyoushaComradeInterface from "../../entity/riyoushaComradeEntity";
import RiyoushaComradeEntity from "../../entity/riyoushaComradeEntity";
import FrameworkMessageAndResultInterface from "../frameworkMessageAndResultDto";
import type SelectOptionNumberInterface from "../selectOptionNumberDto";
import type RiyoushaManagerInterface from "../../entity/riyoushaManagerEntity";
import RiyoushaManagerEntity from "../../entity/riyoushaManagerEntity";

export default interface PickupOrgSelectOptionResultInterface {

}

export default class PickupOrgSelectOptionResultDto extends FrameworkMessageAndResultInterface
    implements PickupOrgSelectOptionResultInterface {

    /** 組織選択肢 */
    listOrgOptions: SelectOptionNumberInterface[];

    /** 呼び出し個人APIユーザEntity */
    riyoushaComradeEntity:RiyoushaComradeInterface;

    /** 呼び出し個人運営者Entity */
    riyoushaManagerEntity:RiyoushaManagerInterface;

    constructor() {
        super();
        this.listOrgOptions = [];
        this.riyoushaComradeEntity = new RiyoushaComradeEntity();
        this.riyoushaManagerEntity = new RiyoushaManagerEntity();
    }
}