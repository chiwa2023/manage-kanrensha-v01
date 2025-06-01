package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import java.io.Serializable;

/**
 * 法人番号CSV格納Dto 汎用性のため、最終的には使用しない項目(一連番号など)も読み取り項目として設定する
 */
public class HoujinNoCsvDto implements Serializable {

    /** serialId */
    private static final long serialVersionUID = 1L;

    // 1から6はAPI接続時のヘッダ
    // 1 ルート要素corporations
    // 2 最終更新年月日lastUpdateDate
    // 3 総件数count
    // 4 分割番号divideNumber
    // 5 分割数divideSize
    // 6 法人等要素corporation

    /** 初期値(String) */
    private static final String INIT_String = "";

    /** 7 一連番号 sequenceNumber */
    private String sequenceNumber = INIT_String;

    /** 8 法人番号 corporateNumber */
    private String corporateNumber = INIT_String;

    /** 9 処理区分 process */
    private String process = INIT_String;

    /** 10 訂正区分 correct */
    private String correct = INIT_String;

    /** 11 更新年月日 updateDate */
    private String updateDate = INIT_String;

    /** 12 変更年月日 changeDate */
    private String changeDate = INIT_String;

    /** 13 商号又は名称 name */
    private String name = INIT_String;

    /** 14 商号又は名称イメージID nameImageId */
    private String nameImageId = INIT_String;

    /** 15 法人種別 kind */
    private String kind = INIT_String;

    /** 16 国内所在地（都道府県） prefectureName */
    private String prefectureName = INIT_String;

    /** 17 国内所在地（市区町村） cityName */
    private String cityName = INIT_String;

    /** 18 国内所在地（丁目番地等） streetNumber */
    private String streetNumber = INIT_String;

    /** 19 国内所在地イメージID addressImageId */
    private String addressImageId = INIT_String;

    /** 20 都道府県コード prefectureCode */
    private String prefectureCode = INIT_String;

    /** 21 市区町村コード cityCode */
    private String cityCode = INIT_String;

    /** 22 郵便番号 postCode */
    private String postCode = INIT_String;

    /** 23 国外所在地 addressOutside */
    private String addressOutside = INIT_String;

    /** 24 国外所在地イメージID addressOutsideImageId */
    private String addressOutsideImageId = INIT_String;

    /** 25 登記記録の閉鎖等年月日 closeDate */
    private String closeDate = INIT_String;

    /** 26 登記記録の閉鎖等の事由 closeCause */
    private String closeCause = INIT_String;

    /** 27 承継先法人番号 successorCorporateNumber */
    private String successorCorporateNumber = INIT_String;

    /** 28 変更事由の詳細 changeCause */
    private String changeCause = INIT_String;

    /** 29 法人番号指定年月日 assignmentDate */
    private String assignmentDate = INIT_String;

    /** 30 最新履歴 latest */
    private String latest = INIT_String;

    /** 31 商号又は名称（英語表記） enName */
    private String enName = INIT_String;

    /** 32 国内所在地（都道府県）（英語表記） enPrefectureName */
    private String enPrefectureName = INIT_String;

    /** 33 国内所在地（市町村丁目番地等）（英語表記） enCityName */
    private String enCityName = INIT_String;

    /** 34 国外所在地（英語表記） enAddressOutside */
    private String enAddressOutside = INIT_String;

    /** 35 フリガナ furigana */
    private String furigana = INIT_String;

    /** 36 検索対象除外 hihyoji */
    private String hihyoji = INIT_String;

