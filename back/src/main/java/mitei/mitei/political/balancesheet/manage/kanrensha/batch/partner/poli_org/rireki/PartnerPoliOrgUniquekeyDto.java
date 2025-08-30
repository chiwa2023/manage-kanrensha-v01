package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Value;

/**
 * 関連者政治団体の一意キー取得Dto
 */
@Value
public class PartnerPoliOrgUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param partnerName          団体名称
     * @param allAddress           全住所
     * @param poliOrgDelegate      団体だ評者
     * @param poliOrgKanrenshaCode 関連者コード政治団体
     */
    public PartnerPoliOrgUniquekeyDto(final String partnerName, final String allAddress, final String poliOrgDelegate,
            final String poliOrgKanrenshaCode) {
        super();
        this.partnerName = partnerName;
        this.allAddress = allAddress;
        this.poliOrgDelegate = poliOrgDelegate;
        this.poliOrgKanrenshaCode = poliOrgKanrenshaCode;
    }

    /** 政治団体名 */
    @Id
    @Column(name = "partner_name")
    private String partnerName;

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
    @Id
    @Column(name = "all_address")
    private String allAddress;

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
    @Id
    @Column(name = "poli_org_delegate")
    private String poliOrgDelegate;

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
    @Id
    @Column(name = "poli_org_kanrensha_code")
    private String poliOrgKanrenshaCode;

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

}
