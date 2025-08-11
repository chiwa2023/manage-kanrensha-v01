package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_by_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml.RetryBatchAddByXmlService;

/**
 * ワークテーブル編集後再試行XML最小マスタController
 */
@RestController
@RequestMapping("/regist-by-xml")
public class RetryBatchAddByXmlController {

    /** ワークテーブル編集後再試行XML最小マスタSerice */
    @Autowired
    private RetryBatchAddByXmlService retryBatchAddByXmlService;

    /**
     * 処理を行う
     */
    @PostMapping("/retry")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final @RequestBody UserPersonLeastDto userDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        retryBatchAddByXmlService.practice(userDto);

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
