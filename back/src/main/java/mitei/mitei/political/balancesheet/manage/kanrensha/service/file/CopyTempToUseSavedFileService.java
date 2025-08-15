package mitei.mitei.political.balancesheet.manage.kanrensha.service.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.TaskInfoConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.SwitchYearInsertSaveStorageService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;

/**
 * アップロード仮保存ファイルから正式保存記録を残すService
 */
@Service
public class CopyTempToUseSavedFileService {

    /** ファイル保存(年管理)Service */
    @Autowired
    private SwitchYearInsertSaveStorageService switchYearInsertSaveStorageService;

    /** タスク計画(年管理)Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    public void practice()throws IOException {

        // 仮ファイルから本ファイルに複写
        Path pathTempFull;

        Path pathSavedFull;
        
        //Files.copy(pathTempFull, pathSavedFull);

        //ファイルが保存出来たら保存場所を記録しスケジュールに登録
        
        // TODO 書証区分を決定次第指定する
        // switchYearInsertSaveStorageService.practice(year, userDto, fullPath,
        // Short.valueOf("205"));
        // すべきタスクとして保存する
        // switchYearInsertTaskPlanService.practice(year, userDto,
        // TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV);

    }

}
