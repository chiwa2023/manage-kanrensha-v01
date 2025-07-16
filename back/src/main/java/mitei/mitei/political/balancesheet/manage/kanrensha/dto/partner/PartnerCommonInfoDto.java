package mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 関連者個人・企業・政治団体共通Dto
 */
@Entity
public class PartnerCommonInfoDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 関連者区分 */
    @Id
    @Column(name = "kanrensha_Kbn")
    private Integer kanrenshaKbn = INIT_Integer;

    /**
     * 関連者区分を取得する
     *
     * @return 関連者区分
     */
    public Integer getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 関連者区分を設定する
     *
     * @param kanrenshaKbn 関連者区分
     */
    public void setKanrenshaKbn(final Integer kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /** 関連者名称 */
    @Column(name = "partner_name")
    private String partnerName = INIT_String;

    /**
     * 関連者名称を取得する
     *
     * @return 関連者名称
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * 関連者名称を設定する
     *
     * @param partnerName 関連者名称
     */
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }

    /** 関連者全住所 */
    @Id
    @Column(name = "all_address")
    private String allAddress = INIT_String;

    /**
     * 関連者全住所を取得する
     *
     * @return 関連者全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 関連者全住所を設定する
     *
     * @param allAddress 関連者全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 認識キー */
    @Id
    @Column(name = "recognized_Key")
    private String recognizedKey = INIT_String;

    /**
     * 認識キーを取得する
     *
     * @return 認識キー
     */
    public String getRecognizedKey() {
        return recognizedKey;
    }

    /**
     * 認識キーを設定する
     *
     * @param recognizedKey 認識キー
     */
    public void setRecognizedKey(final String recognizedKey) {
        this.recognizedKey = recognizedKey;
    }

    /** 関連者コード */
    @Id
    @Column(name = "kanrensha_code")
    private String kanrenshaCode = INIT_String;

    /**
     * 関連者コードを取得する
     *
     * @return 関連者コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 関連者コードを設定する
     *
     * @param kanrenshaCode 関連者コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

}
