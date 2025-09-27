package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * riyousha_invite_new接続用Entity
 */
@Entity
@Table(name = "riyousha_invite_new")
public class RiyoushaInviteNewEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "riyousha_invite_new_id")
    private Integer riyoushaInviteNewId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getRiyoushaInviteNewId() {
        return riyoushaInviteNewId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param riyoushaInviteNewId テーブルId
     */
    public void setRiyoushaInviteNewId(final Integer riyoushaInviteNewId) {
        this.riyoushaInviteNewId = riyoushaInviteNewId;
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

    /** メールアドレス */
    @Column(name = "mail_address")
    private String mailAddress = INIT_String;

    /**
     * メールアドレスを取得する
     *
     * @return メールアドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレスを設定する
     *
     * @param mailAddress メールアドレス
     */
    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /** 発行コード */
    @Column(name = "regist_code")
    private String registCode = INIT_String;

    /**
     * 発行コードを取得する
     *
     * @return 発行コード
     */
    public String getRegistCode() {
        return registCode;
    }

    /**
     * 発行コードを設定する
     *
     * @param registCode 発行コード
     */
    public void setRegistCode(final String registCode) {
        this.registCode = registCode;
    }

    /** 紐づけ予定個人ユーザId */
    @Column(name = "person_user_id")
    private Integer personUserId = INIT_Integer;

    /**
     * 紐づけ予定個人ユーザIdを取得する
     *
     * @return 紐づけ予定個人ユーザId
     */
    public Integer getPersonUserId() {
        return personUserId;
    }

    /**
     * 紐づけ予定個人ユーザIdを設定する
     *
     * @param personUserId 紐づけ予定個人ユーザId
     */
    public void setPersonUserId(final Integer personUserId) {
        this.personUserId = personUserId;
    }

    /** 紐づけ予定個人ユーザId */
    @Column(name = "person_user_code")
    private Integer personUserCode = INIT_Integer;

    /**
     * 紐づけ予定個人ユーザIdを取得する
     *
     * @return 紐づけ予定個人ユーザId
     */
    public Integer getPersonUserCode() {
        return personUserCode;
    }

    /**
     * 紐づけ予定個人ユーザIdを設定する
     *
     * @param personUserCode 紐づけ予定個人ユーザId
     */
    public void setPersonUserCode(final Integer personUserCode) {
        this.personUserCode = personUserCode;
    }

    /** 紐づけ予定個人ユーザ姓名 */
    @Column(name = "person_user_name")
    private String personUserName = INIT_String;

    /**
     * 紐づけ予定個人ユーザ姓名を取得する
     *
     * @return 紐づけ予定個人ユーザ姓名
     */
    public String getPersonUserName() {
        return personUserName;
    }

    /**
     * 紐づけ予定個人ユーザ姓名を設定する
     *
     * @param personUserName 紐づけ予定個人ユーザ姓名
     */
    public void setPersonUserName(final String personUserName) {
        this.personUserName = personUserName;
    }

    /** 組織権限 */
    @Column(name = "dantai_role")
    private String dantaiRole = INIT_String;

    /**
     * 組織権限を取得する
     *
     * @return 組織権限
     */
    public String getDantaiRole() {
        return dantaiRole;
    }

    /**
     * 組織権限を設定する
     *
     * @param dantaiRole 組織権限
     */
    public void setDantaiRole(final String dantaiRole) {
        this.dantaiRole = dantaiRole;
    }

    /** 紐づけ予定利用者組織Id */
    @Column(name = "riyousha_dantai_id")
    private Integer riyoushaDantaiId = INIT_Integer;

    /**
     * 紐づけ予定利用者組織Idを取得する
     *
     * @return 紐づけ予定利用者組織Id
     */
    public Integer getRiyoushaDantaiId() {
        return riyoushaDantaiId;
    }

    /**
     * 紐づけ予定利用者組織Idを設定する
     *
     * @param riyoushaDantaiId 紐づけ予定利用者組織Id
     */
    public void setRiyoushaDantaiId(final Integer riyoushaDantaiId) {
        this.riyoushaDantaiId = riyoushaDantaiId;
    }

    /** 紐づけ予定利用者組織Id */
    @Column(name = "riyousha_dantai_code")
    private Integer riyoushaDantaiCode = INIT_Integer;

    /**
     * 紐づけ予定利用者組織Idを取得する
     *
     * @return 紐づけ予定利用者組織Id
     */
    public Integer getRiyoushaDantaiCode() {
        return riyoushaDantaiCode;
    }

    /**
     * 紐づけ予定利用者組織Idを設定する
     *
     * @param riyoushaDantaiCode 紐づけ予定利用者組織Id
     */
    public void setRiyoushaDantaiCode(final Integer riyoushaDantaiCode) {
        this.riyoushaDantaiCode = riyoushaDantaiCode;
    }

    /** 紐づけ予定利用者組織Id姓名 */
    @Column(name = "riyousha_dantai_name")
    private String riyoushaDantaiName = INIT_String;

    /**
     * 紐づけ予定利用者組織Id姓名を取得する
     *
     * @return 紐づけ予定利用者組織Id姓名
     */
    public String getRiyoushaDantaiName() {
        return riyoushaDantaiName;
    }

    /**
     * 紐づけ予定利用者組織Id姓名を設定する
     *
     * @param riyoushaDantaiName 紐づけ予定利用者組織Id姓名
     */
    public void setRiyoushaDantaiName(final String riyoushaDantaiName) {
        this.riyoushaDantaiName = riyoushaDantaiName;
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
