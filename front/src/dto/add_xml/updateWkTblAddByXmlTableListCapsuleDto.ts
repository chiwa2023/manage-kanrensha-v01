import FrameworkCapsuleDto from "../frameworkCapsuleDto";
import type WkTblMasterAllByXmlInterface from "../../entity/wkTblMasterAllByXmlEntity";

export default interface UpdateWkTblAddByXmlTableListCapsuleInterface {

}

export default class UpdateWkTblAddByXmlTableListCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblAddByXmlTableListCapsuleInterface {

    /** 編集対象リスト */
    listWkTblByXml: WkTblMasterAllByXmlInterface[];

    constructor() {
        super();
        this.listWkTblByXml = [];
    }
}