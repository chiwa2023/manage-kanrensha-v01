package mitei.mitei.political.balancesheet.manage.kanrensha.service.year.trial;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.SwitchYearPagingIntegerDtoInterface;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.paging.SwitchYearCreatePagingConditionLogic;

/**
 * 年切り替え処理試行Service
 */
@Service
public class SwitchYearTrialService {

    /** 年切替ページング検索条件作成Logic */
    @Autowired
    private SwitchYearCreatePagingConditionLogic switchYearCreatePagingConditionLogic;

    /** 実施年(2024) */
    private static final int YEAR_2024 = 2024;
    /** Mck検索Logic(2024) */
    @Autowired
    private MockSearchTaskY2024Logic mockSearchTaskY2024Logic;

    /** 実施年(2025) */
    private static final int YEAR_2025 = 2025;
    /** Mck検索Logic(2025) */
    @Autowired
    private MockSearchTaskY2025Logic mockSearchTaskY2025Logic;

    /** 実施年(2026) */
    private static final int YEAR_2026 = 2026;
    /** Mck検索Logic(2026) */
    @Autowired
    private MockSearchTaskY2026Logic mockSearchTaskY2026Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchMockTaskPlanResultDto practice(final SearchMockTaskPlanCapsuleDto capsuleDto)
            throws Exception { // NOPMD

        Map<Integer, SwitchYearPagingIntegerDtoInterface> map = switchYearCreatePagingConditionLogic
                .practiceMap(capsuleDto);

        SearchMockTaskPlanResultDto resultDto = new SearchMockTaskPlanResultDto();
        for (Integer year : map.keySet()) {

            switch (year) {
                // 2024年
                case YEAR_2024:
                    switchYearCreatePagingConditionLogic.copyCount(resultDto, capsuleDto);
                    SearchMockTaskPlanResultDto resultDto2024 = mockSearchTaskY2024Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2024.getListTaskPlan());
                    switchYearCreatePagingConditionLogic.copyCount(resultDto2024, resultDto);
                    break;

                // 2025年
                case YEAR_2025:
                    switchYearCreatePagingConditionLogic.copyCount(resultDto, capsuleDto);
                    SearchMockTaskPlanResultDto resultDto2025 = mockSearchTaskY2025Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2025.getListTaskPlan());
                    switchYearCreatePagingConditionLogic.copyCount(resultDto2025, resultDto);
                    break;

                // 2025年
                case YEAR_2026:
                    switchYearCreatePagingConditionLogic.copyCount(resultDto, capsuleDto);
                    SearchMockTaskPlanResultDto resultDto2026 = mockSearchTaskY2026Logic.practice(capsuleDto);
                    resultDto.getListTaskPlan().addAll(resultDto2026.getListTaskPlan());
                    switchYearCreatePagingConditionLogic.copyCount(resultDto2026, resultDto);
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected value: " + year);
            }
        }

        // 最後に初期値のLimit,pageNumberを確実に設定
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());
        return resultDto;
    }
}
