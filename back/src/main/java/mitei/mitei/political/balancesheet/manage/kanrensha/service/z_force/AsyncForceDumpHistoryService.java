package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;

/**
 * 履歴ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpHistoryService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpHistoryCorpService forceDumpHistoryCorpService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpHistoryPersonService forceDumpHistoryPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpHistoryPoliOrgService forceDumpHistoryPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final ForceDumpCapsuleDto capsuleDto) {

        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteCorp()) {
            forceDumpHistoryCorpService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpHistoryPersonService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePoliOrg()) {
            forceDumpHistoryPoliOrgService.practice(endDate);
        }
    }

}
