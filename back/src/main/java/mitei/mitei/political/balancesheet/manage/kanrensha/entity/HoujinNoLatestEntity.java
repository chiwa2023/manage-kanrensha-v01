package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * houjin_no_latest接続用Entity
 */
@Entity
@Table(name = "houjin_no_latest")
public class HoujinNoLatestEntity implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "houjin_no_latest_id")
    private Long houjinNoLatestId = INIT_Long;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Long getHoujinNoLatestId() {
        return houjinNoLatestId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param houjinNoLatestId テーブルId
     */
    public void setHoujinNoLatestId(final Long houjinNoLatestId) {
        this.houjinNoLatestId = houjinNoLatestId;
    }

    /** 法人番号 */
    @Column(name = "corporate_number")
    private String corporateNumber = INIT_String;

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

    /** 処理区分 */
    @Column(name = "process")
    private String process = INIT_String;

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

    /** 訂正区分 */
    @Column(name = "correct")
    private Boolean correct = INIT_Boolean;

    /**
     * 訂正区分を取得する
     *
     * @return 訂正区分
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * 訂正区分を設定する
     *
     * @param correct 訂正区分
     */
    public void setCorrect(final Boolean correct) {
        this.correct = correct;
    }

    /** 更新年月日 */
    @Column(name = "update_date")
    private LocalDate updateDate = INIT_LocalDate;

    /**
     * 更新年月日を取得する
     *
     * @return 更新年月日
     */
    public LocalDate getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新年月日を設定する
     *
     * @param updateDate 更新年月日
     */
    public void setUpdateDate(final LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    /** 変更年月日 */
    @Column(name = "change_date")
    private LocalDate changeDate = INIT_LocalDate;

    /**
     * 変更年月日を取得する
     *
     * @return 変更年月日
     */
    public LocalDate getChangeDate() {
        return changeDate;
    }

    /**
     * 変更年月日を設定する
     *
     * @param changeDate 変更年月日
     */
    public void setChangeDate(final LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    /** 商号又は名称 */
    @Column(name = "name")
    private String name = INIT_String;

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

    /** 法人種別 */
    @Column(name = "kind")
    private String kind = INIT_String;

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

    /** 国内所在地（都道府県） */
    @Column(name = "prefecture_name")
    private String prefectureName = INIT_String;

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

    /** 国内所在地（市区町村） */
    @Column(name = "city_name")
    private String cityName = INIT_String;

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

    /** 国内所在地（丁目番地等） */
    @Column(name = "street_number")
    private String streetNumber = INIT_String;

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

    /** 都道府県コード */
    @Column(name = "prefecture_code")
    private String prefectureCode = INIT_String;

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

    /** 市区町村コード */
    @Column(name = "city_code")
    private String cityCode = INIT_String;

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

    /** 郵便番号 */
    @Column(name = "post_code")
    private String postCode = INIT_String;

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

    /** 国外所在地 */
    @Column(name = "address_outside")
    private String addressOutside = INIT_String;

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

    /** 登記記録の閉鎖等年月日 */
    @Column(name = "close_date")
    private LocalDate closeDate = INIT_LocalDate;

    /**
     * 登記記録の閉鎖等年月日を取得する
     *
     * @return 登記記録の閉鎖等年月日
     */
    public LocalDate getCloseDate() {
        return closeDate;
    }

    /**
     * 登記記録の閉鎖等年月日を設定する
     *
     * @param closeDate 登記記録の閉鎖等年月日
     */
    public void setCloseDate(final LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    /** 登記記録の閉鎖等の事由 */
    @Column(name = "close_cause")
    private String closeCause = INIT_String;

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

    /** 承継先法人番号 */
    @Column(name = "successor_corporate_number")
    private String successorCorporateNumber = INIT_String;

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

    /** 変更事由の詳細 */
    @Column(name = "change_cause")
    private String changeCause = INIT_String;

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

    /** 法人番号指定年月日 */
    @Column(name = "assignment_date")
    private LocalDate assignmentDate = INIT_LocalDate;

    /**
     * 法人番号指定年月日を取得する
     *
     * @return 法人番号指定年月日
     */
    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    /**
     * 法人番号指定年月日を設定する
     *
     * @param assignmentDate 法人番号指定年月日
     */
    public void setAssignmentDate(final LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    /** 最新履歴 */
    @Column(name = "latest")
    private Boolean latest = INIT_Boolean;

    /**
     * 最新履歴を取得する
     *
     * @return 最新履歴
     */
    public Boolean getLatest() {
        return latest;
    }

    /**
     * 最新履歴を設定する
     *
     * @param latest 最新履歴
     */
    public void setLatest(final Boolean latest) {
        this.latest = latest;
    }

    /** フリガナ */
    @Column(name = "furigana")
    private String furigana = INIT_String;

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

    /** 検索対象除外 */
    @Column(name = "hihyoji")
    private Boolean hihyoji = INIT_Boolean;

    /**
     * 検索対象除外を取得する
     *
     * @return 検索対象除外
     */
    public Boolean getHihyoji() {
        return hihyoji;
    }

    /**
     * 検索対象除外を設定する
     *
     * @param hihyoji 検索対象除外
     */
    public void setHihyoji(final Boolean hihyoji) {
        this.hihyoji = hihyoji;
    }

    /** 自然検索テキスト */
    @Column(name = "search_text")
    private String searchText = INIT_String;

    /**
     * 自然検索テキストを取得する
     *
     * @return 自然検索テキスト
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 自然検索テキストを設定する
     *
     * @param searchText 自然検索テキスト
     */
    public void setSearchText(final String searchText) {
        this.searchText = searchText;
    }

}
