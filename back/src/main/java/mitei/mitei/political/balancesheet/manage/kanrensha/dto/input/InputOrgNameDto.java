package mitei.mitei.political.balancesheet.manage.kanrensha.dto.input;

import java.io.Serializable;

/**
 * 団体名称Dto
 */
public class InputOrgNameDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 団体名かな */
    private String orgNameKana = INIT_String;

    /** 団体名 */
    private String orgName = INIT_String;

    /**
     * 団体名かなを取得する
     *
     * @return 団体名かな
     */
    public String getOrgNameKana() {
        return orgNameKana;
    }

    /**
     * 団体名かなを設定する
     *
     * @param orgNameKana 団体名かな
     */
    public void setOrgNameKana(final String orgNameKana) {
        this.orgNameKana = orgNameKana;
    }

    /**
     * 団体名を取得する
     *
     * @return 団体名
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 団体名を設定する
     *
     * @param orgName 団体名
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

}
