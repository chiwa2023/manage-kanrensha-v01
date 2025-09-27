package mitei.mitei.political.balancesheet.manage.kanrensha.controller.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendAcceptCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha.AcceptSaveNewPersonService;

/**
 * 利用者個人組織紐づけ承認登録Controller
 */
@RestController
@RequestMapping("/user-riyousha")
public class AcceptSaveNewPersonController {

    /** 利用者個人組織紐づけ承認登録Service */
    @Autowired
    private AcceptSaveNewPersonService acceptSaveNewPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 承認コード入力Dto
     * @return 処理結果Dto
     */
    @PostMapping("/save-accept-code")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SendAcceptCodeCapsuleDto capsuleDto) {

        final Integer inputWrong = -1;
        final Integer notDataRecord = 0;

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer newId = acceptSaveNewPersonService.practice(capsuleDto);

            // 入力コード間違い
            if (inputWrong.equals(newId)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("入力されたコードが異なります");
                return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
            }

            if (notDataRecord.equals(newId)) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("データが登録できませんでした。");
                return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
            }
            if (notDataRecord < newId) {
                resultDto.setMessage("正常に登録できました");
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }

        } catch (Exception exception) { // NOPMD 
            resultDto.setIsFailure(true);
            resultDto.setMessage("データが登録できませんでした。");
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }

        return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
    }
}
