package mitei.mitei.political.balancesheet.manage.kanrensha.logic.paging;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.SwitchYearPagingIntegerDtoInterface;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.trial.SearchMockTaskPlanCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.trial.SearchMockTaskPlanResultDto;

/**
 * SwitchYearCreatePagingConditionLogic単体テスト
 */
class SwitchYearCreatePagingConditionLogicTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate") // NOPMD
    void testMap() throws Exception {

        SwitchYearCreatePagingConditionLogic logic = new SwitchYearCreatePagingConditionLogic();

        SearchMockTaskPlanCapsuleDto capsuleDto0 = this.createCapsuleDto();
        Map<Integer, SwitchYearPagingIntegerDtoInterface> map0 = logic.practiceMap(capsuleDto0);

        assertEquals(3, map0.size(), "2024,2025,2026年の3年分作成されていること");

        SwitchYearPagingIntegerDtoInterface capsuleDto01 = map0.get(2024);
        assertEquals(capsuleDto0.getStartDate(), capsuleDto01.getStartDate(), "開始日時は入力と一致していること");
        assertEquals(LocalDate.of(2024, 12, 31), capsuleDto01.getEndDate(), "年をまたいでいる場合は同年末日であること"); // NOPMD

        SwitchYearPagingIntegerDtoInterface capsuleDto02 = map0.get(2025);
        assertEquals(LocalDate.of(2025, 1, 1), capsuleDto02.getStartDate(), "年をまたいでいる場合は同年末日であること");
        assertEquals(LocalDate.of(2025, 12, 31), capsuleDto02.getEndDate(), "年をまたいでいる場合は同年末日であること");

        SwitchYearPagingIntegerDtoInterface capsuleDto03 = map0.get(2026);
        assertEquals(LocalDate.of(2026, 1, 1), capsuleDto03.getStartDate(), "年をまたいでいる場合は同年末日であること");
        assertEquals(capsuleDto0.getEndDate(), capsuleDto03.getEndDate(), "終了日時は入力と一致していること");

        assertTrue(capsuleDto01 instanceof SearchMockTaskPlanCapsuleDto, "生成されたインスタンスは入力と同じクラスであるであること");
        assertTrue(capsuleDto02 instanceof SearchMockTaskPlanCapsuleDto, "生成されたインスタンスは入力と同じクラスであるであること");
        assertTrue(capsuleDto03 instanceof SearchMockTaskPlanCapsuleDto, "生成されたインスタンスは入力と同じクラスであるであること");

        SearchMockTaskPlanCapsuleDto capsuleDto04 = (SearchMockTaskPlanCapsuleDto) capsuleDto01;
        assertEquals(capsuleDto0.getSearchTaskWord(), capsuleDto04.getSearchTaskWord(), "Interfaceでないフィールドも複写されていること");

        SearchMockTaskPlanCapsuleDto capsuleDto1 = this.createCapsuleDto();
        capsuleDto1.setStartDate(null);
        capsuleDto1.setEndDate(null);
        assertThrows(NullPointerException.class, () -> logic.practiceMap(capsuleDto1),
                "interface実装は常に初期値を生成するので基本的に起きないが例外として処理中断(実装ミス)");

        SearchMockTaskPlanCapsuleDto capsuleDto2 = this.createCapsuleDto();
        capsuleDto2.setStartDate(LocalDate.of(2026, 4, 1));
        capsuleDto2.setEndDate(LocalDate.of(2024, 10, 31));
        Map<Integer, SwitchYearPagingIntegerDtoInterface> map2 = logic.practiceMap(capsuleDto2);
        SwitchYearPagingIntegerDtoInterface capsuleDto21 = map2.get(2024);
        assertEquals(LocalDate.of(2024, 10, 31), capsuleDto21.getStartDate(), "開始日時の補正がされていること");
        SwitchYearPagingIntegerDtoInterface capsuleDto23 = map2.get(2026);
        assertEquals(LocalDate.of(2026, 4, 1), capsuleDto23.getEndDate(), "終了日時の補正がされていること");

        SearchMockTaskPlanCapsuleDto capsuleDto3 = this.createCapsuleDto();
        capsuleDto3.setPageNumber(9);
        capsuleDto3.setIsChangedCondition(true);
        Map<Integer, SwitchYearPagingIntegerDtoInterface> map3 = logic.practiceMap(capsuleDto2);
        assertEquals(0, map3.get(2025).getPageNumber(), "検索条件が変更されているときはページ番号が初期化されていること");
    }

    @Test
    @Tag("TableTruncate")
    void testCopyCount() throws Exception {

        SwitchYearCreatePagingConditionLogic logic = new SwitchYearCreatePagingConditionLogic();

        SearchMockTaskPlanCapsuleDto capsuleDto = this.createCapsuleDto();
        SearchMockTaskPlanResultDto resultDto = new SearchMockTaskPlanResultDto();

        logic.copyCount(capsuleDto, resultDto);

        // 全件数と表示件数が複写されていること
        assertEquals(capsuleDto.getAllCount(), resultDto.getAllCount());
        assertEquals(capsuleDto.getPreStepViewCount(), resultDto.getPreStepViewCount());

        // 該当フィールド以外は複写されていないこと
        assertNotEquals(capsuleDto.getLimit(), resultDto.getLimit());
    }

    @Test
    @Tag("TableTruncate")
    void testLimit() throws Exception {

        SwitchYearCreatePagingConditionLogic logic = new SwitchYearCreatePagingConditionLogic();

        SearchMockTaskPlanCapsuleDto capsuleDto0 = this.createCapsuleDto();
        capsuleDto0.setAllCount(0);
        capsuleDto0.setLimit(50);
        capsuleDto0.setPageNumber(0);
        capsuleDto0.setPreStepViewCount(0);
        SearchMockTaskPlanResultDto resultDto0 = new SearchMockTaskPlanResultDto();
        resultDto0.setAllCount(10);
        resultDto0.setPreStepViewCount(0);

        assertEquals(50, logic.getLimitForExecute(capsuleDto0, resultDto0),
                "初期表示時にLimitが変わることはないし、検索数に対しオーバーすることにも何の問題もない");

        SearchMockTaskPlanCapsuleDto capsuleDto1 = this.createCapsuleDto();
        capsuleDto1.setAllCount(4);
        capsuleDto1.setLimit(10);
        capsuleDto1.setPageNumber(0);
        capsuleDto1.setPreStepViewCount(4);
        SearchMockTaskPlanResultDto resultDto1 = new SearchMockTaskPlanResultDto();
        resultDto1.setAllCount(100);
        assertEquals(6, logic.getLimitForExecute(capsuleDto1, resultDto1),
                "初回に一部が表示済で、今回検索数がページ件数より十分に大きい場合は取得しすぎないよう制御が必要");
    }

    @Test
    @Tag("TableTruncate")
    void testOffset() throws Exception {

        SwitchYearCreatePagingConditionLogic logic = new SwitchYearCreatePagingConditionLogic();

        SearchMockTaskPlanCapsuleDto capsuleDto0 = new SearchMockTaskPlanCapsuleDto();
        capsuleDto0.setAllCount(0);
        capsuleDto0.setLimit(50);
        capsuleDto0.setPageNumber(0);
        SearchMockTaskPlanResultDto resultDto0 = new SearchMockTaskPlanResultDto();
        resultDto0.setAllCount(10);
        resultDto0.setPreStepViewCount(0);
        assertEquals(0, logic.getOffsetForExecute(capsuleDto0, resultDto0), "ページ件数50で初回検索");

        SearchMockTaskPlanCapsuleDto capsuleDto1 = new SearchMockTaskPlanCapsuleDto();
        capsuleDto1.setAllCount(2);
        capsuleDto1.setLimit(50);
        capsuleDto1.setPageNumber(0);
        capsuleDto1.setPreStepViewCount(2);
        SearchMockTaskPlanResultDto resultDto1 = new SearchMockTaskPlanResultDto();
        resultDto1.setAllCount(10);
        assertEquals(0, logic.getOffsetForExecute(capsuleDto1, resultDto1), "ページ件数50で初回検索で初年度は2件見つかった場合、該当年度は最初から");

        SearchMockTaskPlanCapsuleDto capsuleDto2 = new SearchMockTaskPlanCapsuleDto();
        capsuleDto2.setAllCount(100);
        capsuleDto2.setLimit(50);
        capsuleDto2.setPageNumber(0);
        capsuleDto2.setPreStepViewCount(100);
        SearchMockTaskPlanResultDto resultDto2 = new SearchMockTaskPlanResultDto();
        resultDto2.setAllCount(150);
        resultDto2.setPreStepViewCount(100);
        assertEquals(-1, logic.getOffsetForExecute(capsuleDto2, resultDto2), "ページ件数50で初回検索で初年度は100件見つかった場合、検索はしない");

        SearchMockTaskPlanCapsuleDto capsuleDto3 = new SearchMockTaskPlanCapsuleDto();
        capsuleDto3.setAllCount(0);
        capsuleDto3.setLimit(5);
        capsuleDto3.setPageNumber(1);
        capsuleDto3.setPreStepViewCount(0);
        SearchMockTaskPlanResultDto resultDto3 = new SearchMockTaskPlanResultDto();
        resultDto3.setAllCount(100);
        resultDto3.setPreStepViewCount(0);
        assertEquals(5, logic.getOffsetForExecute(capsuleDto3, resultDto3),
                "ページング件数が小さく、初回検索結果内に複数ページが含まれる場合、初回はページ番号×ページ件数");

    }

    private SearchMockTaskPlanCapsuleDto createCapsuleDto() {

        SearchMockTaskPlanCapsuleDto capsuleDto = new SearchMockTaskPlanCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(50);
        capsuleDto.setPageNumber(0);
        // 日時条件は検索には直接使用していないので2024-2026年を検索することしか表していない
        capsuleDto.setStartDate(LocalDate.of(2024, 5, 2));
        capsuleDto.setEndDate(LocalDate.of(2026, 12, 31));
        capsuleDto.setSearchTaskWord("検索語");

        return capsuleDto;
    }

}
