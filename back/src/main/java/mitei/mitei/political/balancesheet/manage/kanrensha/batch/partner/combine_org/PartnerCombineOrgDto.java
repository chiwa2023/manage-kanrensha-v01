package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import java.io.Serializable;

/**
 * 個人団体紐づけCsvDto
 */
public class PartnerCombineOrgDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Short) */
    private static final Short INIT_Short = 0;

    /** 紐づけ関連者区分 */
    private Short kanrenshaKbn = INIT_Short;

    /**
     * 紐づけ関連者区分を取得する
     *
     * @return 紐づけ関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 紐づけ関連者区分を設定する
     *
     * @param kanrenshaKbn 紐づけ関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /** 個人関連者コード */
    private String personKanrenshaCode = INIT_String;

    /**
     * 個人関連者コードを取得する
     *
     * @return 個人関連者コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 個人関連者コードを設定する
     *
     * @param personKanrenshaCode 個人関連者コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

    /** 個人氏名 */
    private String personName = INIT_String;

    /**
     * 個人氏名を取得する
     *
     * @return 個人氏名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 個人氏名を設定する
     *
     * @param personName 個人氏名
     */
    public void setPersonName(final String personName) {
        this.personName = personName;
    }

    /** 団体関連者コード */
    private String orgKanrenshaCode = INIT_String;

    /**
     * 団体関連者コードを取得する
     *
     * @return 団体関連者コード
     */
    public String getOrgKanrenshaCode() {
        return orgKanrenshaCode;
    }

    /**
     * 団体関連者コードを設定する
     *
     * @param orgKanrenshaCode 団体関連者コード
     */
    public void setOrgKanrenshaCode(final String orgKanrenshaCode) {
        this.orgKanrenshaCode = orgKanrenshaCode;
    }

    /** 団体代表者名称 */
    private String orgName = INIT_String;

    /**
     * 団体代表者名称を取得する
     *
     * @return 団体代表者名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 団体代表者名称を設定する
     *
     * @param orgName 団体代表者名称
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    /** 登録開始年 */
    private Short startyear = INIT_Short;

    /**
     * 登録開始年を取得する
     *
     * @return 登録開始年
     */
    public Short getStartyear() {
        return startyear;
    }

    /**
     * 登録開始年を設定する
     *
     * @param startyear 登録開始年
     */
    public void setStartyear(final Short startyear) {
        this.startyear = startyear;
    }

    /** 登録終了年 */
    private Short endyear = INIT_Short;

    /**
     * 登録終了年を取得する
     *
     * @return 登録終了年
     */
    public Short getEndyear() {
        return endyear;
    }

    /**
     * 登録終了年を設定する
     *
     * @param endyear 登録終了年
     */
    public void setEndyear(final Short endyear) {
        this.endyear = endyear;
    }

}
