package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_by_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.RegistDataByXmlCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml.AnalysisUploadXmlWktblCommonByXmlService;

/**
 * アップロード済XMLファイル解析ワークテーブル複写Controller
 */
@RestController
@RequestMapping("/analysis-xml")
public class AnalysisUploadXmlWktblCommonByXmlController {

    /** アップロード済XMLファイル解析ワークテーブル複写Service */
    @Autowired
    private AnalysisUploadXmlWktblCommonByXmlService analysisUploadXmlWktblCommonByXmlService;

    /**
     * 処理を行う
     *
     * @return レスポンス
     */
    @PostMapping("/execute")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final RegistDataByXmlCapsuleDto capsuleDto) {
        
        analysisUploadXmlWktblCommonByXmlService.practice(capsuleDto);
        
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("処理を開始しました。完了までしばらくお待ちください。");
        
        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
