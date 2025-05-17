package mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingLongResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;

/**
 * 法人番号検索結果Dto
 */
public class HoujinNoResultDto extends AbstractPagingLongResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 法人番号検索結果 */
    private List<HoujinNoLatestEntity> listHoujinNo = new ArrayList<>();

    /**
     * 法人番号検索結果を取得する
     *
     * @return 法人番号検索結果
     */
    public List<HoujinNoLatestEntity> getListHoujinNo() {
        return listHoujinNo;
    }

    /**
     * 法人番号検索結果を設定する
     *
     * @param listHoujinNo 法人番号検索結果
     */
    public void setListHoujinNo(final List<HoujinNoLatestEntity> listHoujinNo) {
        this.listHoujinNo = listHoujinNo;
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
