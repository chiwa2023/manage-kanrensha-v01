package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants.SabunMasterMin;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.z_force.CreateMasterCompressFilePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

/**
 * マスタ最小差分ダンプ非同期処理Service
 */
@Service
public class AsyncForceDumpMinMasterSabunService {

    /** ダンプ企業／団体履歴Service */
    @Autowired
    private ForceDumpMinMasterSabunCorpService forceDumpMinMasterSabunCorpService;

    /** ダンプ個人履歴Service */
    @Autowired
    private ForceDumpMinMasterSabunPersonService forceDumpMinMasterSabunPersonService;

    /** ダンプ政治団体履歴Service */
    @Autowired
    private ForceDumpMinMasterSabunPoliOrgService forceDumpMinMasterSabunPoliOrgService;

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
            forceDumpMinMasterSabunCorpService.practice(startDate, endDate);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpMinMasterSabunPersonService.practice(startDate, endDate);
        }
        if (capsuleDto.getIsExecutePoliOrg()) {
            forceDumpMinMasterSabunPoliOrgService.practice(startDate, endDate);
        }

        try {
            // 生成したファイルを圧縮
            compressZipPointedFileLogic.practice(
                    createMasterCompressFilePathLogic.practiceZipFile(SabunMasterMin.SABUN_MIN_ZIP),
                    createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER_SABUN,
                            SabunMasterMin.SABUN_MIN_CORP, SabunMasterMin.SABUN_MIN_PERSON,
                            SabunMasterMin.SABUN_MIN_POLI_ORG));

        } catch (IOException exception) {
            // TODO 例外処理
            writeLogService.practiceError(exception);
        }

    }

}
