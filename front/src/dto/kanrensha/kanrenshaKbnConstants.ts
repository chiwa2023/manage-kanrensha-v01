export default class KanrenshaKbnConstants {

    /** 0 無選択 */
    static readonly NO_SELECT: number = 0;
    static readonly NO_SELECT_TEXT: string = " ";

    /** 1 個人 */
    static readonly PERSON: number = 1;
    static readonly PERSON_TEXT: string = "個人";

    /** 2 企業／団体 */
    static readonly CORP: number = 2;
    static readonly CORP_TEXT: string = "企業／団体";

    /** 3 政治団体 */
    static readonly POLI_ORG: number = 3;
    static readonly POLI_ORG_TEXT: string = "政治団体";

    static getLabel(value: number): string {

        switch (value) {

            // 0 無選択  
            case this.NO_SELECT:
                return this.NO_SELECT_TEXT;

            // 1 個人
            case this.PERSON:
                return this.PERSON_TEXT;

            // 2 企業／団体 
            case this.CORP:
                return this.CORP_TEXT;

            // 3 政治団体
            case this.POLI_ORG:
                return this.POLI_ORG_TEXT;

            default:
                return "";
        }
    }

}