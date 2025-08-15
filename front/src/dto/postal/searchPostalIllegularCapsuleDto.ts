import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface SearchPostalIllegularCapsuleInterface extends PagingFrameworkInterface {

}


export default class SearchPostalIllegularCapsuleDto extends PagingFrameworkDto
    implements SearchPostalIllegularCapsuleInterface {
        
    constructor() {
        super();
    }
}