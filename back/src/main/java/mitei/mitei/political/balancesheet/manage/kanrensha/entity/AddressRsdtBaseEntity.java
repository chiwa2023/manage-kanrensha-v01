package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 住所住居2タイプテーブルEntity(テーブル・Repository指定がない共用のEntity)
 */
@Entity
public class AddressRsdtBaseEntity implements Serializable{ // NOPMD
    
    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(LocalDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 28);

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_rsdt_id")
    private Integer addressRsdtId = INIT_Integer;

    /** 地方自治体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

    /** 郵便番号 */
    @Column(name = "postal_code")
    private String postalCode = INIT_String;

    /** 町字コード */
    @Column(name = "machiaza_id")
    private String machiazaId = INIT_String;

    /** 地番・住居コード */
    @Column(name = "parcel_rsdt_id")
    private String parcelRsdtId = INIT_String;

    /** 街区住所 */
    @Column(name = "address_block")
    private String addressBlock = INIT_String;

    /** 住所建物 */
    @Column(name = "address_building")
    private String addressBuilding = INIT_String;

    /** 適用開始日 */
    @Column(name = "effect_date")
    private LocalDate effectDate = INIT_LocalDate;

    /**
     * テーブルIdコードを取得する
     *
     * @return テーブルId
     */
    public Integer getAddressRsdtId() {
        return addressRsdtId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param addressRsdtId テーブルId
     */
    public void setAddressRsdtId(final Integer addressRsdtId) {
        this.addressRsdtId = addressRsdtId;
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

    /**
     * 地番・住居コードを取得する
     *
     * @return 地番・住居コード
     */
    public String getParcelRsdtId() {
        return parcelRsdtId;
    }

    /**
     * 地番・住居コードを設定する
     *
     * @param parcelRsdtId 地番・住居コード
     */
    public void setParcelRsdtId(final String parcelRsdtId) {
        this.parcelRsdtId = parcelRsdtId;
    }

    /**
     * 街区住所を取得する
     *
     * @return 街区住所
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 街区住所を設定する
     *
     * @param addressBlock 街区住所
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

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

    /**
     * 適用開始日を取得する
     *
     * @return 適用開始日
     */
    public LocalDate getEffectDate() {
        return effectDate;
    }

    /**
     * 適用開始日を設定する
     *
     * @param effectDate 適用開始日
     */
    public void setEffectDate(final LocalDate effectDate) {
        this.effectDate = effectDate;
    }
}
