package mitei.mitei.political.balancesheet.manage.kanrensha.controller.works_approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchApprovalShokugyouResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval.SearchApprovalShokugyouService;

/**
 * 作業承認職業検索Controller
 */
@RestController
@RequestMapping("/works-approval")
public class SearchApprovalShokugyouControler {

    /** 関連者住所承認作業検索Service */
    @Autowired
    private SearchApprovalShokugyouService searchApprovalShokugyouService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search-shokugyou")
    public ResponseEntity<SearchApprovalShokugyouResultDto> practice(
            final @RequestBody SearchWorksApprovalCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpResponseStatus.OK.code())
                .body(searchApprovalShokugyouService.practice(capsuleDto));
    }

}
