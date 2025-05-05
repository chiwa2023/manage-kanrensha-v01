package mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal;

import java.io.Serializable;

/**
 * 郵便番号検索条件Dto
 */
public class PostalCodeCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 郵便番号1 */
    private String postal1 = INIT_String;

    /** 郵便番号2 */
    private String postal2 = INIT_String;

    /** 行政区検索 */
    private Boolean isGyouseikuData = INIT_Boolean;

    /** 選択された住所郵便番号まで */
    private Integer selectedPostal = INIT_Integer;

    /** 選択された住所番地まで */
    private String selectedBlock = INIT_String;

    /** 地方自治体コード */
    private String lgCode = INIT_String;

    /** 郵便番号 */
    private String postalCode = INIT_String;

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
     * 行政区検索該否を取得する
     *
     * @return 行政区検索該否
     */
    public Boolean getIsGyouseikuData() {
        return isGyouseikuData;
    }

    /**
     * 行政区検索該否を設定する
     *
     * @param isGyouseikuData 行政区検索該否
     */
    public void setIsGyouseikuData(final Boolean isGyouseikuData) {
        this.isGyouseikuData = isGyouseikuData;
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
     * 選択された住所郵便番号までを取得する
     *
     * @return 選択された住所郵便番号まで
     */
    public Integer getSelectedPostal() {
        return selectedPostal;
    }

    /**
     * 選択された住所郵便番号までを設定する
     *
     * @param selectedPostal 選択された住所郵便番号まで
     */
    public void setSelectedPostal(final Integer selectedPostal) {
        this.selectedPostal = selectedPostal;
    }

    /**
     * 選択された住所番地までを取得する
     *
     * @return 選択された住所番地まで
     */
    public String getSelectedBlock() {
        return selectedBlock;
    }

    /**
     * 選択された住所番地までを設定する
     *
     * @param selectedBlock 選択された住所番地まで
     */
    public void setSelectedBlock(final String selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

}
