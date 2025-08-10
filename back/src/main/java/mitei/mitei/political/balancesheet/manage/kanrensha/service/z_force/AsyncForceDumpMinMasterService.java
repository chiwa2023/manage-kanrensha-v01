package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;

/**
 * マスタ最小ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpMinMasterService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpMinMasterCorpService forceDumpMinMasterCorpService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpMinMasterPersonService forceDumpMinMasterPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpMinMasterPoliOrgService forceDumpMinMasterPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final ForceDumpCapsuleDto capsuleDto) {
        
        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteCorp()) {
            forceDumpMinMasterCorpService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpMinMasterPersonService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePoliOrg()) {
            forceDumpMinMasterPoliOrgService.practice(endDate);
        }
    }

}
