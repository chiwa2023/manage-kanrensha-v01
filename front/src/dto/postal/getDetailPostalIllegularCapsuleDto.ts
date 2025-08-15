import type PagingFrameworkInterface from "../pagingFrameworkDto";
import PagingFrameworkDto from "../pagingFrameworkDto";

export default interface GetDetailPostalIllegularCapsuleInterface extends PagingFrameworkInterface {

}


export default class GetDetailPostalIllegularCapsuleDto extends PagingFrameworkDto
    implements GetDetailPostalIllegularCapsuleInterface {

    /** 地方自治体コード */
    lgCode: string;

    constructor() {
        super();
        this.lgCode = "";
    }
}