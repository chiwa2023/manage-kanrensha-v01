package mitei.mitei.political.balancesheet.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.postal.SearchAddressBlockService;

/**
 * 住所番地までContrroller
 */
@RestController
@RequestMapping("/postal-search")
public class PostalCodeBlockController {

    /** 住所番地までまで検索Service */
    @Autowired
    private SearchAddressBlockService searchAddressBlockService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/block")
    public ResponseEntity<PostalCodeBlockResultDto> practice(final @RequestBody PostalCodeCapsuleDto capsuleDto) {

        return ResponseEntity
                .ok(searchAddressBlockService.practice(capsuleDto.getSelectedPostal(), capsuleDto.getIsGyouseikuData()));
    }
}
