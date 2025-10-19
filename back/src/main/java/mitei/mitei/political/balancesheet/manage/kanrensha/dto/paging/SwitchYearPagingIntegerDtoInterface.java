package mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging;

import java.time.LocalDate;

/**
 * 年切替処理ありページングDto(IdがInteger)
 */
public interface SwitchYearPagingIntegerDtoInterface extends PagingIntegerDtoInterface {

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

    /**
     * 検索条件変更フラグを取得する
     *
     * @return 検索条件変更フラグ
     */
    Boolean getIsChangedCondition();

    /**
     * 検索条件変更フラグを設定する
     *
     * @param isChangedCondition 検索条件変更フラグ
     */
    void setIsChangedCondition(Boolean isChangedCondition);

    /**
     * 前段階表示件数を取得する
     *
     * @return 前段階表示件数
     */
    Integer getPreStepViewCount();

    /**
     * 前段階表示件数を設定する
     *
     * @param preStepViewCount 前段階表示件数
     */
    void setPreStepViewCount(Integer preStepViewCount);

}
