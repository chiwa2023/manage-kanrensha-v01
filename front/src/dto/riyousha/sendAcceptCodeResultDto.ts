import type RiyoushaInviteNewInterface from "../../entity/riyoushaInviteNewEntity";
import FrameworkMessageAndResultInterface from "../frameworkMessageAndResultDto";

export default interface SendAcceptCodeResultInterface {
}


export default class SendAcceptCodeResultDto extends FrameworkMessageAndResultInterface
    implements SendAcceptCodeResultInterface {

    /** 承諾コードリスト*/
    listAcceptCode: RiyoushaInviteNewInterface[];

    constructor() {
        super();
        this.listAcceptCode = [];
    }
}