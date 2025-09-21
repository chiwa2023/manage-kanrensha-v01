export default interface SearchRiyoushaCapsuleInterface {

}


export default class SearchRiyoushaCapsuleDto implements SearchRiyoushaCapsuleInterface {

    /** APIユーザ検索 */
    isComradeSearch: boolean;

    /** 運営者検索 */
    isManagerSearch: boolean;

    /** 管理者検索 */
    isAdminSearch: boolean;

    constructor() {
        const INIT_BOOLEAN: boolean = false;

        this.isAdminSearch = INIT_BOOLEAN;
        this.isComradeSearch = INIT_BOOLEAN;
        this.isManagerSearch = INIT_BOOLEAN;
    }
}