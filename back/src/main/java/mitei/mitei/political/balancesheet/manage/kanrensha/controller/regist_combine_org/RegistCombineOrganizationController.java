package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_combine_org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_combine_org.RegistCombineOrganizationService;

/**
 * ワークテーブル個人団体紐づけ編集Controller
 */
@RestController
@RequestMapping("/regist-combine")
public class RegistCombineOrganizationController {

    /** ワークテーブル個人団体紐づけ編集Service */
    @Autowired
    private RegistCombineOrganizationService registCombineOrganizationService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集EntityDto
     * @return 編集結果
     */
    @PostMapping("/update")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody UpdateWkTblCombineOrgCapsuleDto capsuleDto) {

        Integer newId = registCombineOrganizationService.practice(capsuleDto);

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (0 == newId) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("更新できませんでした");
            return ResponseEntity.status(HttpResponseStatus.NO_CONTENT.code()).body(resultDto);
        } else {
            resultDto.setMessage("正常に登録できました");
            return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
        }
    }

}
