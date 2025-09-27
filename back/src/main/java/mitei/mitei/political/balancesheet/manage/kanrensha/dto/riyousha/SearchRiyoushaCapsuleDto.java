package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

/**
 * 利用者検索条件Dto
 */
public class SearchRiyoushaCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** APIユーザ検索 */
    private Boolean isComradeSearch = INIT_Boolean;

    /** 運営者検索 */
    private Boolean isManagerSearch = INIT_Boolean;

    /** 管理者検索 */
    private Boolean isAdminSearch = INIT_Boolean;

    /**
     * APIユーザ検索を取得する
     *
     * @return APIユーザ検索
     */
    public Boolean getIsComradeSearch() {
        return isComradeSearch;
    }

    /**
     * APIユーザ検索を設定する
     *
     * @param isComradeSearch APIユーザ検索
     */
    public void setIsComradeSearch(final Boolean isComradeSearch) {
        this.isComradeSearch = isComradeSearch;
    }

    /**
     * APIユーザ検索を取得する
     *
     * @return APIユーザ検索
     */
    public Boolean getIsManagerSearch() {
        return isManagerSearch;
    }

    /**
     * APIユーザ検索を設定する
     *
     * @param isManagerSearch APIユーザ検索
     */
    public void setIsManagerSearch(final Boolean isManagerSearch) {
        this.isManagerSearch = isManagerSearch;
    }

    /**
     * APIユーザ検索を取得する
     *
     * @return APIユーザ検索
     */
    public Boolean getIsAdminSearch() {
        return isAdminSearch;
    }

    /**
     * APIユーザ検索を設定する
     *
     * @param isAdminSearch APIユーザ検索
     */
    public void setIsAdminSearch(final Boolean isAdminSearch) {
        this.isAdminSearch = isAdminSearch;
    }

}
