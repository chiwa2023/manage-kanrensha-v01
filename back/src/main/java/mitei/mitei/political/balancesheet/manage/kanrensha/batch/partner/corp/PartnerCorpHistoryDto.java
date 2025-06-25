package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp;

import java.io.Serializable;

/**
 * 関連者企業・団体履歴Dto
 */
public class PartnerCorpHistoryDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 企業・団体名 */
    private String partnerName = INIT_String;

    /**
     * 企業・団体名を取得する
     *
     * @return 企業・団体名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 企業・団体名を設定する
     *
     * @param partnerName 企業・団体名
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 企業・団体全住所 */
    private String allAddress = INIT_String;

    /**
     * 企業・団体全住所を取得する
     *
     * @return 企業・団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 企業・団体全住所を設定する
     *
     * @param allAddress 企業・団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 企業・団体代表者 */
    private String corpDelegate = INIT_String;

    /**
     * 企業・団体代表者を取得する
     *
     * @return 企業・団体代表者
     */
    public String getCorpDelegate() {
        return corpDelegate;
    }

    /**
     * 企業・団体代表者を設定する
     *
     * @param corpDelegate 企業・団体代表者
     */
    public void setCorpDelegate(final String corpDelegate) {
        this.corpDelegate = corpDelegate;
    }

    /** 企業・団体関連者コード */
    private String corpKanrenshaCode = INIT_String;

    /**
     * 企業・団体関連者コードを取得する
     *
     * @return 企業・団体関連者コード
     */
    public String getCorpKanrenshaCode() {
        return corpKanrenshaCode;
    }

    /**
     * 企業・団体関連者コードを設定する
     *
     * @param corpKanrenshaCode 企業・団体関連者コード
     */
    public void setCorpKanrenshaCode(final String corpKanrenshaCode) {
        this.corpKanrenshaCode = corpKanrenshaCode;
    }

}
