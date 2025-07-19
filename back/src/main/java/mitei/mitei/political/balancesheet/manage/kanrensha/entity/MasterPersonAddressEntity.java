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
 * master_person_address接続用Entity
 */
@Entity
@Table(name = "master_person_address")
public class MasterPersonAddressEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

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
    @Column(name = "master_person_address_id")
    private Integer masterPersonAddressId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getMasterPersonAddressId() {
        return masterPersonAddressId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param masterPersonAddressId テーブルId
     */
    public void setMasterPersonAddressId(final Integer masterPersonAddressId) {
        this.masterPersonAddressId = masterPersonAddressId;
    }

    /** 関連者個人Id */
    @Column(name = "master_person_id")
    private Integer masterPersonId = INIT_Integer;

    /**
     * 関連者個人Idを取得する
     *
     * @return 関連者個人Id
     */
    public Integer getMasterPersonId() {
        return masterPersonId;
    }

    /**
     * 関連者個人Idを設定する
     *
     * @param masterPersonId 関連者個人Id
     */
    public void setMasterPersonId(final Integer masterPersonId) {
        this.masterPersonId = masterPersonId;
    }

    /** 関連者個人コード */
    @Column(name = "person_kanrensha_code")
    private String personKanrenshaCode = INIT_String;

    /**
     * 関連者個人コードを取得する
     *
     * @return 関連者個人コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 関連者個人コードを設定する
     *
     * @param personKanrenshaCode 関連者個人コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

    /** 関連者個人名称 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 関連者個人名称を取得する
     *
     * @return 関連者個人名称
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 関連者個人名称を設定する
     *
     * @param partnerName 関連者個人名称
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

    /** 郵便番号1 */
    @Column(name = "postal1")
    private String postal1 = INIT_String;

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostal1() {
        return postal1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postal1 郵便番号1
     */
    public void setPostal1(final String postal1) {
        this.postal1 = postal1;
    }

    /** 郵便番号2 */
    @Column(name = "postal2")
    private String postal2 = INIT_String;

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostal2() {
        return postal2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postal2 郵便番号2
     */
    public void setPostal2(final String postal2) {
        this.postal2 = postal2;
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

    /** 住所郵便番号編集該否 */
    @Column(name = "is_postal_edit")
    private Boolean isPostalEdit = INIT_Boolean;

    /**
     * 住所郵便番号編集該否を取得する
     *
     * @return 住所郵便番号編集該否
     */
    public Boolean getIsPostalEdit() {
        return isPostalEdit;
    }

    /**
     * 住所郵便番号編集該否を設定する
     *
     * @param isPostalEdit 住所郵便番号編集該否
     */
    public void setIsPostalEdit(final Boolean isPostalEdit) {
        this.isPostalEdit = isPostalEdit;
    }

    /** 住所番地編集該否 */
    @Column(name = "is_block_edit")
    private Boolean isBlockEdit = INIT_Boolean;

    /**
     * 住所番地編集該否を取得する
     *
     * @return 住所番地編集該否
     */
    public Boolean getIsBlockEdit() {
        return isBlockEdit;
    }

    /**
     * 住所番地編集該否を設定する
     *
     * @param isBlockEdit 住所番地編集該否
     */
    public void setIsBlockEdit(final Boolean isBlockEdit) {
        this.isBlockEdit = isBlockEdit;
    }

    /** 住所建物編集該否 */
    @Column(name = "is_building_edit")
    private Boolean isBuildingEdit = INIT_Boolean;

    /**
     * 住所建物編集該否を取得する
     *
     * @return 住所建物編集該否
     */
    public Boolean getIsBuildingEdit() {
        return isBuildingEdit;
    }

    /**
     * 住所建物編集該否を設定する
     *
     * @param isBuildingEdit 住所建物編集該否
     */
    public void setIsBuildingEdit(final Boolean isBuildingEdit) {
        this.isBuildingEdit = isBuildingEdit;
    }

    /** 住所郵便番号承認該否 */
    @Column(name = "is_postal_accept")
    private Boolean isPostalAccept = INIT_Boolean;

    /**
     * 住所郵便番号承認該否を取得する
     *
     * @return 住所郵便番号承認該否
     */
    public Boolean getIsPostalAccept() {
        return isPostalAccept;
    }

    /**
     * 住所郵便番号承認該否を設定する
     *
     * @param isPostalAccept 住所郵便番号承認該否
     */
    public void setIsPostalAccept(final Boolean isPostalAccept) {
        this.isPostalAccept = isPostalAccept;
    }

    /** 住所番地承認該否 */
    @Column(name = "is_block_accept")
    private Boolean isBlockAccept = INIT_Boolean;

    /**
     * 住所番地承認該否を取得する
     *
     * @return 住所番地承認該否
     */
    public Boolean getIsBlockAccept() {
        return isBlockAccept;
    }

    /**
     * 住所番地承認該否を設定する
     *
     * @param isBlockAccept 住所番地承認該否
     */
    public void setIsBlockAccept(final Boolean isBlockAccept) {
        this.isBlockAccept = isBlockAccept;
    }

    /** 住所建物承認該否 */
    @Column(name = "is_building_accept")
    private Boolean isBuildingAccept = INIT_Boolean;

    /**
     * 住所建物承認該否を取得する
     *
     * @return 住所建物承認該否
     */
    public Boolean getIsBuildingAccept() {
        return isBuildingAccept;
    }

    /**
     * 住所建物承認該否を設定する
     *
     * @param isBuildingAccept 住所建物承認該否
     */
    public void setIsBuildingAccept(final Boolean isBuildingAccept) {
        this.isBuildingAccept = isBuildingAccept;
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
