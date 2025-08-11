package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants.SabunMasterStd;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.z_force.CreateMasterCompressFilePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

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

    /** 指定ファイル圧縮Logic */
    @Autowired
    private CompressZipPointedFileLogic compressZipPointedFileLogic;

    /** 圧縮ファイルPath取得Logic */
    @Autowired
    private CreateMasterCompressFilePathLogic createMasterCompressFilePathLogic;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

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

        try {
            // 生成したファイルを圧縮
            compressZipPointedFileLogic.practice(
                    createMasterCompressFilePathLogic.practiceZipFile(SabunMasterStd.SABUN_STD_ZIP),
                    createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER_SABUN,
                            SabunMasterStd.SABUN_STD_CORP, SabunMasterStd.SABUN_STD_PERSON,
                            SabunMasterStd.SABUN_STD_POLI_ORG));
        } catch (IOException exception) {
            // TODO 例外処理
            writeLogService.practiceError(exception);
        }

    }

}
