package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Value;

/**
 * 関連者企業・団体の一意キー取得Dto
 */
@Value
public class PartnerCorpUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param partnerName       企業団体名称
     * @param allAddress        全住所
     * @param corpDelegate      団体代表者
     * @param corpKanrenshaCode 関連者コード
     */
    public PartnerCorpUniquekeyDto(final String partnerName, final String allAddress, final String corpDelegate,
            final String corpKanrenshaCode) {
        super();
        this.partnerName = partnerName;
        this.allAddress = allAddress;
        this.corpDelegate = corpDelegate;
        this.corpKanrenshaCode = corpKanrenshaCode;
    }

    /** 企業・団体名 */
    @Id
    @Column(name = "partner_name")
    private String partnerName;

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
    @Id
    @Column(name = "all_address")
    private String allAddress;

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
    @Id
    @Column(name = "corp_delegate")
    private String corpDelegate;

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
    @Id
    @Column(name = "corp_kanrensha_code")
    private String corpKanrenshaCode;

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
