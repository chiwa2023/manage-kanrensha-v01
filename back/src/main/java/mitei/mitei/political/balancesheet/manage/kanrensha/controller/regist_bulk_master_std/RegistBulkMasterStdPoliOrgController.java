package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_master_std;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPoliOrgResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_std.RegistBulkMasterStdPoliOrgService;

/**
 * ワークテーブルマスタ企業／団体標準編集Controller
 */
@RestController
@RequestMapping("/regist-bulk-master-std")
public class RegistBulkMasterStdPoliOrgController {

    /** ワークテーブルマスタ企業／団体標準編集Service */
    @Autowired
    private RegistBulkMasterStdPoliOrgService registBulkMasterStdPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-poli-org")
    public ResponseEntity<UpdateWkTblStdPoliOrgResultDto> practice(
            final @RequestBody UpdateWkTblStdPoliOrgCapsuleDto capsuleDto) {

        WkTblMasterPoliOrgEntity entity = registBulkMasterStdPoliOrgService.practice(capsuleDto);
        Integer newId = entity.getWkTblMasterPoliOrgId();

        UpdateWkTblStdPoliOrgResultDto resultDto = new UpdateWkTblStdPoliOrgResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NOT_FOUND.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblMasterPoliOrgEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
