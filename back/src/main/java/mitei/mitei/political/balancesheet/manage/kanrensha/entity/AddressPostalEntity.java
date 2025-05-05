package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 郵便番号住所Entity
 */
@Entity
@Table(name = "address_postal")
public class AddressPostalEntity implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Integer) */
    private static final Boolean INIT_Boolean = false;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_postal_id")
    private Integer addressPostalId = INIT_Integer;

    /** 郵便番号1 */
    @Column(name = "postal1")
    private String postal1 = INIT_String;

    /** 郵便番号2 */
    @Column(name = "postal2")
    private String postal2 = INIT_String;

    /** 地方自治体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

    /** 原文書住所 */
    @Column(name = "address_org")
    private String addressOrg = INIT_String;

    /** 連結住所 */
    @Column(name = "address_name")
    private String addressName = INIT_String;

    /** 行政区検索データ */
    @Column(name = "is_gyoseiku_data")
    private Boolean isGyoseikuData = INIT_Boolean;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getAddressPostalId() {
        return addressPostalId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param addressPostalId テーブルId
     */
    public void setAddressPostalId(final Integer addressPostalId) {
        this.addressPostalId = addressPostalId;
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
     * 行政区検索データ該当非該当を取得する
     *
     * @return 行政区検索データ該当非該当
     */
    public Boolean getIsGyoseikuData() {
        return isGyoseikuData;
    }

    /**
     * 行政区検索データ該当非該当を設定する
     *
     * @param isGyoseikuData 行政区検索データ該当非該当
     */
    public void setIsGyoseikuData(final Boolean isGyoseikuData) {
        this.isGyoseikuData = isGyoseikuData;
    }

}
