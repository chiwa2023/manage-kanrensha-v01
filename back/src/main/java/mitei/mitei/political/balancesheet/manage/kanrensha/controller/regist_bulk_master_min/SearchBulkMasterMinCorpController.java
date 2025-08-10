package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_master_min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.SearchWkTblMinCorpPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min.SearchBulkMasterMinCorpService;

/**
 * ワークテーブルマスタ企業／団体最小検索Controller
 */
@RestController
@RequestMapping("/regist-bulk-master-min")
public class SearchBulkMasterMinCorpController {

    /** ワークテーブルマスタ企業／団体最小検索Service */
    @Autowired
    private SearchBulkMasterMinCorpService searchBulkMasterMinCorpService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/search-corp")
    public ResponseEntity<SearchWkTblMinCorpPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {
        
        return ResponseEntity.ok(searchBulkMasterMinCorpService.practice(capsuleDto));
    }

}
