package mitei.mitei.political.balancesheet.manage.kanrensha.controller.csv;

import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.LookAheadCsvResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.file.LookAheadCsvFileService;

/**
 * アップロードされたCSVファイルを10行先読みController
 */
@RestController
@RequestMapping("/csv")
public class LookAheadCsvFileController {

    /** アップロードされたCSVファイルを10行先読みService */
    @Autowired
    private LookAheadCsvFileService lookAheadCsvFileService;

    /**
     * 処理を行う
     *
     * @param capsuleDto アップロードファイル内容Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/look-ahead")
    public ResponseEntity<LookAheadCsvResultDto> practice(final @RequestBody UploadContentCapsuleDto capsuleDto) {

        LocalDate now = LocalDate.now();

        try {
            LookAheadCsvResultDto resultDto = lookAheadCsvFileService.practice(now.getMonthValue(),
                    capsuleDto.getUploadFileDto());
            if (!Objects.isNull(resultDto)) {
                // 正常取得できたらそのまま返却
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }

        } catch (IOException exception) {

            LookAheadCsvResultDto resultDto = new LookAheadCsvResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に保存できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        } catch (NoSuchElementException exception) {

            LookAheadCsvResultDto resultDto = new LookAheadCsvResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("csv解析が正常にできませんでした");
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        }

        LookAheadCsvResultDto resultDto = new LookAheadCsvResultDto();
        resultDto.setIsFailure(true);
        resultDto.setMessage("なにがしかの例外が発生しました");
        return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);

    }

}
