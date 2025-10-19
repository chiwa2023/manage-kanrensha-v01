package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaPoliOrgResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetKanrenshaPoliOrgDtoService;

/**
 * 関連者個人Dto取得Controller
 */
@RestController
@RequestMapping("/user-kanrensha")
public class GetUserKanrenshaPoliOrgController {

    /** 関連者個人Dto取得Service */
    @Autowired
    private GetKanrenshaPoliOrgDtoService getKanrenshaPoliOrgDtoService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/get-poli-org")
    public ResponseEntity<GetKanrenshaPoliOrgResultDto> practice(
            @RequestBody final GetKanrenshaPoliOrgCapsuleDto capsuleDto) {

        GetKanrenshaPoliOrgResultDto resultDto = new GetKanrenshaPoliOrgResultDto();
        
        try {
            KanrenshaPoliOrgDto kanrenshaPoliOrgDto = getKanrenshaPoliOrgDtoService
                    .practice(capsuleDto.getMasterPoliticalOrganizationEntity());
            resultDto.setKanrenshaPoliOrgDto(kanrenshaPoliOrgDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (DataRetrievalFailureException e) {
            // 最新が2件以上
            resultDto.setIsFailure(true);
            resultDto.setMessage("データにエラーがあります。システム運営者にお問い合わせください");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
