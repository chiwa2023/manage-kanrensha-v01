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
import mitei.mitei.political.balancesheet.manage.kanrensha.service.user.EditUserManagerService;

/**
 * 新規ユーザ作成Controller
 */
@RestController
@RequestMapping("/add-user")
public class InsertUserManagerController {

    /** ユーザ管理者編集Service */
    @Autowired
    private EditUserManagerService editUserManagerService;

    /**
     * 処理を行う
     *
     * @return ログイン結果
     */
    @PostMapping("/manager")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(@RequestBody final FrameworkCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpStatus.OK).body(editUserManagerService.practice(capsuleDto));

    }

}
