package mitei.mitei.political.balancesheet.manage.kanrensha.controller.works_approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchApprovalAddressResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval.SearchApprovalAddressService;

/**
 * 作業内容承認検索Controller(初回のみ)
 */
@RestController
@RequestMapping("/works-approval")
public class SearchApprovalAddressControler {

    /** 関連者住所承認作業検索Service */
    @Autowired
    private SearchApprovalAddressService searchApprovalAddressService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search-address")
    public ResponseEntity<SearchApprovalAddressResultDto> practice(
            final @RequestBody SearchWorksApprovalCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpResponseStatus.OK.code())
                .body(searchApprovalAddressService.practice(capsuleDto));
    }

}
