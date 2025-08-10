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
public class AsyncForceDumpStdMasterSabunService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpStdMasterSabunCorpService forceDumpStdMasterSabunCorpService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpStdMasterSabunPersonService forceDumpStdMasterSabunPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpStdMasterSabunPoliOrgService forceDumpStdMasterSabunPoliOrgService;

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
            forceDumpStdMasterSabunCorpService.practice(startDate, endDate);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpStdMasterSabunPersonService.practice(startDate, endDate);
        }
        if (capsuleDto.getIsExecutePoliOrg()) {
            forceDumpStdMasterSabunPoliOrgService.practice(startDate, endDate);
        }
    }

}
