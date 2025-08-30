package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * master_person接続用Entity
 */
public class MasterPersonDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /**
     * コンストラクタ
     */
    public MasterPersonDto() {

    }

    /**
     * コンストラクタ
     *
     * @param masterPersonId      個人マスタId
     * @param personKanrenshaCode 個人マスタコード
     * @param isLatest            最新該否
     * @param partnerName         個人姓名
     * @param allAddress          全住所
     * @param personShokugyou     個人職業
     * @param compareNameText     全件作比較テキスト
     * @param insertUserId        挿入ユーザId
     * @param insertUserCode      挿入ユーザコード
     * @param insertUserName      挿入ユーザ氏名
     * @param insertTimestamp     挿入日時
     * @param deleteUserId        削除ユーザId
     * @param deleteUserCode      削除ユーザコード
     * @param deleteUserName      削除ユーザ氏名
     * @param deleteTimestamp     削除日時
     */
    public MasterPersonDto(final Integer masterPersonId, final String personKanrenshaCode, final Boolean isLatest,
            final String partnerName, final String allAddress, final String personShokugyou,
            final String compareNameText, final Integer insertUserId, final Integer insertUserCode,
            final String insertUserName, final LocalDateTime insertTimestamp, final Integer deleteUserId,
            final Integer deleteUserCode, final String deleteUserName, final LocalDateTime deleteTimestamp) {
        super();
        this.masterPersonId = masterPersonId;
        this.personKanrenshaCode = personKanrenshaCode;
        this.isLatest = isLatest;
        this.partnerName = partnerName;
        this.allAddress = allAddress;
        this.personShokugyou = personShokugyou;
        this.compareNameText = compareNameText;
        this.insertUserId = insertUserId;
        this.insertUserCode = insertUserCode;
        this.insertUserName = insertUserName;
        this.insertTimestamp = insertTimestamp;
        this.deleteUserId = deleteUserId;
        this.deleteUserCode = deleteUserCode;
        this.deleteUserName = deleteUserName;
        this.deleteTimestamp = deleteTimestamp;
    }

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_person_id")
    private Integer masterPersonId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getMasterPersonId() {
        return masterPersonId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param masterPersonId テーブルId
     */
    public void setMasterPersonId(final Integer masterPersonId) {
        this.masterPersonId = masterPersonId;
    }

    /** 企業・団体関連者コード */
    @Column(name = "person_kanrensha_code")
    private String personKanrenshaCode = INIT_String;

    /**
     * 企業・団体関連者コードを取得する
     *
     * @return 企業・団体関連者コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 企業・団体関連者コードを設定する
     *
     * @param personKanrenshaCode 企業・団体関連者コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

    /** 最新該否 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_Boolean;

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 企業・団体名 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 企業・団体名を取得する
     *
     * @return 企業・団体名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 企業・団体名を設定する
     *
     * @param partnerName 企業・団体名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 企業・団体全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_String;

    /**
     * 企業・団体全住所を取得する
     *
     * @return 企業・団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 企業・団体全住所を設定する
     *
     * @param allAddress 企業・団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 個人職業 */
    @Column(name = "person_shokugyou")
    private String personShokugyou = INIT_String;

    /**
     * 個人職業を取得する
     *
     * @return 個人職業
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

    /**
     * 個人職業を設定する
     *
     * @param personShokugyou 個人職業
     */
    public void setPersonShokugyou(final String personShokugyou) {
        this.personShokugyou = personShokugyou;
    }

    /** 名称比較用 */
    @Column(name = "compare_name_text")
    private String compareNameText = INIT_String;

    /**
     * 名称比較用を取得する
     *
     * @return 名称比較用
     */
    public String getCompareNameText() {
        return compareNameText;
    }

    /**
     * 名称比較用を設定する
     *
     * @param compareNameText 名称比較用
     */
    public void setCompareNameText(final String compareNameText) {
        this.compareNameText = compareNameText;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_Integer;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
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
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
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
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
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
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
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
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
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
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
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
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
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
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

}
