package mitei.mitei.political.balancesheet.manage.kanrensha.controller.address_registory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.InsertAddressByComponentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory.InsertAddressByComponentService;

/**
 * 住所編集コンポーネントから追加だけを行うController
 */
@RestController
@RequestMapping("/address-regi-rsdt")
public class InsertAddressByComponentController {

    /** 同一コードが存在しない住所挿入Service */
    @Autowired
    private InsertAddressByComponentService insertAddressByComponentService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 保存住所Dto
     * @return 処理結果レスポンス
     */
    @PostMapping("/insert-component")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final InsertAddressByComponentCapsuleDto capsuleDto) {

        return ResponseEntity.status(HttpResponseStatus.OK.code())
                .body(insertAddressByComponentService.practice(capsuleDto));
    }

}
