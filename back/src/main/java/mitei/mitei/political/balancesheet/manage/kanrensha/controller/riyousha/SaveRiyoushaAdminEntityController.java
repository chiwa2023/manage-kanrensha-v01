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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SaveRiyoushaAdminCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha.SaveRiyoushaAdminEntityService;

/**
 * 利用者管理者保存Controller
 */
@RestController
@RequestMapping("/user-riyousha")
public class SaveRiyoushaAdminEntityController {

    /** 利用者管理者保存Service */
    @Autowired
    private SaveRiyoushaAdminEntityService saveRiyoushaAdminEntityService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザ格納Dto
     * @return 汎用処理結果Dto
     */
    @PostMapping("/save-admin")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SaveRiyoushaAdminCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer newId = saveRiyoushaAdminEntityService.practice(capsuleDto);

            if (0 == newId) {
                resultDto.setMessage("登録できませんでした。");
                return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
            } else {
                resultDto.setMessage("正常に登録できました。");
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }
        } catch (EmptyResultDataAccessException exeception) {
            resultDto.setMessage("データに不備があります。システム管理者にお問い合わせください。");
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }
    }

}