    /**
     * 一連番号を取得する
     *
     * @return 一連番号
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * 一連番号を設定する
     *
     * @param sequenceNumber 一連番号
     */
    public void setSequenceNumber(final String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * 法人番号を取得する
     *
     * @return 法人番号
     */
    public String getCorporateNumber() {
        return corporateNumber;
    }

    /**
     * 法人番号を設定する
     *
     * @param corporateNumber 法人番号
     */
    public void setCorporateNumber(final String corporateNumber) {
        this.corporateNumber = corporateNumber;
    }

    /**
     * 処理区分を取得する
     *
     * @return 処理区分
     */
    public String getProcess() {
        return process;
    }

    /**
     * 処理区分を設定する
     *
     * @param process 処理区分
     */
    public void setProcess(final String process) {
        this.process = process;
    }

    /**
     * 訂正区分を取得する
     *
     * @return 訂正区分
     */
    public String getCorrect() {
        return correct;
    }

    /**
     * 訂正区分を設定する
     *
     * @param correct 訂正区分
     */
    public void setCorrect(final String correct) {
        this.correct = correct;
    }

    /**
     * 更新年月日を取得する
     *
     * @return 更新年月日
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新年月日を設定する
     *
     * @param updateDate 更新年月日
     */
    public void setUpdateDate(final String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 変更年月日を取得する
     *
     * @return 変更年月日
     */
    public String getChangeDate() {
        return changeDate;
    }

    /**
     * 変更年月日を設定する
     *
     * @param changeDate 変更年月日
     */
    public void setChangeDate(final String changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 商号又は名称を取得する
     *
     * @return 商号又は名称
     */
    public String getName() {
        return name;
    }

    /**
     * 商号又は名称を設定する
     *
     * @param name 商号又は名称
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 商号又は名称イメージIDを取得する
     *
     * @return 商号又は名称イメージID
     */
    public String getNameImageId() {
        return nameImageId;
    }

    /**
     * 商号又は名称イメージIDを設定する
     *
     * @param nameImageId 商号又は名称イメージID
     */
    public void setNameImageId(final String nameImageId) {
        this.nameImageId = nameImageId;
    }

    /**
     * 法人種別を取得する
     *
     * @return 法人種別
     */
    public String getKind() {
        return kind;
    }

    /**
     * 法人種別を設定する
     *
     * @param kind 法人種別
     */
    public void setKind(final String kind) {
        this.kind = kind;
    }

    /**
     * 国内所在地（都道府県）を取得する
     *
     * @return 国内所在地（都道府県）
     */
    public String getPrefectureName() {
        return prefectureName;
    }

    /**
     * 国内所在地（都道府県）を設定する
     *
     * @param prefectureName 国内所在地（都道府県）
     */
    public void setPrefectureName(final String prefectureName) {
        this.prefectureName = prefectureName;
    }

    /**
     * 国内所在地（市区町村）を取得する
     *
     * @return 国内所在地（市区町村）
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 国内所在地（市区町村）を設定する
     *
     * @param cityName 国内所在地（市区町村）
     */
    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    /**
     * 国内所在地（丁目番地等）を取得する
     *
     * @return 国内所在地（丁目番地等）
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * 国内所在地（丁目番地等）を設定する
     *
     * @param streetNumber 国内所在地（丁目番地等）
     */
    public void setStreetNumber(final String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * 国内所在地イメージIDを取得する
     *
     * @return 国内所在地イメージID
     */
    public String getAddressImageId() {
        return addressImageId;
    }

    /**
     * 国内所在地イメージIDを設定する
     *
     * @param addressImageId 国内所在地イメージID
     */
    public void setAddressImageId(final String addressImageId) {
        this.addressImageId = addressImageId;
    }

    /**
     * 都道府県コードを取得する
     *
     * @return 都道府県コード
     */
    public String getPrefectureCode() {
        return prefectureCode;
    }

    /**
     * 都道府県コードを設定する
     *
     * @param prefectureCode 都道府県コード
     */
    public void setPrefectureCode(final String prefectureCode) {
        this.prefectureCode = prefectureCode;
    }

    /**
     * 市区町村コードを取得する
     *
     * @return 市区町村コード
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 市区町村コードを設定する
     *
     * @param cityCode 市区町村コード
     */
    public void setCityCode(final String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 郵便番号を取得する
     *
     * @return 郵便番号
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 郵便番号を設定する
     *
     * @param postCode 郵便番号
     */
    public void setPostCode(final String postCode) {
        this.postCode = postCode;
    }

    /**
     * 国外所在地を取得する
     *
     * @return 国外所在地
     */
    public String getAddressOutside() {
        return addressOutside;
    }

    /**
     * 国外所在地を設定する
     *
     * @param addressOutside 国外所在地
     */
    public void setAddressOutside(final String addressOutside) {
        this.addressOutside = addressOutside;
    }

    /**
     * 国外所在地イメージIDを取得する
     *
     * @return 国外所在地イメージID
     */
    public String getAddressOutsideImageId() {
        return addressOutsideImageId;
    }

    /**
     * 国外所在地イメージIDを設定する
     *
     * @param addressOutsideImageId 国外所在地イメージID
     */
    public void setAddressOutsideImageId(final String addressOutsideImageId) {
        this.addressOutsideImageId = addressOutsideImageId;
    }

    /**
     * 登記記録の閉鎖等年月日を取得する
     *
     * @return 登記記録の閉鎖等年月日
     */
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * 登記記録の閉鎖等年月日を設定する
     *
     * @param closeDate 登記記録の閉鎖等年月日
     */
    public void setCloseDate(final String closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * 登記記録の閉鎖等の事由を取得する
     *
     * @return 登記記録の閉鎖等の事由
     */
    public String getCloseCause() {
        return closeCause;
    }

    /**
     * 登記記録の閉鎖等の事由を設定する
     *
     * @param closeCause 登記記録の閉鎖等の事由
     */
    public void setCloseCause(final String closeCause) {
        this.closeCause = closeCause;
    }

    /**
     * 承継先法人番号を取得する
     *
     * @return 承継先法人番号
     */
    public String getSuccessorCorporateNumber() {
        return successorCorporateNumber;
    }

    /**
     * 承継先法人番号を設定する
     *
     * @param successorCorporateNumber 承継先法人番号
     */
    public void setSuccessorCorporateNumber(final String successorCorporateNumber) {
        this.successorCorporateNumber = successorCorporateNumber;
    }

    /**
     * 変更事由の詳細を取得する
     *
     * @return 変更事由の詳細
     */
    public String getChangeCause() {
        return changeCause;
    }

    /**
     * 変更事由の詳細を設定する
     *
     * @param changeCause 変更事由の詳細
     */
    public void setChangeCause(final String changeCause) {
        this.changeCause = changeCause;
    }

    /**
     * 法人番号指定年月日を取得する
     *
     * @return 法人番号指定年月日
     */
    public String getAssignmentDate() {
        return assignmentDate;
    }

    /**
     * 法人番号指定年月日を設定する
     *
     * @param assignmentDate 法人番号指定年月日
     */
    public void setAssignmentDate(final String assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    /**
     * 最新履歴を取得する
     *
     * @return 最新履歴
     */
    public String getLatest() {
        return latest;
    }

    /**
     * 最新履歴を設定する
     *
     * @param latest 最新履歴
     */
    public void setLatest(final String latest) {
        this.latest = latest;
    }

    /**
     * 商号又は名称（英語表記）を取得する
     *
     * @return 商号又は名称（英語表記）
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 国内所在地（都道府県）（英語表記）を設定する
     *
     * @param enName 国内所在地（都道府県）（英語表記）
     */
    public void setEnName(final String enName) {
        this.enName = enName;
    }

    /**
     * 国内所在地（都道府県）（英語表記）を取得する
     *
     * @return 国内所在地（都道府県）（英語表記）
     */
    public String getEnPrefectureName() {
        return enPrefectureName;
    }

    /**
     * 国内所在地（都道府県）（英語表記）を設定する
     *
     * @param enPrefectureName 国内所在地（都道府県）（英語表記）
     */
    public void setEnPrefectureName(final String enPrefectureName) {
        this.enPrefectureName = enPrefectureName;
    }

    /**
     * 国内所在地（市町村丁目番地等）（英語表記）を取得する
     *
     * @return 国内所在地（市町村丁目番地等）（英語表記）
     */
    public String getEnCityName() {
        return enCityName;
    }

    /**
     * 国内所在地（市町村丁目番地等）（英語表記）を設定する
     *
     * @param enCityName 国内所在地（市町村丁目番地等）（英語表記）
     */
    public void setEnCityName(final String enCityName) {
        this.enCityName = enCityName;
    }

    /**
     * 国外所在地（英語表記）を取得する
     *
     * @return 国外所在地（英語表記）
     */
    public String getEnAddressOutside() {
        return enAddressOutside;
    }

    /**
     * 国外所在地（英語表記）を設定する
     *
     * @param enAddressOutside 国外所在地（英語表記）
     */
    public void setEnAddressOutside(final String enAddressOutside) {
        this.enAddressOutside = enAddressOutside;
    }

    /**
     * フリガナを取得する
     *
     * @return フリガナ
     */
    public String getFurigana() {
        return furigana;
    }

    /**
     * フリガナを設定する
     *
     * @param furigana フリガナ
     */
    public void setFurigana(final String furigana) {
        this.furigana = furigana;
    }

    /**
     * 検索対象除外を取得する
     *
     * @return 検索対象除外
     */
    public String getHihyoji() {
        return hihyoji;
    }

    /**
     * 検索対象除外を設定する
     *
     * @param hihyoji 検索対象除外
     */
    public void setHihyoji(final String hihyoji) {
        this.hihyoji = hihyoji;
    }

}
