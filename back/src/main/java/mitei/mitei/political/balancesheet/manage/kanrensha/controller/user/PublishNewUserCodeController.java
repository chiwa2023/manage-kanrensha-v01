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
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.PublishNewUserCodeService;

/**
 * 新規ユーザ用仮コード発行Controller
 */
@RestController
@RequestMapping("/add-user")
public class PublishNewUserCodeController {

    /** 新規ユーザ用メール疎通確認コード発行Service */
    @Autowired
    private PublishNewUserCodeService publishNewUserCodeService;


    /** Logger */
    private final Logger log = LoggerFactory.getLogger(PublishNewUserCodeService.class);

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return 値が入力された新規ユーザDto
     */
    @PostMapping("/publish-code")
    public ResponseEntity<NewComerDto> practice(final @RequestBody NewComerDto newComerDto) {

        log.info("***************コード発行");
        log.info("***************有効期限" + newComerDto.getLimitDateTime());
        
        NewComerDto responseDto = publishNewUserCodeService.practice(newComerDto);
        
        // TODO コードをメール送信する
        
        return ResponseEntity.ok(responseDto);
    }

}
