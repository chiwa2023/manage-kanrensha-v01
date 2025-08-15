package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_master_std;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.RetryWktblBatchCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_std.RetryBatchMasterStdCorpService;

/**
 * 企業団体マスタ標準Csv登録Controller
 */
@RestController
@RequestMapping("/regist-bulk-master-std")
public class RetryBatchMasterStdCorpController {

    /** 非同期処理登録専用Service */
    @Autowired
    private RetryBatchMasterStdCorpService retryBatchMasterStdCorpService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集後再試行条件Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/retry-corp")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RetryWktblBatchCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        retryBatchMasterStdCorpService.practice(capsuleDto.getUserDto());

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
