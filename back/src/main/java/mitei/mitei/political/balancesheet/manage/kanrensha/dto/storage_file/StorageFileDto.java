package mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

/**
 * ストレージ保存ファイルDto
 */
public class StorageFileDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 保存ディレクトリ */
    private String savedDir = INIT_String;

    /** ファイル名 */
    private String fileName = INIT_String;

    /**
     * 保存ディレクトリを取得する
     *
     * @return 保存ディレクトリ
     */
    public String getSavedDir() {
        return savedDir;
    }

    /**
     * 保存ディレクトリを設定する
     *
     * @param savedDir 保存ディレクトリ
     */
    public void setSavedDir(final String savedDir) {
        this.savedDir = savedDir;
    }

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

}
