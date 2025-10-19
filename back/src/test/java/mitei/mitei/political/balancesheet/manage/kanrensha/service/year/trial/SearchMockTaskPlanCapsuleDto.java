package mitei.mitei.political.balancesheet.manage.kanrensha.service.year.trial;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.FrameworkSwitchYearPagingIntegerDto;

/**
 * タスク計画検索条件Dto
 */
public class SearchMockTaskPlanCapsuleDto extends FrameworkSwitchYearPagingIntegerDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** タスク検索語 */
    private String searchTaskWord = INIT_String;

    /**
     * タスク検索語
     *
     * @return タスク検索語を取得する
     */
    public String getSearchTaskWord() {
        return searchTaskWord;
    }

    /**
     * タスク検索語を設定する
     *
     * @param searchTaskWord タスク検索語
     */
    public void setSearchTaskWord(final String searchTaskWord) {
        this.searchTaskWord = searchTaskWord;
    }

}
