package mitei.mitei.political.balancesheet.manage.kanrensha.controller.address_registory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchLocalGovernmentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchLocalGovernmentResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory.SearchLocalGovService;

/**
 * 地方自治体検索Controller
 */
@RestController
@RequestMapping("/local-gov")
public class SearchLocalGovController {

    /** 地方自治体検索Service */
    @Autowired
    private SearchLocalGovService searchLocalGovService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件
     * @return 検索結果
     */
    @PostMapping("/search")
    public ResponseEntity<SearchLocalGovernmentResultDto> practice(
            final @RequestBody SearchLocalGovernmentCapsuleDto capsuleDto) {

        SearchLocalGovernmentResultDto resultDto = searchLocalGovService.practice(capsuleDto);

        if (resultDto.getIsFailure()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);

        } else {
            return ResponseEntity.ok(resultDto);
        }
    }
}
