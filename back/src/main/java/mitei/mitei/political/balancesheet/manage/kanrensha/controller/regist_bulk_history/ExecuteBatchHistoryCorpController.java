package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_history;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.RegistDataByCsvFileCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.StorageFileDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.file.CopyTempToUseSavedFileService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history.ExecuteBatchHistoryCorpService;

/**
 * 企業団体履歴Csv登録Controller
 */
@RestController
@RequestMapping("/regist-bulk-history")
public class ExecuteBatchHistoryCorpController {

    /** 非同期処理登録専用Service */
    @Autowired
    private ExecuteBatchHistoryCorpService executeBatchHistoryCorpService;

    /** 仮ファイル本登録Service */
    @Autowired
    private CopyTempToUseSavedFileService copyTempToUseSavedFileService;

    /**
     * 処理を行う
     *
     * @param capsuleDto Csv登録バッチ起動条件Dto
     * @return 処理受付レスポンス
     */
    @PostMapping("/execute-corp")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody RegistDataByCsvFileCapsuleDto capsuleDto) {

        int year = LocalDate.now().getYear();
        // TODO ファイルタイプとタスク種類は決定次第修正する
        Short fileType = Short.valueOf("205");
        int taskConstants = 1;
        try {
            StorageFileDto fileDto = capsuleDto.getStorageFileDto();
            UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

            copyTempToUseSavedFileService.practice(year, fileDto, userDto, fileType, taskConstants);

            Path path = Paths.get(fileDto.getSavedDir(), fileDto.getFileName());
            executeBatchHistoryCorpService.practice(path.toString(), userDto);
        } catch (IOException exception) {

            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に登録できませんでした。");

            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        }

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
