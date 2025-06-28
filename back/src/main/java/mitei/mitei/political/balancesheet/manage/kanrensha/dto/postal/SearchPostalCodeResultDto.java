package mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.AbstractPagingIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.MessagAndResultInterface;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;

/**
 * 郵便番号データ検索結果Dto
 */
public class SearchPostalCodeResultDto extends AbstractPagingIntegerDto // NOPMD DataClass
        implements Serializable, MessagAndResultInterface {

    /** 郵便番号リスト */
    private List<AddressPostalEntity> listItem = new ArrayList<>();

    /**
     * 郵便番号リストを取得する
     *
     * @return 郵便番号リスト
     */
    public List<AddressPostalEntity> getListItem() {
        return listItem;
    }

    /**
     * 郵便番号リストを設定する
     *
     * @param listItem 郵便番号リスト
     */
    public void setListItem(final List<AddressPostalEntity> listItem) {
        this.listItem = listItem;
    }

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** 抽出件数 */
    private Integer limit = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * 抽出件数を取得する
     *
     * @return 抽出件数
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * 抽出件数を設定する
     *
     * @param limit 抽出件数
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** 出力メッセージ */
    private String message = INIT_String;

    /** 処理失敗フラグ */
    private Boolean isFailure = INIT_Boolean;

    /**
     * メッセージを取得する
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * メッセージを設定
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 処理結果失敗フラグを取得する
     */
    @Override
    public Boolean getIsFailure() {
        return isFailure;
    }

    /**
     * 処理結果失敗フラグを設定する
     */
    @Override
    public void setIsFailure(final Boolean isFailure) {
        this.isFailure = isFailure;
    }
}
