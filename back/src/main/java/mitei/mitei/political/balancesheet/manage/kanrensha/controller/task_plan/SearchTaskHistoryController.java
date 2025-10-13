package mitei.mitei.political.balancesheet.manage.kanrensha.controller.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.SearchTaskHistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.SearchTaskHistoryResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.SwitchYearSearchTaskHistoryService;

/**
 * タスク計画履歴取得Controller
 */
@RestController
@RequestMapping("/task-plan")
public class SearchTaskHistoryController {

    /** タスク計画履歴取得Service */
    @Autowired
    private SwitchYearSearchTaskHistoryService switchYearSearchTaskHistoryService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search-history")
    public ResponseEntity<SearchTaskHistoryResultDto> practice(
            final @RequestBody SearchTaskHistoryCapsuleDto capsuleDto) {

        SearchTaskHistoryResultDto resultDto = new SearchTaskHistoryResultDto();
        resultDto.setListTaskHistory(switchYearSearchTaskHistoryService.practice(capsuleDto));

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
