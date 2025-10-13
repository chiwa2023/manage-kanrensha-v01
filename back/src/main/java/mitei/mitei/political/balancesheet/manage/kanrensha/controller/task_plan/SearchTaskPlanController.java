package mitei.mitei.political.balancesheet.manage.kanrensha.controller.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.SaveStackTraceService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.SwitchYearSearchTaskPlanService;

/**
 * タスク計画検索Controller
 */
@RestController
@RequestMapping("/task-plan")
public class SearchTaskPlanController {

    /** 年切替タスク計画検索Service */
    @Autowired
    private SwitchYearSearchTaskPlanService switchYearSearchTaskPlanService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchTaskPlanResultDto> practice(final @RequestBody SearchTaskPlanCapsuleDto capsuleDto) {

        try {
            SearchTaskPlanResultDto resultDto = switchYearSearchTaskPlanService.practice(capsuleDto);

            final Integer zero = 0;
            if (zero.equals(resultDto.getAllCount())) {
                return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);

            } else {
                return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);

            }
        } catch (Exception exception) { // NOPMD すべての例外をCatchが目的
            saveStackTraceService.practice(exception, null, null);
            return ResponseEntity.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                    .body(new SearchTaskPlanResultDto());
        }
    }

}
