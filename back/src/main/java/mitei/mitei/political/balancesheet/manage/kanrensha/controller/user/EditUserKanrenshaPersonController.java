package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.EditKanrenshaPersonService;

/**
 * 関連者個人編集Controller
 */
@RestController
@RequestMapping("/user-kanrensha")
public class EditUserKanrenshaPersonController {

    /** 関連者個人編集Service */
    @Autowired
    private EditKanrenshaPersonService editKanrenshaPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/edit-person")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final SaveKanrenshaPersonCapsuleDto capsuleDto) {
        
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            Integer newId = editKanrenshaPersonService.practice(capsuleDto);
            if (0 == newId) {
                resultDto.setMessage("前データと元データに変更がなかったようでした。");
                resultDto.setIsFailure(true);
            } else {
                resultDto.setMessage("登録できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (EmptyResultDataAccessException exception) {
            resultDto.setMessage("必要なデータが呼び出せませんでした。システム運営者に連絡してください。");
            resultDto.setIsFailure(true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);

        } catch (ConcurrencyFailureException exception) {
            resultDto.setMessage("他のユーザが修正したようです。大変お手数をおかけしますが変更された後のデータを確認して修正作業をしなおしてください");
            resultDto.setIsFailure(true);
        } catch (Exception exception) { // NOPMD
            resultDto.setMessage("例外が発生しました。システム運営者に連絡してください。");
            resultDto.setIsFailure(true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
    }

}
