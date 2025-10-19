package mitei.mitei.political.balancesheet.manage.kanrensha.controller.file;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DatabindException;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.LookAheadPublishXmlResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.file.LookAheadPublishXmlService;

/**
 * アップロードされたXMLファイルを文書種類先読みService
 */
@RestController
@RequestMapping("/xml")
public class LookAheadPublishXmlController {

    /** 文書種類先読みService */
    @Autowired
    private LookAheadPublishXmlService lookAheadPublishXmlService;

    /**
     * 処理を行う
     *
     * @param capsuleDto アップロードファイル内容Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/look-ahead")
    public ResponseEntity<LookAheadPublishXmlResultDto> practice(
            final @RequestBody UploadContentCapsuleDto capsuleDto) {

        LocalDate now = LocalDate.now();

        try {
            LookAheadPublishXmlResultDto resultDto = lookAheadPublishXmlService.practice(now.getMonthValue(),
                    capsuleDto.getUploadFileDto());
            if (!Objects.isNull(resultDto)) {
                // 正常取得できたらそのまま返却
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }

        } catch (DatabindException exception) {

            LookAheadPublishXmlResultDto resultDto = new LookAheadPublishXmlResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("形式が異なるXMLを読み込むことができませんでした");
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        } catch (IOException exception) {

            LookAheadPublishXmlResultDto resultDto = new LookAheadPublishXmlResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("ファイルが正常に保存できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        }

        LookAheadPublishXmlResultDto resultDto = new LookAheadPublishXmlResultDto();
        resultDto.setIsFailure(true);
        resultDto.setMessage("なにがしかの例外が発生しました");
        return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
    }
}
