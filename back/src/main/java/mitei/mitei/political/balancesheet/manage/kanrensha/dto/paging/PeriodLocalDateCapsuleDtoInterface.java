package mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging;

import java.time.LocalDate;

/**
 * 検索条件期間日付Interface
 */
public interface PeriodLocalDateCapsuleDtoInterface {

    /**
     * 開始日付を取得する
     *
     * @return 開始日付
     */
    LocalDate getStartDate();

    /**
     * 開始日付を設定する
     *
     * @param startDate 開始日付
     */
    void setStartDate(LocalDate startDate);

    /**
     * 終了日付を取得する
     *
     * @return 終了日付
     */
    LocalDate getEndDate();

    /**
     * 終了日付を設定する
     *
     * @param endDate 終了日付
     */
    void setEndDate(LocalDate endDate);

}
