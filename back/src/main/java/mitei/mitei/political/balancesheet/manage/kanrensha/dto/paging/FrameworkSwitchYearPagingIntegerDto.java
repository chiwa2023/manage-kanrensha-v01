package mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 年切替ページング(IdがInteger)実装Dto
 */
public class FrameworkSwitchYearPagingIntegerDto // NOPMD DataClass
        implements Serializable, SwitchYearPagingIntegerDtoInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** 抽出件数 */
    private Integer limit = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

    /** 検索開始日付 */
    private LocalDate startDate = INIT_LocalDate;

    /** 検索終了日付 */
    private LocalDate endDate = INIT_LocalDate;

    /** 検索条件変更フラグ */
    private Boolean isChangedCondition = INIT_Boolean;

    /** 前段階表示件数 */
    private Integer preStepViewCount = INIT_Integer;

    /**
     * 全件数を取得する
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * ページ内件数を取得する
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * ページ内件数を設定する
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 検索開始日付を取得する
     *
     * @return 検索開始日付
     */
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * 検索開始日付を設定する
     *
     * @param startDate 検索開始日付
     */
    @Override
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * 検索終了日付を取得する
     *
     * @return 検索開始日付
     */
    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * 検索終了日付を設定する
     *
     * @param endDate 検索終了日付
     */
    @Override
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * 検索条件変更フラグを取得する
     *
     * @return 検索条件変更フラグ
     */
    @Override
    public Boolean getIsChangedCondition() {
        return isChangedCondition;
    }

    /**
     * 検索条件変更フラグを設定する
     *
     * @param isChangedCondition 検索条件変更フラグ
     */
    @Override
    public void setIsChangedCondition(final Boolean isChangedCondition) {
        this.isChangedCondition = isChangedCondition;
    }

    /**
     * 前段階表示件数を取得する
     *
     * @return 前段階表示件数
     */
    @Override
    public Integer getPreStepViewCount() {
        return preStepViewCount;
    }

    /**
     * 前段階表示件数を設定する
     *
     * @param preStepViewCount 前段階表示件数
     */
    @Override
    public void setPreStepViewCount(final Integer preStepViewCount) {
        this.preStepViewCount = preStepViewCount;
    }

}
