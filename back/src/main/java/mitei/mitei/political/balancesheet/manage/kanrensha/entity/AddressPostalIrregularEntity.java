package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 郵便番号不規則データEntity
 *
 * <p>
 * 不規則とは 1.データ内(１丁目～１０丁目)または(地名A,地名B)のように複数項目が想定されているものを指します。
 * 基本的には１郵便番号=1住所ではあるのですが、その原則からはずれているため不規則としています。
 * この場合、他郵便番号では選択肢はブランクと抽出された住所だけの２項目ですが、この郵便番号では抽出された選択肢を選択する必要があります ex.
 * 郵便番号1234567,和歌山県架空市山麓町（１丁目～３丁目）※元データ ex. 郵便番号1234567,和歌山県架空市山麓町１丁目※元データの展開データ
 * 郵便番号1234567,和歌山県架空市山麓町２丁目※元データの展開データ 郵便番号1234567,和歌山県架空市山麓町３丁目※元データの展開データ
 * 注記：このタイプのデータは上記のように展開された時点で不規則でないデータとしてこのテーブルから削除されます。TODO 移行処理バッチの名前を明記
 * </p>
 *
 * <p>
 * 2. 巨大ビルで建物階位ごとに異なる郵便番号が割り振られていえるもの ex. 郵便番号9876540,東京都ほにゃららビル（地下階）
 * 郵便番号9876541,東京都ほにゃららビル（１階） 郵便番号9876542,東京都ほにゃららビル（２階）
 * 同じ建物＝同じ住所であるため、各住居テーブルにアクセスすると、無用なデータが多数抽出されるため、不規則テーブルから一意の住所を返した方が処理として妥当です。
 * </p>
 */
@Entity
@Table(name = "address_postal_irregular")
public class AddressPostalIrregularEntity implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_postal_irregular_id")
    private Integer addressPostalIrregularId = INIT_Integer;

    /** 郵便番号1 */
    @Column(name = "postal1")
    private String postal1 = INIT_String;

    /** 郵便番号2 */
    @Column(name = "postal2")
    private String postal2 = INIT_String;

    /** 地方公共団体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

    /** 原文書(郵便番号csv)住所 */
    @Column(name = "address_org")
    private String addressOrg = INIT_String;

    /** 住所名 */
    @Column(name = "address_name")
    private String addressName = INIT_String;

    /** 住所郵便番号まで */
    @Column(name = "address_postal")
    private String addressPostal = INIT_String;

    /** 郵便番号番地まで */
    @Column(name = "address_block")
    private String addressBlock = INIT_String;

    /** 正規テーブル複写処理完了 */
    @Column(name = "is_add_postal")
    private Boolean isAddPostal = INIT_Boolean;

    /** 住居テーブル修正完了 */
    @Column(name = "is_repair_rsdt")
    private Boolean isRepairRsdt = INIT_Boolean;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getAddressPostalIrregularId() {
        return addressPostalIrregularId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param addressPostalIrregularId テーブルId
     */
    public void setAddressPostalIrregularId(final Integer addressPostalIrregularId) {
        this.addressPostalIrregularId = addressPostalIrregularId;
    }

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostal1() {
        return postal1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postal1 郵便番号1
     */
    public void setPostal1(final String postal1) {
        this.postal1 = postal1;
    }

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostal2() {
        return postal2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postal2 郵便番号2
     */
    public void setPostal2(final String postal2) {
        this.postal2 = postal2;
    }

    /**
     * 地方自治体コードを取得する
     *
     * @return 地方自治体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方自治体コードを設定する
     *
     * @param lgCode 地方自治体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /**
     * 原文書住所を取得する
     *
     * @return 原文書住所
     */
    public String getAddressOrg() {
        return addressOrg;
    }

    /**
     * 原文書住所を設定する
     *
     * @param addressOrg 原文書住所
     */
    public void setAddressOrg(final String addressOrg) {
        this.addressOrg = addressOrg;
    }

    /**
     * 住所名を取得する
     *
     * @return 住所名
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 住所名を設定する
     *
     * @param addressName 住所名
     */
    public void setAddressName(final String addressName) {
        this.addressName = addressName;
    }

    /**
     * 住所郵便番号までを取得する
     *
     * @return 住所郵便番号まで
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所郵便番号まで
     *
     * @param addressPostal 住所郵便番号まで
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /**
     * 住所番地までを取得する
     *
     * @return 住所番地まで
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地までを設定する
     *
     * @param addressBlock 住所番地まで
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /**
     * 正規テーブル複写処理完了を取得する
     *
     * @return 正規テーブル複写処理完了
     */
    public Boolean getIsAddPostal() {
        return isAddPostal;
    }

    /**
     * 正規テーブル複写処理完了を設定する
     *
     * @param isAddPostal 正規テーブル複写処理完了
     */
    public void setIsAddPostal(final Boolean isAddPostal) {
        this.isAddPostal = isAddPostal;
    }

    /**
     * 住居テーブル修正完了を取得する
     *
     * @return 住居テーブル修正完了
     */
    public Boolean getIsRepairRsdt() {
        return isRepairRsdt;
    }

    /**
     * 住居テーブル修正完了を設定する
     *
     * @param isRepairRsdt 住居テーブル修正完了
     */
    public void setIsRepairRsdt(final Boolean isRepairRsdt) {
        this.isRepairRsdt = isRepairRsdt;
    }

}
