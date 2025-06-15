package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.EditKanrenshaPersonService;

/**
 * 関連者個人追加Controller
 */
@RestController
@RequestMapping("/add-user")
public class InsertUserKanrenshaPersonController {

    /** 関連者個人編集Service */
    @Autowired
    private EditKanrenshaPersonService editKanrenshaPersonService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     * @return 処理結果Dto
     */
    @PostMapping("/partner-person")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final FrameworkCapsuleDto capsuleDto) {
        
        return ResponseEntity.status(HttpStatus.OK).body(editKanrenshaPersonService.practice(capsuleDto));

    }

}
