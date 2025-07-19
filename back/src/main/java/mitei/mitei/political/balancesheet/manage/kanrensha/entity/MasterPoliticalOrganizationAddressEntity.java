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
 * master_political_organization_address接続用Entity
 */
@Entity
@Table(name = "master_political_organization_address")
public class MasterPoliticalOrganizationAddressEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

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
    @Column(name = "master_political_organization_address_id")
    private Integer masterPoliticalOrganizationAddressId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getMasterPoliticalOrganizationAddressId() {
        return masterPoliticalOrganizationAddressId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param masterPoliticalOrganizationAddressId テーブルId
     */
    public void setMasterPoliticalOrganizationAddressId(final Integer masterPoliticalOrganizationAddressId) {
        this.masterPoliticalOrganizationAddressId = masterPoliticalOrganizationAddressId;
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

    /** 関連者政治団体名 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 関連者政治団体名を取得する
     *
     * @return 関連者政治団体名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 関連者政治団体名を設定する
     *
     * @param partnerName 関連者政治団体名
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

    /** 住所郵便番号 */
    @Column(name = "address_postal")
    private String addressPostal = INIT_String;

    /**
     * 住所郵便番号を取得する
     *
     * @return 住所郵便番号
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所郵便番号を設定する
     *
     * @param addressPostal 住所郵便番号
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /** 住所番地 */
    @Column(name = "address_block")
    private String addressBlock = INIT_String;

    /**
     * 住所番地を取得する
     *
     * @return 住所番地
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地を設定する
     *
     * @param addressBlock 住所番地
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /** 住所建物 */
    @Column(name = "address_building")
    private String addressBuilding = INIT_String;

    /**
     * 住所建物を取得する
     *
     * @return 住所建物
     */
    public String getAddressBuilding() {
        return addressBuilding;
    }

    /**
     * 住所建物を設定する
     *
     * @param addressBuilding 住所建物
     */
    public void setAddressBuilding(final String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    /** 郵便番号 */
    @Column(name = "postal_code")
    private String postalCode = INIT_String;

    /**
     * 郵便番号を取得する
     *
     * @return 郵便番号
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 郵便番号を設定する
     *
     * @param postalCode 郵便番号
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /** 地方自治体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

    /**
     * 地方自治体コードを取得する
     *
     * @return 地方自治体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方自治体コードを設定する
     *
     * @param lgCode 地方自治体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /** 町字コード */
    @Column(name = "machiaza_id")
    private String machiazaId = INIT_String;

    /**
     * 町字コードを取得する
     *
     * @return 町字コード
     */
    public String getMachiazaId() {
        return machiazaId;
    }

    /**
     * 町字コードを設定する
     *
     * @param machiazaId 町字コード
     */
    public void setMachiazaId(final String machiazaId) {
        this.machiazaId = machiazaId;
    }

    /** 街区コード */
    @Column(name = "blk_id")
    private String blkId = INIT_String;

    /**
     * 街区コードを取得する
     *
     * @return 街区コード
     */
    public String getBlkId() {
        return blkId;
    }

    /**
     * 街区コードを設定する
     *
     * @param blkId 街区コード
     */
    public void setBlkId(final String blkId) {
        this.blkId = blkId;
    }

    /** 住居コード */
    @Column(name = "rsdt_id")
    private String rsdtId = INIT_String;

    /**
     * 住居コードを取得する
     *
     * @return 住居コード
     */
    public String getRsdtId() {
        return rsdtId;
    }

    /**
     * 住居コードを設定する
     *
     * @param rsdtId 住居コード
     */
    public void setRsdtId(final String rsdtId) {
        this.rsdtId = rsdtId;
    }

    /** 住居2コード */
    @Column(name = "rsdt2_id")
    private String rsdt2Id = INIT_String;

    /**
     * 住居2コードを取得する
     *
     * @return 住居2コード
     */
    public String getRsdt2Id() {
        return rsdt2Id;
    }

    /**
     * 住居2コードを設定する
     *
     * @param rsdt2Id 住居2コード
     */
    public void setRsdt2Id(final String rsdt2Id) {
        this.rsdt2Id = rsdt2Id;
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
