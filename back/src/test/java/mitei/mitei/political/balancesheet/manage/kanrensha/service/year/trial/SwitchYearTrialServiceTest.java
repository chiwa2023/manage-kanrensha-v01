package mitei.mitei.political.balancesheet.manage.kanrensha.service.year.trial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.TaskPlanBaseEntity;

/**
 * SwitchYearTrialService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SwitchYearTrialServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SwitchYearTrialService switchYearTrialService;

    @Test
    @Tag("TableTruncate") // NOPMD
    @Sql({ "SwitchYearTrialServiceTest2024.sql", "SwitchYearTrialServiceTest2025.sql", // NOPMD
            "SwitchYearTrialServiceTest2026.sql" }) // NOPMD
    void testAllCount() throws Exception {

        SearchMockTaskPlanCapsuleDto capsuleDto = new SearchMockTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(50);
        capsuleDto.setPageNumber(0);
        // 日時条件は検索には直接使用していないので2024-2026年を検索することしか表していない
        capsuleDto.setStartDate(LocalDate.of(2024, 5, 2));
        capsuleDto.setEndDate(LocalDate.of(2026, 12, 31));

        SearchMockTaskPlanResultDto resultDto = switchYearTrialService.practice(capsuleDto);
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "Limitは必ず同値であること"); // NOPMD
        assertEquals(0, resultDto.getPageNumber(), "3年分の全件数は50未満であるのでページ番号が0であること");
        assertEquals(19, resultDto.getAllCount(), "とにかく全取得数であること");
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "SwitchYearTrialServiceTest2024.sql", "SwitchYearTrialServiceTest2025.sql",
            "SwitchYearTrialServiceTest2026.sql" })
    void testSearch_1() throws Exception {

        SearchMockTaskPlanCapsuleDto capsuleDto = new SearchMockTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(5);
        capsuleDto.setPageNumber(0);
        // 日時条件は検索には直接使用していないので2024-2026年を検索することしか表していない
        capsuleDto.setStartDate(LocalDate.of(2024, 5, 2));
        capsuleDto.setEndDate(LocalDate.of(2026, 12, 31));

        SearchMockTaskPlanResultDto resultDto = switchYearTrialService.practice(capsuleDto);
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "Limitは必ず同値であること");
        assertEquals(19, resultDto.getAllCount(), "とにかく全件数が全取得数であること"); // NOPMD
        assertEquals(0, resultDto.getPageNumber(), "ページ番号に変更がないこと"); // NOPMD
        List<TaskPlanBaseEntity> list = resultDto.getListTaskPlan();
        assertEquals(5, list.size(), "全件数よりLimitが小さく、ページ番号最末尾でない場合はLimitと一致していること"); // NOPMD

        assertEquals(102, list.get(0).getTaskPlanId());
        assertEquals(103, list.get(1).getTaskPlanId());
        assertEquals(104, list.get(2).getTaskPlanId());
        assertEquals(105, list.get(3).getTaskPlanId());
        assertEquals(106, list.get(4).getTaskPlanId());
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "SwitchYearTrialServiceTest2024.sql", "SwitchYearTrialServiceTest2025.sql",
            "SwitchYearTrialServiceTest2026.sql" })
    void testSearch_1_2() throws Exception {

        SearchMockTaskPlanCapsuleDto capsuleDto = new SearchMockTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(4);
        capsuleDto.setPageNumber(1);
        // 日時条件は検索には直接使用していないので2024-2026年を検索することしか表していない
        capsuleDto.setStartDate(LocalDate.of(2024, 5, 2));
        capsuleDto.setEndDate(LocalDate.of(2026, 12, 31));

        SearchMockTaskPlanResultDto resultDto = switchYearTrialService.practice(capsuleDto);
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "Limitは必ず同値であること");
        assertEquals(1, resultDto.getPageNumber(), "ページ番号に変更がないこと");
        assertEquals(19, resultDto.getAllCount(), "とにかく全件数が全取得数であること");
        List<TaskPlanBaseEntity> list = resultDto.getListTaskPlan();
        assertEquals(4, list.size(), "全件数よりLimitが小さく、ページ番号最末尾でない場合はLimitと一致していること");

        assertEquals(106, list.get(0).getTaskPlanId());
        assertEquals(107, list.get(1).getTaskPlanId());
        assertEquals(202, list.get(2).getTaskPlanId());
        assertEquals(203, list.get(3).getTaskPlanId());
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "SwitchYearTrialServiceTest2024.sql", "SwitchYearTrialServiceTest2025.sql",
            "SwitchYearTrialServiceTest2026.sql" })
    void testSearch_2_3() throws Exception {

        SearchMockTaskPlanCapsuleDto capsuleDto = new SearchMockTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(4);
        capsuleDto.setPageNumber(2);
        // 日時条件は検索には直接使用していないので2024-2026年を検索することしか表していない
        capsuleDto.setStartDate(LocalDate.of(2024, 5, 2));
        capsuleDto.setEndDate(LocalDate.of(2026, 12, 31));

        SearchMockTaskPlanResultDto resultDto = switchYearTrialService.practice(capsuleDto);
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "Limitは必ず同値であること");
        assertEquals(2, resultDto.getPageNumber(), "ページ番号に変更がないこと");
        assertEquals(19, resultDto.getAllCount(), "とにかく全件数が全取得数であること");
        List<TaskPlanBaseEntity> list = resultDto.getListTaskPlan();
        assertEquals(4, list.size(), "全件数よりLimitが小さく、ページ番号最末尾でない場合はLimitと一致していること");

        assertEquals(204, list.get(0).getTaskPlanId());
        assertEquals(205, list.get(1).getTaskPlanId());
        assertEquals(301, list.get(2).getTaskPlanId());
        assertEquals(302, list.get(3).getTaskPlanId());
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "SwitchYearTrialServiceTest2024.sql", "SwitchYearTrialServiceTest2025.sql",
            "SwitchYearTrialServiceTest2026.sql" })
    void testSearch_Boundary() throws Exception {

        SearchMockTaskPlanCapsuleDto capsuleDto = new SearchMockTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(3);
        capsuleDto.setPageNumber(6);
        // 日時条件は検索には直接使用していないので2024-2026年を検索することしか表していない
        capsuleDto.setStartDate(LocalDate.of(2024, 5, 2));
        capsuleDto.setEndDate(LocalDate.of(2026, 12, 31));

        SearchMockTaskPlanResultDto resultDto = switchYearTrialService.practice(capsuleDto);
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "Limitは必ず同値であること");
        assertEquals(6, resultDto.getPageNumber(), "ページ番号に変更がないこと");
        assertEquals(19, resultDto.getAllCount(), "とにかく全件数が全取得数であること");
        List<TaskPlanBaseEntity> list = resultDto.getListTaskPlan();
        assertEquals(1, list.size(), "全件数よりLimitが小さく、ページ番号最末尾でない場合はLimitと一致していること");

        assertEquals(309, list.get(0).getTaskPlanId());

    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "SwitchYearTrialServiceTest2024.sql", "SwitchYearTrialServiceTest2025.sql",
            "SwitchYearTrialServiceTest2026.sql" })
    void testNotSetResult() throws Exception {

        SearchMockTaskPlanCapsuleDto capsuleDto = new SearchMockTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(5);
        capsuleDto.setPageNumber(4);
        // 日時条件は検索には直接使用していないので2024-2026年を検索することしか表していない
        capsuleDto.setStartDate(LocalDate.of(2024, 5, 2));
        capsuleDto.setEndDate(LocalDate.of(2026, 12, 31));

        SearchMockTaskPlanResultDto resultDto = switchYearTrialService.practice(capsuleDto);
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit(), "Limitは必ず同値であること");
        assertEquals(4, resultDto.getPageNumber(), "ページ番号に変更がないこと");
        assertEquals(19, resultDto.getAllCount(), "とにかく全件数が全取得数であること");
        List<TaskPlanBaseEntity> list = resultDto.getListTaskPlan();
        assertEquals(0, list.size(), "検索開始位置が不正なので検索されていないこと");

    }
}
