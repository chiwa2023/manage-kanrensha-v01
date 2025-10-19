package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Value;

/**
 * 関連者個人の一意キー取得Dto
 */
@Value
public class PartnerPersonMasterUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param partnerName     個人姓名
     * @param allAddress      全住所
     * @param personShokugyou 個人職業
     */
    public PartnerPersonMasterUniquekeyDto(final String partnerName, final String allAddress,
            final String personShokugyou) {
        super();
        this.partnerName = partnerName;
        this.allAddress = allAddress;
        this.personShokugyou = personShokugyou;
    }

    /** 個人名 */
    @Id
    @Column(name = "partner_name")
    private String partnerName;

    /**
     * 個人名を取得する
     *
     * @return 個人名
     */
    public String getPartnerName() {
        return partnerName;
    }

    /** 個人全住所 */
    @Id
    @Column(name = "all_address")
    private String allAddress;

    /**
     * 個人全住所を取得する
     *
     * @return 個人全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /** 個人代表者 */
    @Id
    @Column(name = "person_shokugyou")
    private String personShokugyou;

    /**
     * 個人職業を取得する
     *
     * @return 個人職業
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

}
