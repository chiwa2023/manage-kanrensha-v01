package mitei.mitei.political.balancesheet.manage.kanrensha.dto.address;

import java.io.Serializable;

/**
 * 住所入力Dto
 */
public class InputAddressDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 住所全体 */
    private String addressAll = INIT_String;
    /** 元住所全体 */
    private String orginAddressAll = INIT_String;

    /** 郵便番号1 */
    private String postalcode1 = INIT_String;
    /** 郵便番号2 */
    private String postalcode2 = INIT_String;

    /** 住所郵便番号まで1 */
    private String addressPostal = INIT_String;
    /** 住所番地 */
    private String addressBlock = INIT_String;
    /** 住所建物 */
    private String addressBuilding = INIT_String;
    /** 電話番号市外局番 */
    private String tel1 = INIT_String;
    /** 電話番号局番 */
    private String tel2 = INIT_String;
    /** 電話番号番号 */
    private String tel3 = INIT_String;

    /** 地方公共団体コード */
    private String lgCode = INIT_String;
    /** 町字Id */
    private String machiazaId = INIT_String;
    /** 街区Id */
    private String blkId = INIT_String;
    /** 住居Id */
    private String rsdtId = INIT_String;

    /** 住所郵便番号編集該否 */
    private Boolean isPostalEdit = INIT_Boolean;
    /** 住所番地編集該否 */
    private Boolean isBlockEdit = INIT_Boolean;
    /** 住所建物編集該否 */
    private Boolean isBuildingEdit = INIT_Boolean;

    /**
     * 住所全体を取得する
     *
     * @return 住所全体
     */
    public String getAddressAll() {
        return addressAll;
    }

    /**
     * 住所全体を設定する
     *
     * @param addressAll 住所全体
     */
    public void setAddressAll(final String addressAll) {
        this.addressAll = addressAll;
    }

    /**
     * 元住所全体を取得する
     *
     * @return 元住所全体
     */
    public String getOrginAddressAll() {
        return orginAddressAll;
    }

    /**
     * 元住所全体を設定する
     *
     * @param orginAddressAll 元住所全体元住所全体
     */
    public void setOrginAddressAll(final String orginAddressAll) {
        this.orginAddressAll = orginAddressAll;
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
     * 住所郵便番号までを設定する
     *
     * @param addressPostal 住所郵便番号まで
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

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
     * 電話番号市外局番を取得する
     *
     * @return 電話番号市外局番
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * 電話番号市外局番を設定する
     *
     * @param tel1 電話番号市外局番
     */
    public void setTel1(final String tel1) {
        this.tel1 = tel1;
    }

    /**
     * 電話番号局番を取得する
     *
     * @return 電話番号局番
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * 電話番号局番を設定する
     *
     * @param tel2 電話番号局番
     */
    public void setTel2(final String tel2) {
        this.tel2 = tel2;
    }

    /**
     * 電話番号番号を取得する
     *
     * @return 電話番号番号
     */
    public String getTel3() {
        return tel3;
    }

    /**
     * 電話番号番号を設定する
     *
     * @param tel3 電話番号番号
     */
    public void setTel3(final String tel3) {
        this.tel3 = tel3;
    }

    /**
     * 地方公共団体コードを取得する
     *
     * @return 地方公共団体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方公共団体コードを設定する
     *
     * @param lgCode 地方公共団体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /**
     * 町字Idを取得する
     *
     * @return 町字Id
     */
    public String getMachiazaId() {
        return machiazaId;
    }

    /**
     * 町字Idを設定する
     *
     * @param machiazaId 町字Id
     */
    public void setMachiazaId(final String machiazaId) {
        this.machiazaId = machiazaId;
    }

    /**
     * 街区Idを取得する
     *
     * @return 街区Id
     */
    public String getBlkId() {
        return blkId;
    }

    /**
     * 街区Idを設定する
     *
     * @param blkId 街区Id
     */
    public void setBlkId(final String blkId) {
        this.blkId = blkId;
    }

    /**
     * 住居Idを取得する
     *
     * @return 住居Id
     */
    public String getRsdtId() {
        return rsdtId;
    }

    /**
     * 住居Idを設定する
     *
     * @param rsdtId 住居Id
     */
    public void setRsdtId(final String rsdtId) {
        this.rsdtId = rsdtId;
    }

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

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostalcode1() {
        return postalcode1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postalcode1 郵便番号1
     */
    public void setPostalcode1(final String postalcode1) {
        this.postalcode1 = postalcode1;
    }

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostalcode2() {
        return postalcode2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postalcode2 郵便番号2
     */
    public void setPostalcode2(final String postalcode2) {
        this.postalcode2 = postalcode2;
    }

}
