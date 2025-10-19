package mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2026;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.TaskPlanBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;

/**
 * task_plan_2025接続用Repository
 */
public interface TaskPlan2026Repository extends JpaRepository<TaskPlan2026Entity, Integer> {

    /**
     * ページングで検索する
     *
     * @param limit 取得件数
     * @param offset 取得開始位置
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_2026 limit ?1 offset ?2" , nativeQuery = true)
    List<TaskPlanBaseEntity> findAllItems(Integer limit,Integer offset);
    
    /**
     * 全検索の件数を取得する
     *
     * @return 全件数
     */
    @Query(value = "SELECT count(*) FROM task_plan_2026" , nativeQuery = true)
    Integer countAllItems();
}
