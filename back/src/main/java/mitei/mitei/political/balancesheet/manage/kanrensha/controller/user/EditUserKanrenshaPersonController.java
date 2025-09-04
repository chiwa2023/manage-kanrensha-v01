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
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.EditKanrenshaPersonService;

/**
 * 関連者個人追加Controller
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
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final SaveKanrenshaPersonCapsuleDto capsuleDto) {
        
        return ResponseEntity.status(HttpStatus.OK).body(editKanrenshaPersonService.practice(capsuleDto));

    }

}
