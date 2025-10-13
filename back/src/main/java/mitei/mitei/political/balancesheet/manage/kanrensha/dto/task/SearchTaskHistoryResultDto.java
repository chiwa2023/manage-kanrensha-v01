package mitei.mitei.political.balancesheet.manage.kanrensha.dto.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.TaskPlanBaseEntity;

/**
 * タスク計画履歴検索結果Dto
 */
public class SearchTaskHistoryResultDto extends AbstractPagingIntegerDto // NOPMD DataClass
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

    /** タスク計画リスト */
    private List<TaskPlanBaseEntity> listTaskHistory = new ArrayList<>();

    /**
     * タスク計画リストを取得する
     *
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> getListTaskHistory() {
        return listTaskHistory;
    }

    /**
     * タスク計画リストを設定する
     *
     * @param listTaskHistory タスク計画リスト
     */
    public void setListTaskHistory(final List<TaskPlanBaseEntity> listTaskHistory) {
        this.listTaskHistory = listTaskHistory;
    }

}
