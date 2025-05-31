package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

/**
 * 検索条件ページング(全件数Long)格納Dto
 */
public abstract class AbstractPagingLongCapsuleDto {

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    abstract public Long getAllCount();

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数
     */
    abstract public void setAllCount(Long allCount);

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
