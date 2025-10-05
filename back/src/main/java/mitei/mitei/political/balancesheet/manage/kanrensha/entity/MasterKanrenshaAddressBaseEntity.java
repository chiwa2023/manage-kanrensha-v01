package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 関連者住所共通Entity
 */
@Entity
public class MasterKanrenshaAddressBaseEntity implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Short) */
    private static final Short INIT_Short = 0;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_address_id")
    private Integer kanrenshaAddressId = INIT_Integer;

    /** 関連者個人Id */
    @Column(name = "kanrensha_master_id")
    private Integer kanrenshaMasterId = INIT_Integer;

    /** 関連者個人コード */
    @Column(name = "kanrensha_code")
    private String kanrenshaCode = INIT_String;

    /** 関連者区分 */
    @Column(name = "kanrensha_kbn")
    private Short kanrenshaKbn = INIT_Short;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaAddressId() {
        return kanrenshaAddressId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaAddressId テーブルId
     */
    public void setKanrenshaAddressId(final Integer kanrenshaAddressId) {
        this.kanrenshaAddressId = kanrenshaAddressId;
    }

    /**
     * 関連者個人Idを取得する
     *
     * @return 関連者個人Id
     */
    public Integer getKanrenshaMasterId() {
        return kanrenshaMasterId;
    }

    /**
     * 関連者個人Idを設定する
     *
     * @param kanrenshaMasterId 関連者個人Id
     */
    public void setKanrenshaMasterId(final Integer kanrenshaMasterId) {
        this.kanrenshaMasterId = kanrenshaMasterId;
    }

    /**
     * 関連者個人コードを取得する
     *
     * @return 関連者個人コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 関連者個人コードを設定する
     *
     * @param kanrenshaCode 関連者個人コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

    /**
     * 関連者区分を取得する
     *
     * @return 関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 関連者区分を設定する
     *
     * @param kanrenshaKbn 関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /** 関連者個人名称 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 関連者企業・団体名称を取得する
     *
     * @return 関連者企業・団体名称
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 関連者企業・団体名称を設定する
     *
     * @param partnerName 関連者企業・団体名称
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

}
