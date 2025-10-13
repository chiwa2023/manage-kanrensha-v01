package mitei.mitei.political.balancesheet.manage.kanrensha.logic.paging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.PeriodDatetimeCapsuleDtoInterface;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;

/**
 * CreateSearchConditionMapByYearLogic単体テスト
 */
class CreateSearchConditionMapByYearLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    void test() throws Exception {// NOPMD NCSS

        SearchTaskPlanCapsuleDto capsuleDto = new SearchTaskPlanCapsuleDto();
        capsuleDto.setSearchTaskWord("検索語");
        capsuleDto.setStartDate(LocalDateTime.of(2022, 12, 5, 12, 34, 56));
        capsuleDto.setEndDate(LocalDateTime.of(2026, 4, 9, 22, 45, 1));
        capsuleDto.setAllCount(163);
        capsuleDto.setLimit(50);
        capsuleDto.setAllCount(1);

        CreateSearchConditionMapByYearLogic logic = new CreateSearchConditionMapByYearLogic();

        Map<Integer, PeriodDatetimeCapsuleDtoInterface> map = logic.practice(capsuleDto);

        assertEquals(5, map.size());

        Set<Integer> listKey = map.keySet();
        assertTrue(listKey.contains(2022));
        assertTrue(listKey.contains(2023));
        assertTrue(listKey.contains(2024));
        assertTrue(listKey.contains(2025));
        assertTrue(listKey.contains(2026));

        PeriodDatetimeCapsuleDtoInterface interface0 = map.get(2022);
        if (interface0 instanceof SearchTaskPlanCapsuleDto) {
            SearchTaskPlanCapsuleDto dto0 = (SearchTaskPlanCapsuleDto) interface0;

            // その他のフィールドは複写
            assertEquals(capsuleDto.getAllCount(), dto0.getAllCount());
            assertEquals(capsuleDto.getPageNumber(), dto0.getPageNumber());
            assertEquals(capsuleDto.getLimit(), dto0.getLimit());
            assertEquals(capsuleDto.getSearchTaskWord(), dto0.getSearchTaskWord());

            // 開始年は検索条件と同一
            LocalDateTime start = dto0.getStartDate();
            assertEquals(capsuleDto.getStartDate().getYear(), start.getYear());
            assertEquals(capsuleDto.getStartDate().getMonthValue(), start.getMonthValue());
            assertEquals(capsuleDto.getStartDate().getDayOfMonth(), start.getDayOfMonth());
            assertEquals(capsuleDto.getStartDate().getHour(), start.getHour());
            assertEquals(capsuleDto.getStartDate().getMinute(), start.getMinute());
            assertEquals(capsuleDto.getStartDate().getSecond(), start.getSecond());

            // 年をまたぐので年末
            LocalDateTime end = dto0.getEndDate();
            assertEquals(start.getYear(), end.getYear());
            assertEquals(12, end.getMonthValue());
            assertEquals(31, end.getDayOfMonth());
            assertEquals(23, end.getHour());
            assertEquals(59, end.getMinute());
            assertEquals(59, end.getSecond());
        } else {
            fail("cast失敗"); // NOPMD
        }

        PeriodDatetimeCapsuleDtoInterface interface1 = map.get(2026);
        if (interface1 instanceof SearchTaskPlanCapsuleDto) {
            SearchTaskPlanCapsuleDto dto1 = (SearchTaskPlanCapsuleDto) interface1;

            // その他のフィールドは複写
            assertEquals(capsuleDto.getAllCount(), dto1.getAllCount());
            assertEquals(capsuleDto.getPageNumber(), dto1.getPageNumber());
            assertEquals(capsuleDto.getLimit(), dto1.getLimit());
            assertEquals(capsuleDto.getSearchTaskWord(), dto1.getSearchTaskWord());

            // 終了年は検索終了日は検索条件と同一
            LocalDateTime end = dto1.getEndDate();
            assertEquals(capsuleDto.getEndDate().getYear(), end.getYear());
            assertEquals(capsuleDto.getEndDate().getMonthValue(), end.getMonthValue());
            assertEquals(capsuleDto.getEndDate().getDayOfMonth(), end.getDayOfMonth());
            assertEquals(capsuleDto.getEndDate().getHour(), end.getHour());
            assertEquals(capsuleDto.getEndDate().getMinute(), end.getMinute());
            assertEquals(capsuleDto.getEndDate().getSecond(), end.getSecond());

            // 終了年の検索開始日は年始
            LocalDateTime start = dto1.getStartDate();
            assertEquals(start.getYear(), start.getYear());
            assertEquals(1, start.getMonthValue());
            assertEquals(1, start.getDayOfMonth());
            assertEquals(0, start.getHour());
            assertEquals(0, start.getMinute());
            assertEquals(0, start.getSecond());

        } else {
            fail("cast失敗");
        }

