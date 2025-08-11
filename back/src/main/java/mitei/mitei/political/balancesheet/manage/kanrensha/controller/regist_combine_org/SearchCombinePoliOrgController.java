package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_combine_org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_combine.SearchWkTblCombineOrgPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_combine_org.SearchCombineOrganizationService;

/**
 * 個人政治団体紐づけワークテーブル検索Controller
 */
@RestController
@RequestMapping("/regist-combine")
public class SearchCombinePoliOrgController {

    /** 個人団体紐づけ検索Service */
    @Autowired
    private SearchCombineOrganizationService searchCombineOrganizationService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/search-poli-org")
    public ResponseEntity<SearchWkTblCombineOrgPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {

        return ResponseEntity.ok(searchCombineOrganizationService.practice(KanrenshaKbnConstants.POLI_ORG, capsuleDto));
    }

}
