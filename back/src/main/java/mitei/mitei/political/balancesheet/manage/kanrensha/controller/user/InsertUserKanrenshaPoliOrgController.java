package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.InsertKanrenshaPoliOrgService;

/**
 * 関連者政治団体追加Controller
 */
@RestController
@RequestMapping("/add-user")
public class InsertUserKanrenshaPoliOrgController {

    /** 関連者政治団体編集Service */
    @Autowired
    private InsertKanrenshaPoliOrgService insertKanrenshaPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/partner-poli-org")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveKanrenshaPoliOrgDto capsuleDto) {
        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer newId = insertKanrenshaPoliOrgService.practice(capsuleDto);
            if (0 == newId) {
                resultDto.setMessage("登録できませんでした");
                resultDto.setIsFailure(true);
            } else {
                resultDto.setMessage("登録できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD
            resultDto.setMessage("登録できませんでした");
            resultDto.setIsFailure(true);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);

    }

}
