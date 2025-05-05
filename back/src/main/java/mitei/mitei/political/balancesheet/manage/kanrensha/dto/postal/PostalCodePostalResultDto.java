package mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionNumberDto;

/**
 * 住所郵便番号まで選択肢Dto
 */
public class PostalCodePostalResultDto implements Serializable{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;
    
    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 行政区検索 */
    private Boolean isGyouseikuData = INIT_Boolean;
    
    /** 行政区検索 */
    private List<SelectOptionNumberDto> listOptions = new ArrayList<>();


    /**
     * 検索行政区該否を取得する
     *
     * @return 検索行政区該否
     */
    public Boolean getIsGyouseikuData() {
        return isGyouseikuData;
    }

    /**
     * 検索行政区該否を設定する
     *
     * @param isGyouseikuData 検索行政区該否
     */
    public void setIsGyouseikuData(final Boolean isGyouseikuData) {
        this.isGyouseikuData = isGyouseikuData;
    }

    /**
     * 建物選択肢リストを取得する
     *
     * @return 建物選択肢リスト
     */
    public List<SelectOptionNumberDto> getListOptions() {
        return listOptions;
    }

    /**
     * 建物選択肢リストを設定する
     *
     * @param listOptions 建物選択肢リスト
     */
    public void setListOptions(final List<SelectOptionNumberDto> listOptions) {
        this.listOptions = listOptions;
    }

}
