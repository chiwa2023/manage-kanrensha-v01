package mitei.mitei.political.balancesheet.manage.kanrensha.controller.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.PickupOrgSelectOptionResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha.PickupRiyoushaOrgOptionComradeService;

/**
 * APIユーザ組織選択肢を取得する
 */
@RestController
@RequestMapping("/user-riyousha")
public class PickupRiyoushaOrgOptionComradeController {

    /** 組織選択肢取得Service */
    @Autowired
    private PickupRiyoushaOrgOptionComradeService pickupRiyoushaOrgOptionComradeService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザ格納Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/org-comrade-option")
    public ResponseEntity<PickupOrgSelectOptionResultDto> practice(final @RequestBody FrameworkCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.status(HttpResponseStatus.OK.code())
                    .body(pickupRiyoushaOrgOptionComradeService.practice(capsuleDto.getUserPersonLeastDto()));

        } catch (EmptyResultDataAccessException | ConcurrencyFailureException exeception) {
            PickupOrgSelectOptionResultDto resultDto = new PickupOrgSelectOptionResultDto();
            resultDto.setMessage("データに不備があります。システム管理者にお問い合わせください。");
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }
    }

}
