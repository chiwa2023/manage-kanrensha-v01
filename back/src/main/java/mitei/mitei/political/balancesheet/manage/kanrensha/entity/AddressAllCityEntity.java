package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 市区町村Entity
 */
@Entity
@Table(name = "address_all_city")
public class AddressAllCityEntity implements Serializable{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(LoclaDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 地方自治体テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_all_city_id")
    private Integer addressAllCityId = INIT_Integer;

    /** 地方自治体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

    /** 住所 */
    @Column(name = "address_name")
    private String addressName = INIT_String;

    /** 住所かな */
    @Column(name = "address_name_kana")
    private String addressNameKana = INIT_String;

    /** 影響日 */
    @Column(name = "effect_date")
    private LocalDate effectDate = INIT_LocalDate;

    /**
     * 地方自治体テーブルIdを取得する
     *
     * @return 地方自治体テーブルId
     */
    public Integer getAddressAllCityId() {
        return addressAllCityId;
    }

    /**
     * 地方自治体テーブルId
     *
     * @param addressAllCityId 地方自治体テーブルId
     */
    public void setAddressAllCityId(final Integer addressAllCityId) {
        this.addressAllCityId = addressAllCityId;
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
     * 住所を取得する
     *
     * @return 住所
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 住所を設定する
     *
     * @param addressName 住所
     */
    public void setAddressName(final String addressName) {
        this.addressName = addressName;
    }

    /**
     * 住所かなを取得する
     *
     * @return 住所かな
     */
    public String getAddressNameKana() {
        return addressNameKana;
    }

    /**
     * 住所かなを設定する
     *
     * @param addressNameKana 住所かな
     */
    public void setAddressNameKana(final String addressNameKana) {
        this.addressNameKana = addressNameKana;
    }

    /**
     * 適用日を取得する
     *
     * @return 適用日
     */
    public LocalDate getEffectDate() {
        return effectDate;
    }

    /**
     * 適用日を設定する
     *
     * @param effectDate 適用日
     */
    public void setEffectDate(final LocalDate effectDate) {
        this.effectDate = effectDate;
    }

}
