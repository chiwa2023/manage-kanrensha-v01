package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_combine_org;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_combine_org.ExecuteBatchCombinePoliOrgService;

/**
 * 個人団体紐づけ登録企業／団体Controller
 */
@RestController
@RequestMapping("/regist-combine")
public class ExecuteBatchCombinePoliOrgController {

    /** 個人団体紐づけ登録企業／団体Service */
    @Autowired
    private ExecuteBatchCombinePoliOrgService executeBatchCombinePoliOrgService;

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
        executeBatchCombinePoliOrgService.practice(Paths.get(fileDto.getSavedDir(), fileDto.getFileName()).toString(),
                capsuleDto.getUserPersonLeastDto());

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);

    }

}
