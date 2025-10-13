package mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

/**
 * ファイルバイナリBas64テキストDto
 */
public class OneFileBlobDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** ファイル名 */
    private String fileName = INIT_String;

    /** ファイル内容バイナリBase64 */
    private String fileContentBase64 = INIT_String;

    /**
     * ファイル名を取得する
     *
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ファイル名を設定する
     *
     * @param fileName ファイル名
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * ファイル内容バイナリBase64を取得する
     *
     * @return ファイル内容バイナリBase64
     */
    public String getFileContentBase64() {
        return fileContentBase64;
    }

    /**
     * ファイル内容バイナリBase64を設定する
     *
     * @param fileContentBase64 ファイル内容バイナリBase64
     */
    public void setFileContentBase64(final String fileContentBase64) {
        this.fileContentBase64 = fileContentBase64;
    }

}
