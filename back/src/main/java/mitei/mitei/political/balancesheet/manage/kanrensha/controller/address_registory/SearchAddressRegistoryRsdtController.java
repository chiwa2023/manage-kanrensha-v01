package mitei.mitei.political.balancesheet.manage.kanrensha.controller.address_registory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchAddressRegistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchAddressRegistoryResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory.SearchAddressRegistoryRsdtService;

/**
 * アドレス・ベース・レジストリ住居検索Controller
 */
@RestController
@RequestMapping("/address-regi-rsdt")
public class SearchAddressRegistoryRsdtController {

    /** アドレス・ベース・レジストリ住居検索Service */
    @Autowired
    private SearchAddressRegistoryRsdtService searchAddressRegistoryRsdtService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/search")
    public ResponseEntity<SearchAddressRegistoryResultDto> practice(
            final @RequestBody SearchAddressRegistoryCapsuleDto capsuleDto) {

        SearchAddressRegistoryResultDto resultDto = searchAddressRegistoryRsdtService.practice(capsuleDto);

        if (resultDto.getIsFailure()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);

        } else {
            return ResponseEntity.ok(resultDto);
        }

    }

}
