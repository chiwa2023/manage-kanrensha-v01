package mitei.mitei.political.balancesheet.manage.kanrensha.controller.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SearchRiyoushaCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SearchRiyoushaResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha.SearcRiyoushaAllService;

/**
 * 利用者検索Controller
 */
@RestController
@RequestMapping("/user-riyousha")
public class SearcRiyoushaAllController {

    /** 利用者検索Service */
    @Autowired
    private SearcRiyoushaAllService searcRiyoushaAllService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/search")
    public ResponseEntity<SearchRiyoushaResultDto> practice(final @RequestBody SearchRiyoushaCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(searcRiyoushaAllService.practice(capsuleDto));
    }

}
