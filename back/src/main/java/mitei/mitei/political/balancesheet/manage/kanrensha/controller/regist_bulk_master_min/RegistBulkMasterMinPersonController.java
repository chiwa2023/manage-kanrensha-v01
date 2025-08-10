package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_master_min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min.RegistBulkMasterMinPersonService;

/**
 * ワークテーブルマスタ企業／団体最小編集Controller
 */
@RestController
@RequestMapping("/regist-bulk-master-min")
public class RegistBulkMasterMinPersonController {

    /** ワークテーブルマスタ企業／団体最小編集Service */
    @Autowired
    private RegistBulkMasterMinPersonService registBulkMasterMinPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-person")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody UpdateWkTblMinPersonCapsuleDto capsuleDto) {

        Integer newId = registBulkMasterMinPersonService.practice(capsuleDto);

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
