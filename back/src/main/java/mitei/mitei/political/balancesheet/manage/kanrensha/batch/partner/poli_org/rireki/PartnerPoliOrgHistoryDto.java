package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import java.io.Serializable;

/**
 * 関連者政治団体履歴Dto
 */
public class PartnerPoliOrgHistoryDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 政治団体名 */
    private String partnerName = INIT_String;

    /**
     * 政治団体名を取得する
     *
     * @return 政治団体名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 政治団体名を設定する
     *
     * @param partnerName 政治団体名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 政治団体全住所 */
    private String allAddress = INIT_String;

    /**
     * 政治団体全住所を取得する
     *
     * @return 政治団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 政治団体全住所を設定する
     *
     * @param allAddress 政治団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 政治団体代表者 */
    private String poliOrgDelegate = INIT_String;

    /**
     * 政治団体代表者を取得する
     *
     * @return 政治団体代表者
     */
    public String getPoliOrgDelegate() {
        return poliOrgDelegate;
    }

    /**
     * 政治団体代表者を設定する
     *
     * @param poliOrgDelegate 政治団体代表者
     */
    public void setPoliOrgDelegate(final String poliOrgDelegate) {
        this.poliOrgDelegate = poliOrgDelegate;
    }

    /** 政治団体関連者コード */
    private String poliOrgKanrenshaCode = INIT_String;

    /**
     * 政治団体関連者コードを取得する
     *
     * @return 政治団体関連者コード
     */
    public String getPoliOrgKanrenshaCode() {
        return poliOrgKanrenshaCode;
    }

    /**
     * 政治団体関連者コードを設定する
     *
     * @param poliOrgKanrenshaCode 政治団体関連者コード
     */
    public void setPoliOrgKanrenshaCode(final String poliOrgKanrenshaCode) {
        this.poliOrgKanrenshaCode = poliOrgKanrenshaCode;
    }

    /** 団体代表者関連者コード */
    private String orgDelegateCode = INIT_String;

    /**
     * 団体代表者関連者コードを取得する
     *
     * @return 団体代表者関連者コード
     */
    public String getOrgDelegateCode() {
        return orgDelegateCode;
    }

    /**
     * 団体代表者関連者コードを設定する
     *
     * @param orgDelegateCode 団体代表者関連者コード
     */
    public void setOrgDelegateCode(final String orgDelegateCode) {
        this.orgDelegateCode = orgDelegateCode;
    }


}
