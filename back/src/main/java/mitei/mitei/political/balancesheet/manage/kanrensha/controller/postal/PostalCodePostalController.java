package mitei.mitei.political.balancesheet.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.service.postal.SearchAddressPostalService;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodePostalResultDto;

/**
 * 住所郵便番号までController
 */
@RestController
@RequestMapping("/postal-search")
public class PostalCodePostalController {

    /** 住所郵便番号まで検索Service */
    @Autowired
    private SearchAddressPostalService searchAddressPostalService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/postal")
    public ResponseEntity<PostalCodePostalResultDto> practice(final @RequestBody PostalCodeCapsuleDto capsuleDto) {

        return ResponseEntity
                .ok(searchAddressPostalService.practice(capsuleDto.getPostal1() + capsuleDto.getPostal2()));
    }

}
