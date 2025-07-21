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
 * master_political_organization_property接続用Entity
 */
@Entity
@Table(name = "master_political_organization_property")
public class MasterPoliticalOrganizationPropertyEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948,7,29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** テーブルId */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_political_organization_property_id")
    private Integer masterPoliticalOrganizationPropertyId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getMasterPoliticalOrganizationPropertyId() {
        return masterPoliticalOrganizationPropertyId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param masterPoliticalOrganizationPropertyId テーブルId
     */
    public void setMasterPoliticalOrganizationPropertyId(final Integer masterPoliticalOrganizationPropertyId) {
        this.masterPoliticalOrganizationPropertyId = masterPoliticalOrganizationPropertyId;
    }

    /** 関連者政治団体Id */
    @Column(name = "master_political_organization_id")
    private Integer masterPoliticalOrganizationId = INIT_Integer;

    /**
     * 関連者政治団体Idを取得する
     *
     * @return 関連者政治団体Id
     */
    public Integer getMasterPoliticalOrganizationId() {
        return masterPoliticalOrganizationId;
    }

    /**
     * 関連者政治団体Idを設定する
     *
     * @param masterPoliticalOrganizationId 関連者政治団体Id
     */
    public void setMasterPoliticalOrganizationId(final Integer masterPoliticalOrganizationId) {
        this.masterPoliticalOrganizationId = masterPoliticalOrganizationId;
    }

    /** 関連者政治団体コード */
    @Column(name = "poli_org_kanrensha_code")
    private String poliOrgKanrenshaCode = INIT_String;

    /**
     * 関連者政治団体コードを取得する
     *
     * @return 関連者政治団体コード
     */
    public String getPoliOrgKanrenshaCode() {
        return poliOrgKanrenshaCode;
    }

    /**
     * 関連者政治団体コードを設定する
     *
     * @param poliOrgKanrenshaCode 関連者政治団体コード
     */
    public void setPoliOrgKanrenshaCode(final String poliOrgKanrenshaCode) {
        this.poliOrgKanrenshaCode = poliOrgKanrenshaCode;
    }

    /** 関連者政治団体名称 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 関連者政治団体名称を取得する
     *
     * @return 関連者政治団体名称
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 関連者政治団体名称を設定する
     *
     * @param partnerName 関連者政治団体名称
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
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

    /** 会計責任者関連者個人コード */
    @Column(name = "account_mgr_code")
    private String accountMgrCode = INIT_String;

    /**
     * 会計責任者関連者個人コードを取得する
     *
     * @return 会計責任者関連者個人コード
     */
    public String getAccountMgrCode() {
        return accountMgrCode;
    }

    /**
     * 会計責任者関連者個人コードを設定する
     *
     * @param accountMgrCode 会計責任者関連者個人コード
     */
    public void setAccountMgrCode(final String accountMgrCode) {
        this.accountMgrCode = accountMgrCode;
    }

    /** 会計責任者関連者個人氏名 */
    @Column(name = "account_mgr_name")
    private String accountMgrName = INIT_String;

    /**
     * 会計責任者関連者個人氏名を取得する
     *
     * @return 会計責任者関連者個人氏名
     */
    public String getAccountMgrName() {
        return accountMgrName;
    }

    /**
     * 会計責任者関連者個人氏名を設定する
     *
     * @param accountMgrName 会計責任者関連者個人氏名
     */
    public void setAccountMgrName(final String accountMgrName) {
        this.accountMgrName = accountMgrName;
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
