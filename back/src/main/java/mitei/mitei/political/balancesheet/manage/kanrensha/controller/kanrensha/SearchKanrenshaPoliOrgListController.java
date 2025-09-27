package mitei.mitei.political.balancesheet.manage.kanrensha.controller.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.NaturalTextSearchPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SearchKanrenshaPoliOrgResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.SearchKanrenshaPoliOrgListService;

/**
 * 関連者政治団体リスト検索Controller
 */
@RestController
@RequestMapping("/user-kanrensha")
public class SearchKanrenshaPoliOrgListController {


    /** 関連者個人リスト検索Service */
    @Autowired
    private SearchKanrenshaPoliOrgListService searchKanrenshaPoliOrgListService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ページング含む検索条件格納Dto
     * @return 検索結果
     */
    @PostMapping("/search-poli-org")
    public ResponseEntity<SearchKanrenshaPoliOrgResultDto> practice(
            final @RequestBody NaturalTextSearchPagingCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpResponseStatus.OK.code())
                .body(searchKanrenshaPoliOrgListService.practice(capsuleDto));
    }

}
