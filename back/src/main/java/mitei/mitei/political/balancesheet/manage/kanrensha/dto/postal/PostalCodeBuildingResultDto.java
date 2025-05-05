package mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionStringDto;

/**
 * 住所建物選択肢Dto
 */
public class PostalCodeBuildingResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 地方自治体コード */
    private String lgCode = INIT_String;

    /** 建物選択肢リスト */
    private List<SelectOptionStringDto> listOptions = new ArrayList<>();

    /**
     * 地方自治体コードを取得する
     *
     * @return 地方自治体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方自治体コードを設定する
     *
     * @param lgCode 地方自治体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /**
     * 建物選択肢リストを取得する
     *
     * @return 建物選択肢リスト
     */
    public List<SelectOptionStringDto> getListOptions() {
        return listOptions;
    }

    /**
     * 建物選択肢リストを設定する
     *
     * @param listOptions 建物選択肢リスト
     */
    public void setListOptions(final List<SelectOptionStringDto> listOptions) {
        this.listOptions = listOptions;
    }

}
