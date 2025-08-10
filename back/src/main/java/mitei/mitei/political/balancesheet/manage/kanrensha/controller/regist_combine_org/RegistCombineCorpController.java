package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_combine_org;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.SearchWkTblHistoryCorpPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.UpdateWkTblHistoryCorpCapsuleDto;

@RestController
@RequestMapping("/regist-combine")
public class RegistCombineCorpController {

    @PostMapping("/update-corp")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody UpdateWkTblHistoryCorpCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        return ResponseEntity.ok(resultDto);
    }

}
