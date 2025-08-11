package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants.MasterMin;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.z_force.CreateMasterCompressFilePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

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
            forceDumpMinMasterCorpService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePerson()) {
            forceDumpMinMasterPersonService.practice(endDate);
        }
        if (capsuleDto.getIsExecutePoliOrg()) {
            forceDumpMinMasterPoliOrgService.practice(endDate);
        }

        try {
            // 生成したファイルを圧縮
            compressZipPointedFileLogic
                    .practice(createMasterCompressFilePathLogic.practiceZipFile(MasterMin.MASTER_MIN_ZIP),
                            createMasterCompressFilePathLogic.practiceFileList(MasterCsvFileNameConstants.FOLDER_MASTER,
                                    MasterMin.MASTER_MIN_CORP, MasterMin.MASTER_MIN_PERSON,
                                    MasterMin.MASTER_MIN_POLI_ORG));
        } catch (IOException exception) {
            // TODO 例外処理
            writeLogService.practiceError(exception);
        }
    }

}
