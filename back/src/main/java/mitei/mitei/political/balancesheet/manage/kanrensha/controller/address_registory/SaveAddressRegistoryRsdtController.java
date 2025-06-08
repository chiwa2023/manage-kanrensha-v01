package mitei.mitei.political.balancesheet.manage.kanrensha.controller.address_registory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SaveAddressRegistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory.SaveAddressRegistoryRsdtService;

/**
 * アドレス・ベース・レジストリ住居更新Controller
 */
@RestController
@RequestMapping("/address-regi-rsdt")
public class SaveAddressRegistoryRsdtController {

    /** アドレス・ベース・レジストリ住居更新Service */
    @Autowired
    private SaveAddressRegistoryRsdtService saveAddressRegistoryRsdtService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集Entity格納Dto
     * @return 処理結果Dto
     */
    @PostMapping("/save")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final SaveAddressRegistoryCapsuleDto capsuleDto) {

        try {
            return ResponseEntity.ok(saveAddressRegistoryRsdtService.practice(capsuleDto));

        } catch (Exception exception) { // TODO 徐々にCatch範囲を細かくしていく

            // 保存できない場合は保存できないかったメッセージを挿入
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("保存できませんでした");

            return null;

        }

    }

}
