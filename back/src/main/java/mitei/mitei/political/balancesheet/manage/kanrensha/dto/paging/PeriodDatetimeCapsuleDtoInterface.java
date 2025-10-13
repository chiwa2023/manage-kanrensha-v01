package mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging;

import java.time.LocalDateTime;

/**
 * 検索条件期間日時Interface
 */
public interface PeriodDatetimeCapsuleDtoInterface {

    /**
     * 開始日時を取得する
     *
     * @return 開始日時
     */
    LocalDateTime getStartDate();

    /**
     * 開始日時を設定する
     *
     * @param startDate 開始日時
     */
    void setStartDate(LocalDateTime startDate);

    /**
     * 終了日時を取得する
     *
     * @return 終了日時
     */
    LocalDateTime getEndDate();

    /**
     * 終了日時を設定する
     *
     * @param endDate 終了日時
     */
    void setEndDate(LocalDateTime endDate);

}
