package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;

/**
 * 履歴差分ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpHistorySabunService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpHistorySabunCorpService forceDumpHistorySabunCorpService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpHistorySabunPersonService forceDumpHistorySabunPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpHistorySabunPoliOrgService forceDumpHistorySabunPoliOrgService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件Dto
     */
    @Async
    public void practice(final ForceDumpCapsuleDto capsuleDto) {

        LocalDate startDate = capsuleDto.getDateStart();
        LocalDate endDate = capsuleDto.getDateEnd();

        if (capsuleDto.getIsExecuteCorp()) {
            forceDumpHistorySabunCorpService.practice(startDate, endDate);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpHistorySabunPersonService.practice(startDate, endDate);
        }
        if (capsuleDto.getIsExecutePoliOrg()) {
            forceDumpHistorySabunPoliOrgService.practice(startDate, endDate);
        }
    }

}
