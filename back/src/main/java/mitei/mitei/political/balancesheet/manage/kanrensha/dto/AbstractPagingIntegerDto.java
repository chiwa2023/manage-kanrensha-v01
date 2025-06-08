package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

/**
 * 検索条件ページング(全件数Long)格納Dto
 */
public abstract class AbstractPagingIntegerDto {

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    abstract public Integer getAllCount();

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    abstract public void setAllCount(Integer allCount);

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
