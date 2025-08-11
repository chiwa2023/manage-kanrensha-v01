package mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;

/**
 * XMLからマスタ登録ワークテーブル検索ページングDto
 */
public class SearchWkTbPagingCapsuleDto extends AbstractPagingIntegerDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** ページ内件数 */
    private Integer limit = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

    /**
     * 全件数を取得する
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * ページ内件数を取得する
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * ページ内件数を設定する
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** 履歴データ検索結果保持 */
    private Boolean hasHistorry = INIT_Boolean;

    /** 終了データ検索結果保持 */
    private Boolean hasFinished = INIT_Boolean;

    /** 無影響検索結果保持 */
    private Boolean hasAffectNot = INIT_Boolean;

    /** ユーザ最小限Dto */
    private UserPersonLeastDto userLeast = new UserPersonLeastDto();

    /**
     * 履歴データ検索結果保持
     *
     * @return 履歴データ検索結果保持
     */
    public Boolean getHasHistorry() {
        return hasHistorry;
    }

    /**
     * 履歴データ検索結果保持
     *
     * @param hasHistorry 履歴データ検索結果保持
     */
    public void setHasHistorry(final Boolean hasHistorry) {
        this.hasHistorry = hasHistorry;
    }

    /**
     * 終了データ検索結果保持
     *
     * @return 終了データ検索結果保持
     */
    public Boolean getHasFinished() {
        return hasFinished;
    }

    /**
     * 終了データ検索結果保持
     *
     * @param hasFinished 終了データ検索結果保持
     */
    public void setHasFinished(final Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    /**
     * 無影響検索結果保持
     *
     * @return 無影響検索結果保持
     */
    public Boolean getHasAffectNot() {
        return hasAffectNot;
    }

    /**
     * 無影響検索結果保持
     *
     * @param hasAffectNot 無影響検索結果保持
     */
    public void setHasAffectNot(final Boolean hasAffectNot) {
        this.hasAffectNot = hasAffectNot;
    }

    /**
     * ユーザ最小限Dto
     *
     * @return ユーザ最小限Dto
     */
    public UserPersonLeastDto getUserLeast() {
        return userLeast;
    }

    /**
     * ユーザ最小限Dto
     *
     * @param userLeast ユーザ最小限Dto
     */
    public void setUserLeast(final UserPersonLeastDto userLeast) {
        this.userLeast = userLeast;
    }

}
