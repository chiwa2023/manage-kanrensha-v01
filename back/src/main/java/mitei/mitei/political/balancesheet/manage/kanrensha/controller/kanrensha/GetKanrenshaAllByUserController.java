package mitei.mitei.political.balancesheet.manage.kanrensha.controller.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaAllByUserResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetKanrenshaAllByUserService;

/**
 * ユーザから関連者を取得するController
 */
@RestController
@RequestMapping("/user-kanrensha")
public class GetKanrenshaAllByUserController {

    /** ユーザから関連者を取得Service */
    @Autowired
    private GetKanrenshaAllByUserService getKanrenshaAllByUserService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザ格納Dto
     * @return 関連者取得結果Dto
     */
    @PostMapping("/get-by-user")
    public ResponseEntity<GetKanrenshaAllByUserResultDto> practice(final @RequestBody FrameworkCapsuleDto capsuleDto) {


        GetKanrenshaAllByUserResultDto resultDto = new GetKanrenshaAllByUserResultDto();
        try {
            resultDto = getKanrenshaAllByUserService
                    .practice(capsuleDto.getUserPersonLeastDto());
            
            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);

            } else {
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }
        } catch (DataRetrievalFailureException exeception) {
            resultDto.setMessage("データに不備があります。システム管理者にお問い合わせください。");
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }
    }

}