        PeriodDatetimeCapsuleDtoInterface interface2 = map.get(2023);
        if (interface2 instanceof SearchTaskPlanCapsuleDto) {
            SearchTaskPlanCapsuleDto dto2 = (SearchTaskPlanCapsuleDto) interface2;

            // その他のフィールドは複写
            assertEquals(capsuleDto.getAllCount(), dto2.getAllCount());
            assertEquals(capsuleDto.getPageNumber(), dto2.getPageNumber());
            assertEquals(capsuleDto.getLimit(), dto2.getLimit());
            assertEquals(capsuleDto.getSearchTaskWord(), dto2.getSearchTaskWord());

            // 終了年の検索開始日は年始
            LocalDateTime start = dto2.getStartDate();
            assertEquals(start.getYear(), start.getYear());
            assertEquals(1, start.getMonthValue());
            assertEquals(1, start.getDayOfMonth());
            assertEquals(0, start.getHour());
            assertEquals(0, start.getMinute());
            assertEquals(0, start.getSecond());

            // 年をまたぐので年末
            LocalDateTime end = dto2.getEndDate();
            assertEquals(start.getYear(), end.getYear());
            assertEquals(12, end.getMonthValue());
            assertEquals(31, end.getDayOfMonth());
            assertEquals(23, end.getHour());
            assertEquals(59, end.getMinute());
            assertEquals(59, end.getSecond());

        } else {
            fail("cast失敗");
        }
    }

    @Test
    void testSameYear() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto = new SearchTaskPlanCapsuleDto();
        capsuleDto.setSearchTaskWord("検索語");
        capsuleDto.setStartDate(LocalDateTime.of(2023, 5, 6, 7, 8, 9));
        capsuleDto.setEndDate(LocalDateTime.of(2023, 7, 8, 9, 0, 2));
        capsuleDto.setAllCount(163);
        capsuleDto.setLimit(50);
        capsuleDto.setAllCount(1);

        CreateSearchConditionMapByYearLogic logic = new CreateSearchConditionMapByYearLogic();

        Map<Integer, PeriodDatetimeCapsuleDtoInterface> map = logic.practice(capsuleDto);

        assertEquals(1, map.size());
        assertTrue(map.keySet().contains(2023));

        PeriodDatetimeCapsuleDtoInterface interface0 = map.get(2023);
        if (interface0 instanceof SearchTaskPlanCapsuleDto) {
            SearchTaskPlanCapsuleDto dto0 = (SearchTaskPlanCapsuleDto) interface0;

            // 年をまたがない場合は完全に同一の条件が返る
            assertEquals(capsuleDto.getAllCount(), dto0.getAllCount());
            assertEquals(capsuleDto.getPageNumber(), dto0.getPageNumber());
            assertEquals(capsuleDto.getLimit(), dto0.getLimit());
            assertEquals(capsuleDto.getSearchTaskWord(), dto0.getSearchTaskWord());
            assertTrue(capsuleDto.getStartDate().isEqual(dto0.getStartDate()));
            assertTrue(capsuleDto.getEndDate().isEqual(dto0.getEndDate()));

        } else {
            fail("cast失敗");
        }
    }
}
