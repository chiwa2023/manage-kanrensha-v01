package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_by_xml;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.SearchWkTblHistoryCorpPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.SearchWkTblStdCorpPagingResultDto;

@RestController
@RequestMapping("/regist-by-xml")
public class RegistAddByXmlController {

    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SearchWkTblStdCorpPagingResultDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        return ResponseEntity.ok(resultDto);
    }

}
