package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.SearchWkTblHistoryCorpPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history.SearchBulkHistoryCorpService;

/**
 * ワークテーブルマスタ企業／団体履歴検索Controller
 */
@RestController
@RequestMapping("/regist-bulk-history")
public class SearchBulkHistoryCorpController {

    /** ワークテーブルマスタ企業／団体履歴検索Service */
    @Autowired
    private SearchBulkHistoryCorpService searchBulkHistoryCorpService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/search-corp")
    public ResponseEntity<SearchWkTblHistoryCorpPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {
        
        return ResponseEntity.ok(searchBulkHistoryCorpService.practice(capsuleDto));
    }

}
