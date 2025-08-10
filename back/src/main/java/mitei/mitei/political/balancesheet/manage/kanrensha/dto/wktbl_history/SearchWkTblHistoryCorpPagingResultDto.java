package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;

/**
 * 企業／団体一括登録履歴ワークテーブル検索ページングDto
 */
public class SearchWkTblHistoryCorpPagingResultDto extends AbstractPagingIntegerDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** ページ内件数 */
    private Integer limit = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

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

    /** 企業／団体登録候補リスト */
    private List<WkTblPartnerCorpHistoryEntity> listWktblCorp = new ArrayList<>();

    /**
     * 企業／団体登録候補リストを取得する
     *
     * @return 企業／団体登録候補リスト
     */
    public List<WkTblPartnerCorpHistoryEntity> getListWktblCorp() {
        return listWktblCorp;
    }

    /**
     * 企業／団体登録候補リストを設定する
     *
     * @param listWktblCorp 企業／団体登録候補リスト
     */
    public void setListWktblCorp(final List<WkTblPartnerCorpHistoryEntity> listWktblCorp) {
        this.listWktblCorp = listWktblCorp;
    }

}
