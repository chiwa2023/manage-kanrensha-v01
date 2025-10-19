package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_master_min;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.RegistDataByCsvFileCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.StorageFileDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min.ExecuteBatchMasterMinPoliOrgService;

/**
 * 政治団体体マスタ最小Csv登録Controller
 */
@RestController
@RequestMapping("/regist-bulk-master-min")
public class ExecuteBatchMasterMinPoliOrgController {

    /** 非同期処理登録専用Service */
    @Autowired
    private ExecuteBatchMasterMinPoliOrgService executeBatchMasterMinPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto Csv登録バッチ起動条件Dto
     * @return 処理受付レスポンス
     */
    @PostMapping("/execute-poli-org")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RegistDataByCsvFileCapsuleDto capsuleDto) {
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        StorageFileDto fileDto = capsuleDto.getStorageFileDto();
        executeBatchMasterMinPoliOrgService.practice(Paths.get(fileDto.getSavedDir(), fileDto.getFileName()).toString(),
                capsuleDto.getUserPersonLeastDto());

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
