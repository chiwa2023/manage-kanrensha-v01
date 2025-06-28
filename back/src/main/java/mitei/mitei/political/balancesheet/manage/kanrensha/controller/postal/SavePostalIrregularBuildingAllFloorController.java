package mitei.mitei.political.balancesheet.manage.kanrensha.controller.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.postal.SavePostalIrregularBuildingAllFloorService;

/**
 * 郵便番号同一建物フロア住所更新Controller
 */
@RestController
@RequestMapping("/postal-irregular")
public class SavePostalIrregularBuildingAllFloorController {

    /** 郵便番号同一建物フロア住所更新 */
    @Autowired
    private SavePostalIrregularBuildingAllFloorService savePostalIrregularBuildingAllFloorService;
    
    
    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 処理結果Dto
     */
    @PostMapping("/save-building")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody SavePostalIrregularCapsuleDto capsuleDto) {
        
        // 処理結果によってステータスを変えない
        return ResponseEntity.ok(savePostalIrregularBuildingAllFloorService.practice(capsuleDto));
    }
        
}
