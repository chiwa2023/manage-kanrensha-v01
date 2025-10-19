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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.GetRiyoushaAdminCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.GetRiyoushaAdminResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha.GetRiyoushaAdminDtoService;

/**
 * 利用者管理者取得Controller
 */
@RestController
@RequestMapping("/user-riyousha")
public class GetRiyoushaAdminDtoController {

    /** 利用者管理者取得Service */
    @Autowired
    private GetRiyoushaAdminDtoService getRiyoushaAdminDtoService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザ格納Dto
     * @return 利用者管理者取得結果Dto
     */
    @PostMapping("/get-admin")
    public ResponseEntity<GetRiyoushaAdminResultDto> practice(
            final @RequestBody GetRiyoushaAdminCapsuleDto capsuleDto) {

        GetRiyoushaAdminResultDto resultDto = new GetRiyoushaAdminResultDto();
        try {
            resultDto.setRiyoushaAdminDto(getRiyoushaAdminDtoService.practice(capsuleDto.getRiyoushaAdminEntity()));

            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
            } else {
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
            }
        } catch (EmptyResultDataAccessException | ConcurrencyFailureException exeception) {
            resultDto.setMessage("データに不備があります。システム管理者にお問い合わせください。");
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).body(resultDto);
        }
    }
}
