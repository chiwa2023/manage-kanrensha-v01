package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;



/**
 * wk_tbl_master_person接続用Entity
 */
@Entity
@Table(name = "wk_tbl_master_person")
public class WkTblMasterPersonEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948,7,29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** テーブルId */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_master_person_id")
    private Integer wkTblMasterPersonId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblMasterPersonId() {
        return wkTblMasterPersonId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblMasterPersonId テーブルId
     */
    public void setWkTblMasterPersonId(final Integer wkTblMasterPersonId) {
        this.wkTblMasterPersonId = wkTblMasterPersonId;
    }

    /** ワークテーブルコード */
    @Column(name = "wk_tbl_master_person_code")
    private Integer wkTblMasterPersonCode = INIT_Integer;

    /**
     * ワークテーブルコードを取得する
     *
     * @return ワークテーブルコード
     */
    public Integer getWkTblMasterPersonCode() {
        return wkTblMasterPersonCode;
    }

    /**
     * ワークテーブルコードを設定する
     *
     * @param wkTblMasterPersonCode ワークテーブルコード
     */
    public void setWkTblMasterPersonCode(final Integer wkTblMasterPersonCode) {
        this.wkTblMasterPersonCode = wkTblMasterPersonCode;
    }

    /** 最新該否 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_Boolean;

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    @Override
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    @Override
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 処理完了該否 */
    @Column(name = "is_finish")
    private Boolean isFinish = INIT_Boolean;

    /**
     * 処理完了該否を取得する
     *
     * @return 処理完了該否
     */
    public Boolean getIsFinish() {
        return isFinish;
    }

    /**
     * 処理完了該否を設定する
     *
     * @param isFinish 処理完了該否
     */
    public void setIsFinish(final Boolean isFinish) {
        this.isFinish = isFinish;
    }

    /** 個人名 */
    @Column(name = "partner_name")
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
    @Column(name = "all_address")
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
    @Column(name = "person_shokugyou")
    private String personShokugyou = INIT_String;

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

    /** 住所郵便番号 */
    @Column(name = "address_postal")
    private String addressPostal = INIT_String;

    /**
     * 住所郵便番号を取得する
     *
     * @return 住所郵便番号
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所郵便番号を設定する
     *
     * @param addressPostal 住所郵便番号
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /** 住所番地 */
    @Column(name = "address_block")
    private String addressBlock = INIT_String;

    /**
     * 住所番地を取得する
     *
     * @return 住所番地
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地を設定する
     *
     * @param addressBlock 住所番地
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /** 住所建物 */
    @Column(name = "address_building")
    private String addressBuilding = INIT_String;

    /**
     * 住所建物を取得する
     *
     * @return 住所建物
     */
    public String getAddressBuilding() {
        return addressBuilding;
    }

    /**
     * 住所建物を設定する
     *
     * @param addressBuilding 住所建物
     */
    public void setAddressBuilding(final String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    /** 郵便番号1 */
    @Column(name = "postal1")
    private String postal1 = INIT_String;

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

    /** 郵便番号2 */
    @Column(name = "postal2")
    private String postal2 = INIT_String;

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

    /** 地方自治体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_String;

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

    /** 町字コード */
    @Column(name = "machiaza_id")
    private String machiazaId = INIT_String;

    /**
     * 町字コードを取得する
     *
     * @return 町字コード
     */
    public String getMachiazaId() {
        return machiazaId;
    }

    /**
     * 町字コードを設定する
     *
     * @param machiazaId 町字コード
     */
    public void setMachiazaId(final String machiazaId) {
        this.machiazaId = machiazaId;
    }

    /** 街区コード */
    @Column(name = "blk_id")
    private String blkId = INIT_String;

    /**
     * 街区コードを取得する
     *
     * @return 街区コード
     */
    public String getBlkId() {
        return blkId;
    }

    /**
     * 街区コードを設定する
     *
     * @param blkId 街区コード
     */
    public void setBlkId(final String blkId) {
        this.blkId = blkId;
    }

    /** 住居コード */
    @Column(name = "rsdt_id")
    private String rsdtId = INIT_String;

    /**
     * 住居コードを取得する
     *
     * @return 住居コード
     */
    public String getRsdtId() {
        return rsdtId;
    }

    /**
     * 住居コードを設定する
     *
     * @param rsdtId 住居コード
     */
    public void setRsdtId(final String rsdtId) {
        this.rsdtId = rsdtId;
    }

    /** 住居2コード */
    @Column(name = "rsdt2_id")
    private String rsdt2Id = INIT_String;

    /**
     * 住居2コードを取得する
     *
     * @return 住居2コード
     */
    public String getRsdt2Id() {
        return rsdt2Id;
    }

    /**
     * 住居2コードを設定する
     *
     * @param rsdt2Id 住居2コード
     */
    public void setRsdt2Id(final String rsdt2Id) {
        this.rsdt2Id = rsdt2Id;
    }

    /** 電話番号1 */
    @Column(name = "phon1")
    private String phon1 = INIT_String;

    /**
     * 電話番号1を取得する
     *
     * @return 電話番号1
     */
    public String getPhon1() {
        return phon1;
    }

    /**
     * 電話番号1を設定する
     *
     * @param phon1 電話番号1
     */
    public void setPhon1(final String phon1) {
        this.phon1 = phon1;
    }

    /** 電話番号2 */
    @Column(name = "phon2")
    private String phon2 = INIT_String;

    /**
     * 電話番号2を取得する
     *
     * @return 電話番号2
     */
    public String getPhon2() {
        return phon2;
    }

    /**
     * 電話番号2を設定する
     *
     * @param phon2 電話番号2
     */
    public void setPhon2(final String phon2) {
        this.phon2 = phon2;
    }

    /** 電話番号3 */
    @Column(name = "phon3")
    private String phon3 = INIT_String;

    /**
     * 電話番号3を取得する
     *
     * @return 電話番号3
     */
    public String getPhon3() {
        return phon3;
    }

    /**
     * 電話番号3を設定する
     *
     * @param phon3 電話番号3
     */
    public void setPhon3(final String phon3) {
        this.phon3 = phon3;
    }

    /** 電子メール */
    @Column(name = "email")
    private String email = INIT_String;

    /**
     * 電子メールを取得する
     *
     * @return 電子メール
     */
    public String getEmail() {
        return email;
    }

    /**
     * 電子メールを設定する
     *
     * @param email 電子メール
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** 代表(公式)url */
    @Column(name = "my_portal_url")
    private String myPortalUrl = INIT_String;

    /**
     * 代表(公式)urlを取得する
     *
     * @return 代表(公式)url
     */
    public String getMyPortalUrl() {
        return myPortalUrl;
    }

    /**
     * 代表(公式)urlを設定する
     *
     * @param myPortalUrl 代表(公式)url
     */
    public void setMyPortalUrl(final String myPortalUrl) {
        this.myPortalUrl = myPortalUrl;
    }

    /** SNSサービス名称 */
    @Column(name = "sns_service_name")
    private String snsServiceName = INIT_String;

    /**
     * SNSサービス名称を取得する
     *
     * @return SNSサービス名称
     */
    public String getSnsServiceName() {
        return snsServiceName;
    }

    /**
     * SNSサービス名称を設定する
     *
     * @param snsServiceName SNSサービス名称
     */
    public void setSnsServiceName(final String snsServiceName) {
        this.snsServiceName = snsServiceName;
    }

    /** SNSサービスアカウント */
    @Column(name = "sns_account")
    private String snsAccount = INIT_String;

    /**
     * SNSサービスアカウントを取得する
     *
     * @return SNSサービスアカウント
     */
    public String getSnsAccount() {
        return snsAccount;
    }

    /**
     * SNSサービスアカウントを設定する
     *
     * @param snsAccount SNSサービスアカウント
     */
    public void setSnsAccount(final String snsAccount) {
        this.snsAccount = snsAccount;
    }

    /** 姓名の姓 */
    @Column(name = "last_name")
    private String lastName = INIT_String;

    /**
     * 姓名の姓を取得する
     *
     * @return 姓名の姓
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 姓名の姓を設定する
     *
     * @param lastName 姓名の姓
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /** 姓名の名 */
    @Column(name = "first_name")
    private String firstName = INIT_String;

    /**
     * 姓名の名を取得する
     *
     * @return 姓名の名
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 姓名の名を設定する
     *
     * @param firstName 姓名の名
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /** 姓名のミドルネーム */
    @Column(name = "middle_name")
    private String middleName = INIT_String;

    /**
     * 姓名のミドルネームを取得する
     *
     * @return 姓名のミドルネーム
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 姓名のミドルネームを設定する
     *
     * @param middleName 姓名のミドルネーム
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /** 姓名の姓かな */
    @Column(name = "last_name_kana")
    private String lastNameKana = INIT_String;

    /**
     * 姓名の姓かなを取得する
     *
     * @return 姓名の姓かな
     */
    public String getLastNameKana() {
        return lastNameKana;
    }

    /**
     * 姓名の姓かなを設定する
     *
     * @param lastNameKana 姓名の姓かな
     */
    public void setLastNameKana(final String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    /** 姓名の名かな */
    @Column(name = "first_name_kana")
    private String firstNameKana = INIT_String;

    /**
     * 姓名の名かなを取得する
     *
     * @return 姓名の名かな
     */
    public String getFirstNameKana() {
        return firstNameKana;
    }

    /**
     * 姓名の名かなを設定する
     *
     * @param firstNameKana 姓名の名かな
     */
    public void setFirstNameKana(final String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

    /** 姓名のミドルネームかな */
    @Column(name = "middle_name_kana")
    private String middleNameKana = INIT_String;

    /**
     * 姓名のミドルネームかなを取得する
     *
     * @return 姓名のミドルネームかな
     */
    public String getMiddleNameKana() {
        return middleNameKana;
    }

    /**
     * 姓名のミドルネームかなを設定する
     *
     * @param middleNameKana 姓名のミドルネームかな
     */
    public void setMiddleNameKana(final String middleNameKana) {
        this.middleNameKana = middleNameKana;
    }

    /** 職業の業種 */
    @Column(name = "gyoushu")
    private String gyoushu = INIT_String;

    /**
     * 職業の業種を取得する
     *
     * @return 職業の業種
     */
    public String getGyoushu() {
        return gyoushu;
    }

    /**
     * 職業の業種を設定する
     *
     * @param gyoushu 職業の業種
     */
    public void setGyoushu(final String gyoushu) {
        this.gyoushu = gyoushu;
    }

    /** 職業の役職 */
    @Column(name = "yakushoku")
    private String yakushoku = INIT_String;

    /**
     * 職業の役職を取得する
     *
     * @return 職業の役職
     */
    public String getYakushoku() {
        return yakushoku;
    }

    /**
     * 職業の役職を設定する
     *
     * @param yakushoku 職業の役職
     */
    public void setYakushoku(final String yakushoku) {
        this.yakushoku = yakushoku;
    }

    /** ユーザ記述の職業 */
    @Column(name = "shokugyou_user_write")
    private String shokugyouUserWrite = INIT_String;

    /**
     * ユーザ記述の職業を取得する
     *
     * @return ユーザ記述の職業
     */
    public String getShokugyouUserWrite() {
        return shokugyouUserWrite;
    }

    /**
     * ユーザ記述の職業を設定する
     *
     * @param shokugyouUserWrite ユーザ記述の職業
     */
    public void setShokugyouUserWrite(final String shokugyouUserWrite) {
        this.shokugyouUserWrite = shokugyouUserWrite;
    }

    /** 企業番号 */
    @Column(name = "corp_no")
    private String corpNo = INIT_String;

    /**
     * 企業番号を取得する
     *
     * @return 企業番号
     */
    public String getCorpNo() {
        return corpNo;
    }

    /**
     * 企業番号を設定する
     *
     * @param corpNo 企業番号
     */
    public void setCorpNo(final String corpNo) {
        this.corpNo = corpNo;
    }

    /** 企業所在地 */
    @Column(name = "corp_address")
    private String corpAddress = INIT_String;

    /**
     * 企業所在地を取得する
     *
     * @return 企業所在地
     */
    public String getCorpAddress() {
        return corpAddress;
    }

    /**
     * 企業所在地を設定する
     *
     * @param corpAddress 企業所在地
     */
    public void setCorpAddress(final String corpAddress) {
        this.corpAddress = corpAddress;
    }

    /** 企業名 */
    @Column(name = "corp_name")
    private String corpName = INIT_String;

    /**
     * 企業名を取得する
     *
     * @return 企業名
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 企業名を設定する
     *
     * @param corpName 企業名
     */
    public void setCorpName(final String corpName) {
        this.corpName = corpName;
    }

    /** 外国籍該否 */
    @Column(name = "is_foreign")
    private Boolean isForeign = INIT_Boolean;

    /**
     * 外国籍該否を取得する
     *
     * @return 外国籍該否
     */
    public Boolean getIsForeign() {
        return isForeign;
    }

    /**
     * 外国籍該否を設定する
     *
     * @param isForeign 外国籍該否
     */
    public void setIsForeign(final Boolean isForeign) {
        this.isForeign = isForeign;
    }

    /** 反映有無 */
    @Column(name = "is_affected")
    private Boolean isAffected = INIT_Boolean;

    /**
     * 反映有無を取得する
     *
     * @return 反映有無
     */
    public Boolean getIsAffected() {
        return isAffected;
    }

    /**
     * 反映有無を設定する
     *
     * @param isAffected 反映有無
     */
    public void setIsAffected(final Boolean isAffected) {
        this.isAffected = isAffected;
    }

    /** 判定理由 */
    @Column(name = "judge_reason")
    private String judgeReason = INIT_String;

    /**
     * 判定理由を取得する
     *
     * @return 判定理由
     */
    public String getJudgeReason() {
        return judgeReason;
    }

    /**
     * 判定理由を設定する
     *
     * @param judgeReason 判定理由
     */
    public void setJudgeReason(final String judgeReason) {
        this.judgeReason = judgeReason;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_Integer;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    @Override
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    @Override
    public void setInsertUserId(final Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    /** 挿入ユーザコード */
    @Column(name = "insert_user_code")
    private Integer insertUserCode = INIT_Integer;

    /**
     * 挿入ユーザコードを取得する
     *
     * @return 挿入ユーザコード
     */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** 挿入ユーザ名称 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_String;

    /**
     * 挿入ユーザ名称を取得する
     *
     * @return 挿入ユーザ名称
     */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }

    /** 挿入日時 */
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp = INIT_Timestamp;

    /**
     * 挿入日時を取得する
     *
     * @return 挿入日時
     */
    @Override
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
    @Override
    public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** 無効ユーザId */
    @Column(name = "delete_user_id")
    private Integer deleteUserId = INIT_Integer;

    /**
     * 無効ユーザIdを取得する
     *
     * @return 無効ユーザId
     */
    @Override
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
    @Override
    public void setDeleteUserId(final Integer deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    /** 無効ユーザコード */
    @Column(name = "delete_user_code")
    private Integer deleteUserCode = INIT_Integer;

    /**
     * 無効ユーザコードを取得する
     *
     * @return 無効ユーザコード
     */
    @Override
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
    @Override
    public void setDeleteUserCode(final Integer deleteUserCode) {
        this.deleteUserCode = deleteUserCode;
    }

    /** 無効ユーザ名称 */
    @Column(name = "delete_user_name")
    private String deleteUserName = INIT_String;

    /**
     * 無効ユーザ名称を取得する
     *
     * @return 無効ユーザ名称
     */
    @Override
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
    @Override
    public void setDeleteUserName(final String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    /** 無効日時 */
    @Column(name = "delete_timestamp")
    private LocalDateTime deleteTimestamp = INIT_Timestamp;

    /**
     * 無効日時を取得する
     *
     * @return 無効日時
     */
    @Override
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    @Override
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

}
