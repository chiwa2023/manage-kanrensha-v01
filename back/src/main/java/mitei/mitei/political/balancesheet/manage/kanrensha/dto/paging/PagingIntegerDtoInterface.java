package mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging;

/**
 * 件数がIntegerで戻るページングInterface
 */
public interface PagingIntegerDtoInterface {

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    Integer getAllCount();

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    void setAllCount(Integer allCount);

    /**
     * 抽出件数を取得する
     *
     * @return 抽出件数
     */
    Integer getLimit();

    /**
     * 抽出件数を設定する
     *
     * @param limit 抽出件数
     */
    void setLimit(Integer limit);

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    Integer getPageNumber();

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    void setPageNumber(Integer pageNumber);

}
