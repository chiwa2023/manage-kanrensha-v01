package mitei.mitei.political.balancesheet.manage.kanrensha.controller.task_plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.OneFileBlobDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.GetTaskStackTraceCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.PickupStackTraceService;

/**
 * タスク計画からStackTrace取得Controller
 */
@RestController
@RequestMapping("/stack-trace")
public class DownloadStackTraceByTaskCodeController {

    /** StackTrace取得Service */
    @Autowired
    private PickupStackTraceService pickupStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto タスク計画Dto
     * @return ダウンロードファイルレスポンス
     */
    @PostMapping("/get-by-code")
    public ResponseEntity<OneFileBlobDto> practice(final @RequestBody GetTaskStackTraceCapsuleDto capsuleDto) {

        final String BLANK = "";

        OneFileBlobDto blobDto = pickupStackTraceService.practiceByTaskCode(capsuleDto.getTaskYear(),
                capsuleDto.getTaskPlanCode());
        if (BLANK.equals(blobDto.getFileName())) {
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(blobDto);

        } else {
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(blobDto);
        }
    }

}
