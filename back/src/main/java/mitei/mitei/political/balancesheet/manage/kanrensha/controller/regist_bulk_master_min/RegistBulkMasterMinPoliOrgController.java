package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_master_min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPoliOrgResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min.RegistBulkMasterMinPoliOrgService;

/**
 * ワークテーブルマスタ企業／団体最小編集Controller
 */
@RestController
@RequestMapping("/regist-bulk-master-min")
public class RegistBulkMasterMinPoliOrgController {

    /** ワークテーブルマスタ企業／団体最小編集Service */
    @Autowired
    private RegistBulkMasterMinPoliOrgService registBulkMasterMinPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @PostMapping("/update-poli-org")
    public ResponseEntity<UpdateWkTblMinPoliOrgResultDto> practice(
            final @RequestBody UpdateWkTblMinPoliOrgCapsuleDto capsuleDto) {

        WkTblPartnerPoliOrgAddMinEntity entity = registBulkMasterMinPoliOrgService.practice(capsuleDto);
        Integer newId = entity.getWkTblPartnerPoliOrgAddMinId();

        UpdateWkTblMinPoliOrgResultDto resultDto = new UpdateWkTblMinPoliOrgResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NOT_FOUND.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            resultDto.setWkTblPartnerPoliOrgAddMinEntity(entity);
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
