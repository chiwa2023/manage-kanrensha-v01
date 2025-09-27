import FrameworkCapsuleInterface from "../frameworkCapsuleDto";

export default interface SendInviteCodeCapsuleInterface {

}



export default class SendInviteCodeCapsuleDto extends FrameworkCapsuleInterface
    implements SendInviteCodeCapsuleInterface {

    /** 組織Id */
    orgId: number;

    /** 組織紐づき権限 */
    orgRole: string;

    /** 個人メールアドレス */
    personMail: string;

    constructor() {
        super();
        const INIT_STRING: string = "";

        this.orgId = 0;
        this.orgRole = INIT_STRING;
        this.personMail = INIT_STRING;
    }
}