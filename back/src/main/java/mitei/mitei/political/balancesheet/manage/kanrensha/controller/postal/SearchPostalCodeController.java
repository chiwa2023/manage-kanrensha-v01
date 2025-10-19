package mitei.mitei.political.balancesheet.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalCodeResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.postal.SearchPostalCodeService;

/**
 * 郵便番号検索Controller
 */
@RestController
@RequestMapping("/postal-code")
public class SearchPostalCodeController {

    /** 郵便番検索Service */
    @Autowired
    private SearchPostalCodeService searchPostalCodeService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 処理結果Dto
     */
    @PostMapping("/search")
    public ResponseEntity<SearchPostalCodeResultDto> practice(final @RequestBody SearchPostalCodeCapsuleDto capsuleDto) {

        SearchPostalCodeResultDto resultDto = searchPostalCodeService.practice(capsuleDto);

        if (resultDto.getIsFailure()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);

        } else {
            return ResponseEntity.ok(resultDto);
        }
    }
}
