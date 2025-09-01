package mitei.mitei.political.balancesheet.manage.kanrensha.controller.z_trial;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * 機能疎通確認Controller
 */
@RestController
public class TrialAccessController {

    /**
     * 処理を行う
     *
     * @return 表示文字列
     */
    @GetMapping("/trial-access")
    public ResponseEntity<String> practice() {

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body("access success!!");
    }

}
