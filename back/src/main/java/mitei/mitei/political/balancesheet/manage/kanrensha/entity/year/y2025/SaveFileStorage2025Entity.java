package mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AllTabeDataHistoryInterface;



/**
 * save_file_storage_2025接続用Entity
 */
@Entity
@Table(name = "save_file_storage_2025")
public class SaveFileStorage2025Entity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Short) */
    private static final Short INIT_Short = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948,7,29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** ファイル保存ストレージId */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_file_storage_id")
    private Integer saveFileStorageId = INIT_Integer;

    /**
     * ファイル保存ストレージIdを取得する
     *
     * @return ファイル保存ストレージId
     */
    public Integer getSaveFileStorageId() {
        return saveFileStorageId;
    }

    /**
     * ファイル保存ストレージIdを設定する
     *
     * @param saveFileStorageId ファイル保存ストレージId
     */
    public void setSaveFileStorageId(final Integer saveFileStorageId) {
        this.saveFileStorageId = saveFileStorageId;
    }

    /** ファイル保存ストレージ同一識別コード */
    @Column(name = "save_file_storage_code")
    private Integer saveFileStorageCode = INIT_Integer;

    /**
     * ファイル保存ストレージ同一識別コードを取得する
     *
     * @return ファイル保存ストレージ同一識別コード
     */
    public Integer getSaveFileStorageCode() {
        return saveFileStorageCode;
    }

    /**
     * ファイル保存ストレージ同一識別コードを設定する
     *
     * @param saveFileStorageCode ファイル保存ストレージ同一識別コード
     */
    public void setSaveFileStorageCode(final Integer saveFileStorageCode) {
        this.saveFileStorageCode = saveFileStorageCode;
    }

    /** 最新該否 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_Boolean;

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    @Override
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    @Override
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 格納子ディレクトリ */
    @Column(name = "child_dir")
    private String childDir = INIT_String;

    /**
     * 格納子ディレクトリを取得する
     *
     * @return 格納子ディレクトリ
     */
    public String getChildDir() {
        return childDir;
    }

    /**
     * 格納子ディレクトリを設定する
     *
     * @param childDir 格納子ディレクトリ
     */
    public void setChildDir(final String childDir) {
        this.childDir = childDir;
    }

    /** ファイル名 */
    @Column(name = "file_name")
    private String fileName = INIT_String;

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

    /** 登録Unix時間 */
    @Column(name = "regist_time_text")
    private String registTimeText = INIT_String;

    /**
     * 登録Unix時間を取得する
     *
     * @return 登録Unix時間
     */
    public String getRegistTimeText() {
        return registTimeText;
    }

    /**
     * 登録Unix時間を設定する
     *
     * @param registTimeText 登録Unix時間
     */
    public void setRegistTimeText(final String registTimeText) {
        this.registTimeText = registTimeText;
    }

    /** 書証区分 */
    @Column(name = "shosho_kbn")
    private Short shoshoKbn = INIT_Short;

    /**
     * 書証区分を取得する
     *
     * @return 書証区分
     */
    public Short getShoshoKbn() {
        return shoshoKbn;
    }

    /**
     * 書証区分を設定する
     *
     * @param shoshoKbn 書証区分
     */
    public void setShoshoKbn(final Short shoshoKbn) {
        this.shoshoKbn = shoshoKbn;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_Integer;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    @Override
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    @Override
    public void setInsertUserId(final Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    /** 挿入ユーザコード */
    @Column(name = "insert_user_code")
    private Integer insertUserCode = INIT_Integer;

    /**
     * 挿入ユーザコードを取得する
     *
     * @return 挿入ユーザコード
     */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** 挿入ユーザ名称 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_String;

    /**
     * 挿入ユーザ名称を取得する
     *
     * @return 挿入ユーザ名称
     */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }

    /** 挿入日時 */
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp = INIT_Timestamp;

    /**
     * 挿入日時を取得する
     *
     * @return 挿入日時
     */
    @Override
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
    @Override
    public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** 無効ユーザId */
    @Column(name = "delete_user_id")
    private Integer deleteUserId = INIT_Integer;

    /**
     * 無効ユーザIdを取得する
     *
     * @return 無効ユーザId
     */
    @Override
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
    @Override
    public void setDeleteUserId(final Integer deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    /** 無効ユーザコード */
    @Column(name = "delete_user_code")
    private Integer deleteUserCode = INIT_Integer;

    /**
     * 無効ユーザコードを取得する
     *
     * @return 無効ユーザコード
     */
    @Override
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
    @Override
    public void setDeleteUserCode(final Integer deleteUserCode) {
        this.deleteUserCode = deleteUserCode;
    }

    /** 無効ユーザ名称 */
    @Column(name = "delete_user_name")
    private String deleteUserName = INIT_String;

    /**
     * 無効ユーザ名称を取得する
     *
     * @return 無効ユーザ名称
     */
    @Override
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
    @Override
    public void setDeleteUserName(final String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    /** 無効日時 */
    @Column(name = "delete_timestamp")
    private LocalDateTime deleteTimestamp = INIT_Timestamp;

    /**
     * 無効日時を取得する
     *
     * @return 無効日時
     */
    @Override
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    @Override
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

}
