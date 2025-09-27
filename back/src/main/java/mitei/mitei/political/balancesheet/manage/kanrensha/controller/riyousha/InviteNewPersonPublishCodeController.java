package mitei.mitei.political.balancesheet.manage.kanrensha.controller.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendInviteCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha.InviteNewPersonPublishCodeService;

/**
 * 利用者組織個人承認コード発行Controller
 */
@RestController
@RequestMapping("/user-riyousha")
public class InviteNewPersonPublishCodeController {

    /** 利用者組織個人承認コード発行Service */
    @Autowired
    private InviteNewPersonPublishCodeService inviteNewPersonPublishCodeService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 承認コード発行条件
     * @return 処理結果レスポンス
     */
    @PostMapping("/publish-accept-code")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SendInviteCodeCapsuleDto capsuleDto) {
        
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer insertId = inviteNewPersonPublishCodeService.practice(capsuleDto);
            if (0 < insertId) {
                // TODO mail送信しました、に実装後変更
                resultDto.setMessage("承認コードを発行しました。");
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }
            
            resultDto.setIsFailure(true);
            resultDto.setMessage("登録できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);

        } catch (EmptyResultDataAccessException exception) {
            // 紐づけ団体が存在しないまたはメアドが存在しない
            // TODO システム的な例外である紐づけ団体が存在しないのと、メアドコピペミスという人的要因の例外は分離する
            resultDto.setIsFailure(true);
            resultDto.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        } catch (Exception exception) { // NOPMD
            resultDto.setIsFailure(true);
            resultDto.setMessage("不明な例外が発生しました。");
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }
    }

}
