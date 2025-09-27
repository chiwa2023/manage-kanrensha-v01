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
 * riyousha_manager_name接続用Entity
 */
@Entity
@Table(name = "riyousha_manager_name")
public class RiyoushaManagerNameEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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
    @Column(name = "riyousha_manager_name_id")
    private Integer riyoushaManagerNameId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getRiyoushaManagerNameId() {
        return riyoushaManagerNameId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param riyoushaManagerNameId テーブルId
     */
    public void setRiyoushaManagerNameId(final Integer riyoushaManagerNameId) {
        this.riyoushaManagerNameId = riyoushaManagerNameId;
    }

    /** 利用者管理者Id */
    @Column(name = "riyousha_manager_id")
    private Integer riyoushaManagerId = INIT_Integer;

    /**
     * 利用者管理者Idを取得する
     *
     * @return 利用者管理者Id
     */
    public Integer getRiyoushaManagerId() {
        return riyoushaManagerId;
    }

    /**
     * 利用者管理者Idを設定する
     *
     * @param riyoushaManagerId 利用者管理者Id
     */
    public void setRiyoushaManagerId(final Integer riyoushaManagerId) {
        this.riyoushaManagerId = riyoushaManagerId;
    }

    /** 利用者管理者コード */
    @Column(name = "riyousha_manager_code")
    private Integer riyoushaManagerCode = INIT_Integer;

    /**
     * 利用者管理者コードを取得する
     *
     * @return 利用者管理者コード
     */
    public Integer getRiyoushaManagerCode() {
        return riyoushaManagerCode;
    }

    /**
     * 利用者管理者コードを設定する
     *
     * @param riyoushaManagerCode 利用者管理者コード
     */
    public void setRiyoushaManagerCode(final Integer riyoushaManagerCode) {
        this.riyoushaManagerCode = riyoushaManagerCode;
    }

    /** 利用者管理者名称 */
    @Column(name = "riyousha_manager_name")
    private String riyoushaManagerName = INIT_String;

    /**
     * 利用者管理者名称を取得する
     *
     * @return 利用者管理者名称
     */
    public String getRiyoushaManagerName() {
        return riyoushaManagerName;
    }

    /**
     * 利用者管理者名称を設定する
     *
     * @param riyoushaManagerName 利用者管理者名称
     */
    public void setRiyoushaManagerName(final String riyoushaManagerName) {
        this.riyoushaManagerName = riyoushaManagerName;
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

    /** 姓名の姓 */
    @Column(name = "last_name")
    private String lastName = INIT_String;

    /**
     * 姓名の姓を取得する
     *
     * @return 姓名の姓
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 姓名の姓を設定する
     *
     * @param lastName 姓名の姓
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /** 姓名の名 */
    @Column(name = "first_name")
    private String firstName = INIT_String;

    /**
     * 姓名の名を取得する
     *
     * @return 姓名の名
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 姓名の名を設定する
     *
     * @param firstName 姓名の名
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /** 姓名のミドルネーム */
    @Column(name = "middle_name")
    private String middleName = INIT_String;

    /**
     * 姓名のミドルネームを取得する
     *
     * @return 姓名のミドルネーム
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 姓名のミドルネームを設定する
     *
     * @param middleName 姓名のミドルネーム
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /** 姓名の姓かな */
    @Column(name = "last_name_kana")
    private String lastNameKana = INIT_String;

    /**
     * 姓名の姓かなを取得する
     *
     * @return 姓名の姓かな
     */
    public String getLastNameKana() {
        return lastNameKana;
    }

    /**
     * 姓名の姓かなを設定する
     *
     * @param lastNameKana 姓名の姓かな
     */
    public void setLastNameKana(final String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    /** 姓名の名かな */
    @Column(name = "first_name_kana")
    private String firstNameKana = INIT_String;

    /**
     * 姓名の名かなを取得する
     *
     * @return 姓名の名かな
     */
    public String getFirstNameKana() {
        return firstNameKana;
    }

    /**
     * 姓名の名かなを設定する
     *
     * @param firstNameKana 姓名の名かな
     */
    public void setFirstNameKana(final String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

    /** 姓名のミドルネームかな */
    @Column(name = "middle_name_kana")
    private String middleNameKana = INIT_String;

    /**
     * 姓名のミドルネームかなを取得する
     *
     * @return 姓名のミドルネームかな
     */
    public String getMiddleNameKana() {
        return middleNameKana;
    }

    /**
     * 姓名のミドルネームかなを設定する
     *
     * @param middleNameKana 姓名のミドルネームかな
     */
    public void setMiddleNameKana(final String middleNameKana) {
        this.middleNameKana = middleNameKana;
    }

    /** 組織名称 */
    @Column(name = "org_name")
    private String orgName = INIT_String;

    /**
     * 組織名称を取得する
     *
     * @return 組織名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 組織名称を設定する
     *
     * @param orgName 組織名称
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    /** 組織名称かな */
    @Column(name = "org_name_kana")
    private String orgNameKana = INIT_String;

    /**
     * 組織名称かなを取得する
     *
     * @return 組織名称かな
     */
    public String getOrgNameKana() {
        return orgNameKana;
    }

    /**
     * 組織名称かなを設定する
     *
     * @param orgNameKana 組織名称かな
     */
    public void setOrgNameKana(final String orgNameKana) {
        this.orgNameKana = orgNameKana;
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
