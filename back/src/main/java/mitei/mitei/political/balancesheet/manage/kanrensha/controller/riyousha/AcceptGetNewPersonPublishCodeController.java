package mitei.mitei.political.balancesheet.manage.kanrensha.controller.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendAcceptCodeResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha.AcceptGetNewPersonPublishCodeService;

/**
 * 利用者組織個人承認コード取得Controller
 */
@RestController
@RequestMapping("/user-riyousha")
public class AcceptGetNewPersonPublishCodeController {

    /** */
    @Autowired
    private AcceptGetNewPersonPublishCodeService acceptGetNewPersonPublishCodeService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザ格納Dto
     * @return 利用者組織個人承認コードリスト格納Dto
     */
    @PostMapping("/get-accept-code-list")
    public ResponseEntity<SendAcceptCodeResultDto> practice(final @RequestBody FrameworkCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpResponseStatus.OK.code())
                .body(acceptGetNewPersonPublishCodeService.practice(capsuleDto.getUserPersonLeastDto()));
    }

}
