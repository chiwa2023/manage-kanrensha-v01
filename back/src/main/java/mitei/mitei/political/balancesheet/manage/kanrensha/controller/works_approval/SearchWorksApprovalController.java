package mitei.mitei.political.balancesheet.manage.kanrensha.controller.works_approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval.SearchApprovalAddressService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval.SearchApprovalShokugyouService;

/**
 * 未承認変更データ検索Controller
 */
@RestController
@RequestMapping("/works-approval")
public class SearchWorksApprovalController {

    /** 関連者住所承認作業検索Service */
    @Autowired
    private SearchApprovalAddressService searchApprovalAddressService;

    /** 関連者住所承認作業検索Service */
    @Autowired
    private SearchApprovalShokugyouService searchApprovalShokugyouService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search-both")
    public ResponseEntity<SearchWorksApprovalResultDto> practice(
            final @RequestBody SearchWorksApprovalCapsuleDto capsuleDto) {

        SearchWorksApprovalResultDto resultDto = new SearchWorksApprovalResultDto();
        resultDto.setResultDtoAddress(searchApprovalAddressService.practice(capsuleDto));
        resultDto.setResultDtoShokugyou(searchApprovalShokugyouService.practice(capsuleDto));

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
