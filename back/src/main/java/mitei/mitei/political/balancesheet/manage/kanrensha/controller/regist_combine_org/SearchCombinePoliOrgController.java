package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_combine_org;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.SearchWkTblHistoryCorpPagingResultDto;

@RestController
@RequestMapping("/regist-combine")
public class SearchCombinePoliOrgController {

    @PostMapping("/search-poli-org")
    public ResponseEntity<SearchWkTblHistoryCorpPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {

        // 基本は最新のみ、影響させるデータのみ、未終了データのみ
        List<Boolean> searchHistoryList = this.createSearchConditionList(true, capsuleDto.getHasHistorry());
        List<Boolean> searchAffectedList = this.createSearchConditionList(true, capsuleDto.getHasAffectNot());
        List<Boolean> searchLFinishedList = this.createSearchConditionList(true, capsuleDto.getHasFinished());

        SearchWkTblHistoryCorpPagingResultDto resultDto = new SearchWkTblHistoryCorpPagingResultDto();
        return ResponseEntity.ok(resultDto);
    }

    private List<Boolean> createSearchConditionList(final Boolean baseFlg, final Boolean isSearch) {
        List<Boolean> list = new ArrayList<>();
        list.add(baseFlg);
        if (isSearch) {
            list.add(!baseFlg);
        }
        return list;
    }

}
