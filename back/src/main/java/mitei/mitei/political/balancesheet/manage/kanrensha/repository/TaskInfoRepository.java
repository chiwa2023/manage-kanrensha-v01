package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.TaskInfoEntity;

/**
 * task_info接続用Repository
 */
public interface TaskInfoRepository extends JpaRepository<TaskInfoEntity, Integer> {

    /**
     * タスクコードが同一(かつ最新)のデータを取得する
     *
     * @param taskCode タスクコード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<TaskInfoEntity> findByTaskInfoCodeAndIsLatest(Integer taskCode, boolean isLatest);

}
