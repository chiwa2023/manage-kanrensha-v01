package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 郵便番号作業テーブル
 */
@Entity
@Table(name = "address_postal_works")
public class AddressPostalWorksEntity implements Serializable{ // NOPMD DataClass
    
    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_postal_works_id")
    private Integer addressPostalWorksId = INIT_Integer;

    /** 郵便番号不規則データId */
    @Column(name = "address_postal_irregular_id")
    private Integer addressPostalIrregularId = INIT_Integer;

    /** 郵便番号1 */
    @Column(name = "postal1")
    private String postal1 = INIT_String;

    /** 郵便番号2 */
    @Column(name = "postal2")
    private String postal2 = INIT_String;

    /** 地方公共団体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

    /** 原文書(郵便番号csv)住所 */
    @Column(name = "address_org")
    private String addressOrg = INIT_String;

    /** 住所名 */
    @Column(name = "address_name")
    private String addressName = INIT_String;

    /** 住所郵便番号まで */
    @Column(name = "address_postal")
    private String addressPostal = INIT_String;

    /** 郵便番号番地まで */
    @Column(name = "address_block")
    private String addressBlock = INIT_String;

    /** 正規テーブル複写処理完了 */
    @Column(name = "is_add_postal")
    private Boolean isAddPostal = INIT_Boolean;

    /** 住居テーブル修正完了 */
    @Column(name = "is_repair_rsdt")
    private Boolean isRepairRsdt = INIT_Boolean;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getAddressPostalIrregularId() {
        return addressPostalIrregularId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param addressPostalIrregularId テーブルId
     */
    public void setAddressPostalIrregularId(final Integer addressPostalIrregularId) {
        this.addressPostalIrregularId = addressPostalIrregularId;
    }

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

    /**
     * 原文書住所を取得する
     *
     * @return 原文書住所
     */
    public String getAddressOrg() {
        return addressOrg;
    }

    /**
     * 原文書住所を設定する
     *
     * @param addressOrg 原文書住所
     */
    public void setAddressOrg(final String addressOrg) {
        this.addressOrg = addressOrg;
    }

    /**
     * 住所名を取得する
     *
     * @return 住所名
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 住所名を設定する
     *
     * @param addressName 住所名
     */
    public void setAddressName(final String addressName) {
        this.addressName = addressName;
    }

    /**
     * 住所郵便番号までを取得する
     *
     * @return 住所郵便番号まで
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所郵便番号まで
     *
     * @param addressPostal 住所郵便番号まで
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /**
     * 住所番地までを取得する
     *
     * @return 住所番地まで
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地までを設定する
     *
     * @param addressBlock 住所番地まで
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /**
     * 正規テーブル複写処理完了を取得する
     *
     * @return 正規テーブル複写処理完了
     */
    public Boolean getIsAddPostal() {
        return isAddPostal;
    }

    /**
     * 正規テーブル複写処理完了を設定する
     *
     * @param isAddPostal 正規テーブル複写処理完了
     */
    public void setIsAddPostal(final Boolean isAddPostal) {
        this.isAddPostal = isAddPostal;
    }

    /**
     * 住居テーブル修正完了を取得する
     *
     * @return 住居テーブル修正完了
     */
    public Boolean getIsRepairRsdt() {
        return isRepairRsdt;
    }

    /**
     * 住居テーブル修正完了を設定する
     *
     * @param isRepairRsdt 住居テーブル修正完了
     */
    public void setIsRepairRsdt(final Boolean isRepairRsdt) {
        this.isRepairRsdt = isRepairRsdt;
    }

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getAddressPostalWorksId() {
        return addressPostalWorksId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param addressPostalWorksId テーブルId
     */
    public void setAddressPostalWorksId(final Integer addressPostalWorksId) {
        this.addressPostalWorksId = addressPostalWorksId;
    }
    
}
