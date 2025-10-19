package mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingLongDto;

/**
 * 法人番号検索条件格納Dto
 */
public class HoujinNoCapsuleDto extends AbstractPagingLongDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 自然検索該否 */
    private Boolean isNaturalSearch = INIT_Boolean;

    /** 検索語番号 */
    private String searchNoWords = INIT_String;

    /** 検索語自然検索 */
    private String searchNaturaloWords = INIT_String;

    /**
     * 自然検索該否を取得する
     *
     * @return 自然検索該否
     */
    public Boolean getIsNaturalSearch() {
        return isNaturalSearch;
    }

    /**
     * 自然検索該否を設定する
     *
     * @param isNaturalSearch 自然検索該否
     */
    public void setIsNaturalSearch(final Boolean isNaturalSearch) {
        this.isNaturalSearch = isNaturalSearch;
    }

    /**
     * 検索語番号を取得する
     *
     * @return 検索語番号
     */
    public String getSearchNoWords() {
        return searchNoWords;
    }

    /**
     * 検索語番号を設定する
     *
     * @param searchNoWords 検索語番号
     */
    public void setSearchNoWords(final String searchNoWords) {
        this.searchNoWords = searchNoWords;
    }

    /**
     * 検索語自然検索を取得する
     *
     * @return 検索語自然検索
     */
    public String getSearchNaturaloWords() {
        return searchNaturaloWords;
    }

    /**
     * 検索語自然検索を設定する
     *
     * @param searchNaturaloWords 検索語自然検索
     */
    public void setSearchNaturaloWords(final String searchNaturaloWords) {
        this.searchNaturaloWords = searchNaturaloWords;
    }

    /** 全件数 */
    private Long allCount = INIT_Long;

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
    public Long getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    @Override
    public void setAllCount(final Long allCount) {
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