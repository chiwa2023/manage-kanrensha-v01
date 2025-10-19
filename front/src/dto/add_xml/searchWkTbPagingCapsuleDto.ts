import PagingFrameworkDto from "../pagingFrameworkDto";
import type UserPersonLeastInterface from "../user/userPersonLeastDto";
import UserPersonLeastDto from "../user/userPersonLeastDto";

export default interface SearchWkTblPagingCapsuleInterface {
}    


export default class SearchgPagingCapsuleDto extends PagingFrameworkDto implements SearchWkTblPagingCapsuleInterface {

        /** りれきデータ検索結果保持 */
        hasHistorry: boolean;
    
        /** 終了データ検索結果保持 */
        hasFinished: boolean;
    
        /** 無影響検索結果保持 */
        hasAffectNot: boolean;
    
        /** ユーザ最小限Dto */
        userLeast: UserPersonLeastInterface;
    
        constructor() {
    
            super();
            const INIT_BOOLEAN: boolean = false;
    
            this.hasFinished = INIT_BOOLEAN;
            this.hasAffectNot = INIT_BOOLEAN
            this.hasHistorry = INIT_BOOLEAN
            this.userLeast = new UserPersonLeastDto();
        }
    
}