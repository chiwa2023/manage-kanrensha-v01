package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.InsertKanrenshaPersonService;

/**
 * 関連者個人追加Controller
 */
@RestController
@RequestMapping("/add-user")
public class InsertUserKanrenshaPersonController {

    /** 関連者個人編集Service */
    @Autowired
    private InsertKanrenshaPersonService insertKanrenshaPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/partner-person")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveKanrenshaPersonCapsuleDto capsuleDto) {

        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("個人仮設定");
        try {
            Integer newId = insertKanrenshaPersonService.practice(capsuleDto);
            if (0 != newId) {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            } else {
                resultDto.setIsFailure(true);
            }

        } catch (Exception exception) {
            resultDto.setIsFailure(true);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);

    }

}
