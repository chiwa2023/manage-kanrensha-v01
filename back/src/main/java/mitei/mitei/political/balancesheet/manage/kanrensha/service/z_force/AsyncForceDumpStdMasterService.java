package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;

/**
 * マスタ標準ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpStdMasterService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpStdMasterCorpService forceDumpStdMasterCorpService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpStdMasterPersonService forceDumpStdMasterPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpStdMasterPoliOrgService forceDumpStdMasterPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final ForceDumpCapsuleDto capsuleDto) {

        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteCorp()) {
            forceDumpStdMasterCorpService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpStdMasterPersonService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePoliOrg()) {
            forceDumpStdMasterPoliOrgService.practice(endDate);
        }
    }

}
