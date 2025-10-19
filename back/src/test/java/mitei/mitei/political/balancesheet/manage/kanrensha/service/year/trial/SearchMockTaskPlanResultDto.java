package mitei.mitei.political.balancesheet.manage.kanrensha.service.year.trial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.FrameworkSwitchYearPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.TaskPlanBaseEntity;

/**
 * タスク計画検索結果Dto
 */
public class SearchMockTaskPlanResultDto extends FrameworkSwitchYearPagingIntegerDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク計画リスト */
    private List<TaskPlanBaseEntity> listTaskPlan = new ArrayList<>();

    /**
     * タスク計画リスト
     *
     * @return タスク計画リストを設定する
     */
    public List<TaskPlanBaseEntity> getListTaskPlan() {
        return listTaskPlan;
    }

    /**
     * タスク計画リストを取得する
     *
     * @param listTaskPlan タスク計画リスト
     */
    public void setListTaskPlan(final List<TaskPlanBaseEntity> listTaskPlan) {
        this.listTaskPlan = listTaskPlan;
    }

}
