package mitei.mitei.political.balancesheet.manage.kanrensha.service.year.trial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.TaskPlanBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.paging.SwitchYearCreatePagingConditionLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;

/**
 * 年切替実装試行MockLogic(2026)
 */
@Component
public class MockSearchTaskY2026Logic {

    /** タスク計画検索Repository */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    /** 年切替ページング検索条件作成Logic */
    @Autowired
    private SwitchYearCreatePagingConditionLogic switchYearCreatePagingConditionLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchMockTaskPlanResultDto practice(final SearchMockTaskPlanCapsuleDto capsuleDto) {

        SearchMockTaskPlanResultDto resultDto = new SearchMockTaskPlanResultDto();

        // 件数確認
        int count = taskPlan2026Repository.countAllItems();
        resultDto.setAllCount(capsuleDto.getAllCount() + count);

        // 検索実行に使用するoffsetとlimitを算出
        int offsetExe = switchYearCreatePagingConditionLogic.getOffsetForExecute(capsuleDto, resultDto);
        int limitExe = switchYearCreatePagingConditionLogic.getLimitForExecute(capsuleDto, resultDto);

        if (0 <= offsetExe) {

            List<TaskPlanBaseEntity> list = taskPlan2026Repository.findAllItems(limitExe, offsetExe);
            resultDto.setListTaskPlan(list);
            resultDto.setPreStepViewCount(capsuleDto.getPreStepViewCount() + list.size());
        }

        return resultDto;
    }

}
