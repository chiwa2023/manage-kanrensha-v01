package mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml;

import java.io.Serializable;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;

/**
 * 全XMLデータマスタ一括登録履歴ワークテーブル更新CapsuleDto
 */
public class SearchWkTblAddByXmlPagingResultDto // NOPMD DataClass
        extends AbstractPagingIntegerDto implements Serializable {

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

    /** 全XML登録リスト */
    private List<WkTblMasterAllByXmlEntity> listXmlEntity;

    /**
     * 全XML登録リストを取得する
     *
     * @return 全XML登録リスト
     */
    public List<WkTblMasterAllByXmlEntity> getListXmlEntity() {
        return listXmlEntity;
    }

    /**
     * 全XML登録リストを設定する
     *
     * @param listXmlEntity 全XML登録リスト
     */
    public void setListXmlEntity(final List<WkTblMasterAllByXmlEntity> listXmlEntity) {
        this.listXmlEntity = listXmlEntity;
    }

}
