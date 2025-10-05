package mitei.mitei.political.balancesheet.manage.kanrensha.controller.z_trial;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.SampleTableRepository;

/**
 * 機能疎通確認Controller
 */
@RestController
public class TrialAccessController {

    /** 絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** サンプルテーブルRepository */
    @Autowired
    private SampleTableRepository sampleTableRepository;

    /**
     * 処理を行う
     *
     * @return 表示文字列
     */
    @GetMapping("/trial-access")
    public ResponseEntity<String> practice() {

        final String BLANK = "";
        final String KAIGYOU = "<br>  ";
        // ルートを取得
        Path path = getAbsolutePathLogic.practice(BLANK, BLANK);

        StringBuilder builder = new StringBuilder("access success!!").append(KAIGYOU);
        try {

            builder.append("ファイルストレージのルート：").append(path.toString()).append(KAIGYOU);
            boolean isFolderExist = Files.exists(path);
            builder.append("ストレージの存在確認：").append(isFolderExist).append(KAIGYOU);

            if (!isFolderExist) {
                Path saved = Files.createDirectories(path);
                builder.append("作成確認：").append(Files.exists(saved)).append(KAIGYOU);
            }

            boolean isExistSampleTable = sampleTableRepository.findMyself().isEmpty();

            // DB疎通確認
            if (isExistSampleTable) {
                builder.append("sample_tableが存在しません").append(KAIGYOU);
            } else {
                builder.append("sample_tableが作成されています").append(KAIGYOU);
            }

        } catch (Exception exception) { // NOPMD AvoidCatchingGenericException controller-runtime
            StringBuilder builderException = new StringBuilder();
            for (StackTraceElement element : exception.getStackTrace()) {
                builderException.append(element.toString()).append(KAIGYOU);
            }
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(builderException.toString());
        }

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(builder.toString());
    }

}
