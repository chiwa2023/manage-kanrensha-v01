package mitei.mitei.political.balancesheet.manage.kanrensha.service.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.OneFileBlobDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.StorageFileDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetStackTraceFolderLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetTempFilePathLogic;

/**
 * StackTrace抽出Service
 */
@Service
public class PickupStackTraceService {

    /** フォルダ圧縮Logic */
    @Autowired
    private CompressZipPointedFileLogic compressZipPointedFileLogic;

    /** StackTrace保存フォルダ取得Logic */
    @Autowired
    private GetStackTraceFolderLogic getStackTraceFolderLogic;

    /** 仮ファイルフォルダ取得Logic */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    /** ファイル保存フォルダ絶対パスLogic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** ハイフン */
    private static final String HYPHEN = "-";

    /** zip拡張子 */
    private static final String EXPANDS = ".zip";

    /**
     * タスク計画コードと発生年を条件にして取得処理を行う
     *
     * @param taskYear    タスク発生年
     * @param taskPlaCode タスク計画コード
     * @return base64形式Blob格納Dto
     */
    public OneFileBlobDto practiceByTaskCode(final Integer taskYear, final Integer taskPlaCode) {

        // 保存フォルダと圧縮リストを引き渡す
        Path pathFolder = getStackTraceFolderLogic.practice(taskYear, taskPlaCode, null);

        int length = pathFolder.getNameCount();
        StringBuilder builder = new StringBuilder();
        builder.append(pathFolder.getName(length - 3)) // SUPPRESS CHECKSTYLE MagicNumber
                .append(HYPHEN).append(pathFolder.getName(length - 2)).append(HYPHEN)
                .append(pathFolder.getName(length - 1)).append(EXPANDS);

        try {
            // フォルダが存在しないときは空Dtoを返却
            if (Files.exists(pathFolder)) {
                return this.practice(builder.toString(), Files.list(pathFolder).toList());
            } else {
                return new OneFileBlobDto();
            }
        } catch (IOException exception) {
            return new OneFileBlobDto();
        }
    }

    /**
     * 発生日時を条件にして取得処理を行う
     *
     * @param pointedDate 指定日
     * @return base64形式Blob格納Dto
     */
    public OneFileBlobDto practiceByDate(final LocalDate pointedDate) {

        // 保存フォルダと圧縮リストを引き渡す
        Path pathFolder = getStackTraceFolderLogic.practice(null, null, LocalDateTime.of(pointedDate, LocalTime.MIN));

        int length = pathFolder.getNameCount();

        StringBuilder builder = new StringBuilder();
        builder.append(pathFolder.getName(length - 2)).append(HYPHEN) //
                .append(pathFolder.getName(length - 1)).append(EXPANDS);
        try {
            // フォルダが存在しないときは空Dtoを返却
            if (Files.exists(pathFolder)) {
                return this.practice(builder.toString(), Files.list(pathFolder).toList());
            } else {
                return new OneFileBlobDto();
            }
        } catch (IOException exception) {
            return new OneFileBlobDto();
        }
    }

    private OneFileBlobDto practice(final String fileName, final List<Path> listFile) throws IOException {

        // リストが空の場合は空Dtoを返却
        if (listFile.isEmpty()) {
            return new OneFileBlobDto();
        }

        StorageFileDto tempFileDto = getTempFilePathLogic.practice(LocalDate.now().getMonthValue(), fileName);
        Path pathZip = Paths.get(getAbsolutePathLogic.getStorageFolder(), tempFileDto.getSavedDir(),
                tempFileDto.getFileName());

        // ファイル圧縮して成功したらDtoに格納して返す
        OneFileBlobDto blobDto = new OneFileBlobDto();
        if (compressZipPointedFileLogic.practice(pathZip, listFile)) {
            blobDto.setFileName(fileName);
            blobDto.setFileContentBase64(
                    new String(Base64.getUrlEncoder().encode(Files.readAllBytes(pathZip))));
            return blobDto;
        } else {
            // 取得できないときは空Dto
            return blobDto;
        }
    }

}
