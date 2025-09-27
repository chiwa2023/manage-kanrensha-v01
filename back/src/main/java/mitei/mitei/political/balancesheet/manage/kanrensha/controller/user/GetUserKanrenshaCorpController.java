package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaCorpResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetKanrenshaCorpDtoService;

/**
 * 関連者個人Dto取得Controller
 */
@RestController
@RequestMapping("/user-kanrensha")
public class GetUserKanrenshaCorpController {

    /** 関連者個人Dto取得Service */
    @Autowired
    private GetKanrenshaCorpDtoService getKanrenshaCorpDtoService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/get-corp")
    public ResponseEntity<GetKanrenshaCorpResultDto> practice(
            @RequestBody final GetKanrenshaCorpCapsuleDto capsuleDto) {

        GetKanrenshaCorpResultDto resultDto = new GetKanrenshaCorpResultDto();

        try {
            KanrenshaCorpDto kanrenshaCorpDto = getKanrenshaCorpDtoService
                    .practice(capsuleDto.getMasterCorporationEntity());
            resultDto.setKanrenshaCorpDto(kanrenshaCorpDto);
            
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (DataRetrievalFailureException e) {
            // 最新が2件以上
            resultDto.setIsFailure(true);
            resultDto.setMessage("データにエラーがあります。システム運営者にお問い合わせください");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
