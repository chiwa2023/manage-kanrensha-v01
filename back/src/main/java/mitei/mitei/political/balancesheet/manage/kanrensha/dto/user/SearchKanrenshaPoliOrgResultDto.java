package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;

/**
 * 関連者政治団体検索結果Dto
 */
public class SearchKanrenshaPoliOrgResultDto extends AbstractPagingIntegerDto // NOPMD DataClass
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

    /** 関連者政治団体マスタリスト */
    private List<MasterPoliticalOrganizationEntity> listMasterPoliOrg = new ArrayList<>();

    /**
     * 関連者政治団体マスタリストを取得する
     *
     * @return 関連者政治団体マスタリスト
     */
    public List<MasterPoliticalOrganizationEntity> getListMasterPoliOrg() {
        return listMasterPoliOrg;
    }

    /**
     * 関連者政治団体マスタリストを設定する
     *
     * @param listMasterPoliOrg 関連者政治団体マスタリスト
     */
    public void setListMasterPoliOrg(final List<MasterPoliticalOrganizationEntity> listMasterPoliOrg) {
        this.listMasterPoliOrg = listMasterPoliOrg;
    }

}
