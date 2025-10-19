package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants.MasterStd;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.z_force.CreateMasterCompressFilePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

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

        try {
            // 生成したファイルを圧縮
            compressZipPointedFileLogic
                    .practice(createMasterCompressFilePathLogic.practiceZipFile(MasterStd.MASTER_STD_ZIP),
                            createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER,
                                    MasterStd.MASTER_STD_CORP, MasterStd.MASTER_STD_PERSON,
                                    MasterStd.MASTER_STD_POLI_ORG));
        } catch (IOException exception) {
            // TODO 例外処理
            writeLogService.practiceError(exception);
        }

    }

}
