package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_by_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.SearchWkTblAddByXmlPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml.SearchAddByXmlService;

/**
 * XMLからマスタ最小登録ワークテーブル検索Controller
 */
@RestController
@RequestMapping("/regist-by-xml")
public class SearchAddByXmlController {

    /** XMLからマスタ最小登録ワークテーブル検索Service */
    @Autowired
    private SearchAddByXmlService searchAddByXmlService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/search")
    public ResponseEntity<SearchWkTblAddByXmlPagingResultDto> practice(
            final @RequestBody SearchWkTbPagingCapsuleDto capsuleDto) {

        return ResponseEntity.ok(searchAddByXmlService.practice(capsuleDto));
    }
}
