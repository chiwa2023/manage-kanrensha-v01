package mitei.mitei.political.balancesheet.manage.kanrensha.service.file;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.TaskInfoConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetStoragePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.SaveFileLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.SwitchYearInsertSaveStorageService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * ファイルアップロードService
 */
@Service
public class FileUploadServcie {

    /** ファイル保存フォルダ取得Logic */
    @Autowired
    private GetStoragePathLogic getStoragePathLogic;

    /** 絶対パス変換Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** ファイル保存Logic */
    @Autowired
    private SaveFileLogic saveFileLogic;

    /** ファイル保存(年管理)Service */
    @Autowired
    private SwitchYearInsertSaveStorageService switchYearInsertSaveStorageService;

    /** タスク計画(年管理)Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /**
     * 処理を行う
     *
     * @param year    処理年
     * @param capsuleDto アップロードファイルDto
     * @throws IOException ファイル書き込み例外
     */
    @Transactional
    public void practice(final int year, final UploadContentCapsuleDto capsuleDto) throws IOException {

        UserPersonLeastDto userDto = capsuleDto.getUserDto();
        
        Path childPath = getStoragePathLogic.practice(userDto);
        
        Path fullPath = getAbsolutePathLogic.practice(childPath.toString(),capsuleDto.getUploadFileDto().getFileName());
        
        // ファイルを保存する
        saveFileLogic.practice(fullPath, capsuleDto.getUploadFileDto().getFileContent());

        // TODO 書証区分を決定次第指定する
        switchYearInsertSaveStorageService.practice(year, userDto, fullPath, Short.valueOf("205"));
        // すべきタスクとして保存する
        switchYearInsertTaskPlanService.practice(year, userDto, TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV);
    }

}
