package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import java.io.Serializable;

/**
 * 関連者個人履歴Dto
 */
public class PartnerPersonHistoryDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 個人名 */
    private String partnerName = INIT_String;

    /**
     * 個人名を取得する
     *
     * @return 個人名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 個人名を設定する
     *
     * @param partnerName 個人名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 個人全住所 */
    private String allAddress = INIT_String;

    /**
     * 個人全住所を取得する
     *
     * @return 個人全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 個人全住所を設定する
     *
     * @param allAddress 個人全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 個人職業 */
    private String personShokugyou = INIT_String;

    /** 個人関連者コード */
    private String personKanrenshaCode = INIT_String;

    /**
     * 個人職業を取得する
     *
     * @return 個人職業
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

    /**
     * 個人職業を設定する
     *
     * @param personShokugyou 個人職業
     */
    public void setPersonShokugyou(final String personShokugyou) {
        this.personShokugyou = personShokugyou;
    }

    /**
     * 個人関連者コードを取得する
     *
     * @return 個人関連者コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 個人関連者コード設定する
     *
     * @param personKanrenshaCode 個人関連者コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

}
