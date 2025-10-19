package mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.TaskInfoConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertTaskPlanY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertTaskPlanY2025LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    @Sql({ "delete_task_plan_2025.sql", "sample_task_info.sql" })
    @Transactional
    void test() {

        UserPersonLeastDto usertDto = CreateLeastUserForTestUtil.practice();
        Integer idSaved = insertTaskPlanY2025Logic.practice(usertDto, TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV);
        assertNotEquals(0, idSaved); // 0でなければ登録成功

        List<TaskPlan2025Entity> list = taskPlan2025Repository.findAll();
        assertEquals(1, list.size());

        TaskPlan2025Entity entity = list.get(0);

        assertEquals(false, entity.getIsFinished());
        assertEquals(true, entity.getIsLatest());
        assertEquals("admin,manager", entity.getRoleList());
        assertEquals("pageUrl", entity.getTransferPass());
        assertEquals("郵便番号差分修正", entity.getTaskPlanName());
    }
}
