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

        return ResponseEntity.status(HttpStatus.OK).body(insertKanrenshaPoliOrgService.practice(capsuleDto));

    }

}
