package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.NewComerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.CheckNewUserCodeService;

/**
 * 新規コード確認Controller
 */
@RestController
@RequestMapping("/add-user")
public class CheckNewUserCodeController {

    /** 新規コード確認Service */
    @Autowired
    private CheckNewUserCodeService checkNewUserCodeService;

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(CheckNewUserCodeController.class);

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return チェック結果
     */
    @PostMapping("/check-code")
    public ResponseEntity<NewComerDto> practice(final @RequestBody NewComerDto newComerDto) {
        
        log.info("----------------checkCode");
        
        return ResponseEntity.ok(checkNewUserCodeService.practice(newComerDto));
    }

}
