package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 関連者政治団体の一意キー取得Dto
 */
@Entity
public class PartnerPoliOrgMasterUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 政治団体名 */
    @Id
    @Column(name = "partner_name")
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
    @Id
    @Column(name = "all_address")
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
    @Column(name = "poli_org_delegate")
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
}
