package mitei.mitei.political.balancesheet.manage.kanrensha.dto.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.PagingIntegerDtoInterface;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.PeriodDatetimeCapsuleDtoInterface;

/**
 * タスク計画検索条件Dto
 */
public class SearchTaskPlanCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, PeriodDatetimeCapsuleDtoInterface, PagingIntegerDtoInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** 検索開始日時 */
    private LocalDateTime startDate = INIT_Timestamp;

    /** 検索終了日時 */
    private LocalDateTime endDate = INIT_Timestamp;

    /** タスク検索語 */
    private String searchTaskWord = INIT_String;

    /**
     * 検索開始日時を取得する
     */
    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * 検索開始日時を設定する
     */
    @Override
    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * 検索終了日時を取得する
     */
    @Override
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * 検索終了日時を設定する
     */
    @Override
    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * タスク検索語
     *
     * @return タスク検索語を取得する
     */
    public String getSearchTaskWord() {
        return searchTaskWord;
    }

    /**
     * タスク検索語を設定する
     *
     * @param searchTaskWord タスク検索語
     */
    public void setSearchTaskWord(final String searchTaskWord) {
        this.searchTaskWord = searchTaskWord;
    }

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** 抽出件数 */
    private Integer limit = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * 抽出件数を取得する
     *
     * @return 抽出件数
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * 抽出件数を設定する
     *
     * @param limit 抽出件数
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

}
