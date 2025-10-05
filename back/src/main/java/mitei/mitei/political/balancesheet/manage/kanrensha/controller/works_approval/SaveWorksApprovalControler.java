package mitei.mitei.political.balancesheet.manage.kanrensha.controller.works_approval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SaveWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval.SaveApprovalAddressService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval.SaveApprovalShokugyouService;

/**
 * 作業承認保存Controller
 */
@RestController
@RequestMapping("/works-approval")
public class SaveWorksApprovalControler {

    /** 職業承認作業保存Service */
    @Autowired
    private SaveApprovalShokugyouService saveApprovalShokugyouService;

    /** 住所承認作業保存Service */
    @Autowired
    private SaveApprovalAddressService saveApprovalAddressService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理リストDto
     * @return 処理結果レスポンス
     */
    @PostMapping("/save")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SaveWorksApprovalCapsuleDto capsuleDto) {

        final String kaigyou = "\n\r";

        Integer shokugyouCount = saveApprovalShokugyouService.practice(capsuleDto.getListShokugyou(),
                capsuleDto.getUserPersonLeastDto());
        Integer addressCount = saveApprovalAddressService.practice(capsuleDto.getListAddress(),
                capsuleDto.getUserPersonLeastDto());

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();

        StringBuilder builder = new StringBuilder();
        builder.append("職業の承認を").append(shokugyouCount).append("件保存しました").append(kaigyou) //
                .append("住所の承認を").append(addressCount).append("件保存しました").append(kaigyou);

        resultDto.setMessage(builder.toString());

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

}
