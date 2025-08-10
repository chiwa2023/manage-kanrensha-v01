package mitei.mitei.political.balancesheet.manage.kanrensha.controller.z_force;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force.AsyncForceDumpMinMasterService;

/**
 * マスタ最小ダンプController
 */
@RestController
@RequestMapping("/dump-master-min")
public class ForceDumpMinMasterController {

    /** 非同期処理専用Service */
    @Autowired
    private AsyncForceDumpMinMasterService asyncForceDumpMinMasterService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ダンプ実行条件
     */
    @PostMapping("/execute")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final @RequestBody ForceDumpCapsuleDto capsuleDto) {

        // 処理が選択されていないときは即終了
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (!capsuleDto.getIsExecuteCorp() && !capsuleDto.getIsExecutePerson() && !capsuleDto.getIsExecutePoliOrg()) {

            resultDto.setMessage("実行するダンプ処理が選択されていません");
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        }

        // TODO タスク登録

        asyncForceDumpMinMasterService.practice(capsuleDto);

        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");
        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);

    }

}
