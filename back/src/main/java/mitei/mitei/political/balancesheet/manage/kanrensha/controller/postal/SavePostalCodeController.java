package mitei.mitei.political.balancesheet.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SavePostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.postal.SavePostalCodeService;

/**
 * 郵便番号更新Controller
 */
@RestController
@RequestMapping("/postal-code")
public class SavePostalCodeController {

    /** 郵便番号更新Service */
    @Autowired
    private SavePostalCodeService savePostalCodeService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 処理結果Dto
     */
    @PostMapping("/save")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final @RequestBody SavePostalCodeCapsuleDto capsuleDto) {

        // 処理結果によってステータスを変えない
        return ResponseEntity.ok(savePostalCodeService.practice(capsuleDto));
    }

}
