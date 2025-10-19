package mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;

/**
 * 作業内容承認住所検索結果Dto
 */
public class SearchApprovalAddressResultDto extends AbstractPagingIntegerDto // NOPMD DataClass
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

    /** 住所承認作業リスト */
    private List<MasterKanrenshaAddressBaseEntity> listAddress = new ArrayList<>();

    /**
     * 住所承認作業リストを取得する
     *
     * @return 住所承認作業リスト
     */
    public List<MasterKanrenshaAddressBaseEntity> getListAddress() {
        return listAddress;
    }

    /**
     * 住所承認作業リストを設定する
     *
     * @param listAddress 住所承認作業リスト
     */
    public void setListAddress(final List<MasterKanrenshaAddressBaseEntity> listAddress) {
        this.listAddress = listAddress;
    }

}
