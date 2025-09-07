package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaPersonResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;

/**
 * 関連者個人Dto取得Controller
 */
@RestController
@RequestMapping("/user-kanrensha")
public class GetUserKanrenshaPersonController {

    /** 関連者個人Dto取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/get-person")
    public ResponseEntity<GetKanrenshaPersonResultDto> practice(
            @RequestBody final GetKanrenshaPersonCapsuleDto capsuleDto) {

        GetKanrenshaPersonResultDto resultDto = new GetKanrenshaPersonResultDto();

        try {
            KanrenshaPersonDto kanrenshaPersonDto = getKanrenshaPersonDtoService
                    .practice(capsuleDto.getMasterPersonEntity());
            resultDto.setKanrenshaPersonDto(kanrenshaPersonDto);
            
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (DataRetrievalFailureException e) {
            // 最新が2件以上
            resultDto.setIsFailure(true);
            resultDto.setMessage("データにエラーがあります。システム運営者にお問い合わせください");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
