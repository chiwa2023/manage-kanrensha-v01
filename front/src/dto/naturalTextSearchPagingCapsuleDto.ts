import PagingFrameworkInterface from "./pagingFrameworkDto";

export default interface NaturalTextSearchPagingCapsuleInterface {

}

export default class NaturalTextSearchPagingCapsuleDto extends PagingFrameworkInterface
    implements NaturalTextSearchPagingCapsuleInterface {

    /** 検索語自然検索 */
    searchNaturaloWords: string;

    constructor() {
        super();
        const INIT_STRING: string = "";

        this.searchNaturaloWords = INIT_STRING;

    }
}