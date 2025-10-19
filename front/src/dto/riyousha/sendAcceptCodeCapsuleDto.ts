import type RiyoushaInviteNewInterface from "../../entity/riyoushaInviteNewEntity";
import RiyoushaInviteNewEntity from "../../entity/riyoushaInviteNewEntity";
import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface SendAcceptCodeCapsuleInterface {

}


export default class SendAcceptCodeCapsuleDto extends FrameworkCapsuleInterface
    implements SendAcceptCodeCapsuleInterface {

    /** 利用者組織紐づけ承認コードEntity  */
    riyoushaInviteNewEntity:RiyoushaInviteNewInterface;

    /** 入力承認コード  */
    inputAcceptCode: string;

    constructor() {
        super();
        const INIT_STRING: string = "";

        this.riyoushaInviteNewEntity = new RiyoushaInviteNewEntity();
        this.inputAcceptCode = INIT_STRING;
    }
}