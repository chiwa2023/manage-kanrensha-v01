package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import java.io.Serializable;

/**
 * 郵便番号CSV読み込みDto
 */
public class PostalCodeCsvDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Boolean INIT_Boolean = false;

    /** 郵便番号1 */
    private String postal1 = INIT_String;

    /** 郵便番号2 */
    private String postal2 = INIT_String;

    /** 地方自治体コード */
    private String lgCode = INIT_String;

    /** 原文書住所 */
    private String addressOrg = INIT_String;

    /** 住所名 */
    private String addressName = INIT_String;

    /** 住所郵便番号まで */
    private String addressPostal = INIT_String;

    /** 住所番地まで */
    private String addressBlock = INIT_String;

    /** 行政区検索データ該当非該当 */
    private Boolean isGyoseikuData = INIT_Boolean;

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
