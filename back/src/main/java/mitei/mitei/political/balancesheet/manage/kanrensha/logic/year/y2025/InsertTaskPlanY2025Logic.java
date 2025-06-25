package mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.y2025;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.TaskInfoEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.TaskInfoRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * タスク情報を基にタスク計画挿入Logic
 */
@Component
public class InsertTaskPlanY2025Logic {

    /** タスク情報Repository */
    @Autowired
    private TaskInfoRepository taskInfoRepository;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /** テービル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param userDto  ユーザ最低限Dto
     * @param taskCode タスク情報コード
     * @return 挿入後Id
     */
    public Integer practice(final UserPersonLeastDto userDto, final Integer taskCode) {

        List<TaskInfoEntity> list = taskInfoRepository.findByTaskInfoCodeAndIsLatest(taskCode,
                SetTableDataHistoryUtil.INSERT_STATE);
        if (list.isEmpty()) {
            throw new EmptyResultDataAccessException("指定されたタスク情報が存在しません(" + taskCode + ")", 0);
        }

        // DB的に1件しか存在しない想定
        TaskInfoEntity taskInfoEntity = list.get(0);

        TaskPlan2025Entity entityPlan = new TaskPlan2025Entity();

        setTableDataHistoryUtil.practiceInsert(userDto, entityPlan);
        entityPlan.setRoleList(taskInfoEntity.getRoleList());
        entityPlan.setTaskPlanName(taskInfoEntity.getTaskInfoName());
        entityPlan.setTransferPass(taskInfoEntity.getTransferPass());

        // 新規タスクは新しいコード番号を振る
        int code = 1;
        Optional<TaskPlan2025Entity> optional = taskPlan2025Repository.findFirstByOrderByTaskPlanCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getTaskPlanCode();
        }
        entityPlan.setTaskPlanCode(code);

        return taskPlan2025Repository.save(entityPlan).getTaskPlanId();
    }

}
