package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history.RegistBulkHistoryPersonService;

/**
 * ワークテーブルマスタ企業／団体履歴編集Controller
 */
@RestController
@RequestMapping("/regist-bulk-history")
public class RegistBulkHistoryPersonController {

    /** ワークテーブルマスタ企業／団体履歴編集Service */
    @Autowired
    private RegistBulkHistoryPersonService registBulkHistoryPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-person")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody UpdateWkTblHistoryPersonCapsuleDto capsuleDto) {


        Integer newId = registBulkHistoryPersonService.practice(capsuleDto);

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NOT_FOUND.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
