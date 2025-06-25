package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

/**
 * ページング検索結果(全件数Long)Dto
 */
public abstract class AbstractPagingLongResultDto {

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    abstract public Long getAllCount();

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    abstract public void setAllCount(Long allCount);

    /**
     * 抽出件数を取得する
     *
     * @return 抽出件数
     */
    abstract public Integer getLimit();
    /**
     * 抽出件数を設定する
     *
     * @param limit 抽出件数
     */
    abstract public void setLimit(Integer limit);
    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    abstract public Integer getPageNumber();

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    abstract public void setPageNumber(Integer pageNumber);
}
