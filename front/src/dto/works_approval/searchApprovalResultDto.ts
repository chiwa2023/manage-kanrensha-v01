import type SearchWorksApprovalAddressResultDtoInterface from "./searchWorksApprovalAddressResultDto";
import type SearchWorksApprovalShokugyouResultDtoInterface from "./searchWorksApprovalShokugyouResultDto";
import SearchWorksApprovalAddressResultDto from "./searchWorksApprovalAddressResultDto";
import SearchWorksApprovalShokugyouResultDto from "./searchWorksApprovalShokugyouResultDto";

export default interface SearchApprovalResultDtoInterface {

}

export default class SearchApprovalResultDto implements SearchApprovalResultDtoInterface {

    /** 承認作業住所検索結果Dto */
    resultDtoAddress: SearchWorksApprovalAddressResultDtoInterface;

    /** 承認作業職業検索結果Dto */
    resultDtoShokugyou: SearchWorksApprovalShokugyouResultDtoInterface;

    constructor() {
        this.resultDtoAddress = new SearchWorksApprovalAddressResultDto();
        this.resultDtoShokugyou = new SearchWorksApprovalShokugyouResultDto();
    }
}