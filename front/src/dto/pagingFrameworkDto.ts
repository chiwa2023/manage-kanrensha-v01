export default interface PagingFrameworkInterface {

    /** 全件数 */
    allCount: number;

    /** ページ数 */
    pageNumber: number;

    /** 1ページ件数 */
    limit: number
}

export default class PagingFrameworkDto implements PagingFrameworkInterface{

    /** 全件数 */
    allCount: number;

    /** ページ数 */
    pageNumber: number;

    /** 1ページ件数 */
    limit: number

    constructor() {
        const INIT_NUMBER: number = 0;

        this.allCount = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.limit = INIT_NUMBER;
    }
}