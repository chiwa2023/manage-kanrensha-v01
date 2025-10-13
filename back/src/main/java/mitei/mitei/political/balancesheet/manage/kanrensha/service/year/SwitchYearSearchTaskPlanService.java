package mitei.mitei.political.balancesheet.manage.kanrensha.service.year;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.PeriodDatetimeCapsuleDtoInterface;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.paging.CreateSearchConditionMapByYearLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.y2025.SearchTaskPlanY2025Logic;

/**
 * 年切替タスク計画検索Service
 */
@Service
public class SwitchYearSearchTaskPlanService {

    /** 検索条件年展開Logic */
    @Autowired
    private CreateSearchConditionMapByYearLogic createSearchConditionMapByYearLogic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** タスク計画検索Logic(2025) */
    @Autowired
    private SearchTaskPlanY2025Logic searchTaskPlanY2025Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     * @throws InvocationTargetException reflection例外(検索条件年展開)
     * @throws IllegalAccessException    reflection例外(検索条件年展開)
     * @throws InstantiationException    reflection例外(検索条件年展開)
     * @throws NoSuchMethodException     reflection例外(検索条件年展開)
     */
    public SearchTaskPlanResultDto practice(final SearchTaskPlanCapsuleDto capsuleDto)
            throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        Map<Integer, PeriodDatetimeCapsuleDtoInterface> map = createSearchConditionMapByYearLogic.practice(capsuleDto);

        SearchTaskPlanResultDto resultDto = new SearchTaskPlanResultDto();
        for (Integer year : map.keySet()) {

            switch (year) {
                // 2025年
                case YEAR_2025:
                    SearchTaskPlanResultDto resultDto2025 = searchTaskPlanY2025Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2025.getListTaskPlan());
                    resultDto.setLimit(resultDto2025.getLimit());
                    resultDto.setAllCount(resultDto2025.getAllCount());
                    resultDto.setPageNumber(resultDto2025.getPageNumber());
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected value: " + year);
            }
        }

        return resultDto;
    }

}
