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
 * wk_tbl_partner_person_history接続用Entity
 */
@Entity
@Table(name = "wk_tbl_partner_person_history")
public class WkTblPartnerPersonHistoryEntity  implements Serializable,AllTabeDataHistoryInterface{ // NOPMD DataClass

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
    @Column(name = "wk_partner_person_history_id")
    private Integer wkPartnerPersonHistoryId = INIT_Integer;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkPartnerPersonHistoryId() {
        return wkPartnerPersonHistoryId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkPartnerPersonHistoryId テーブルId
     */
    public void setWkPartnerPersonHistoryId(final Integer wkPartnerPersonHistoryId) {
        this.wkPartnerPersonHistoryId = wkPartnerPersonHistoryId;
    }

    /** 関連者企業・団体コード */
    @Column(name = "wk_partner_person_history_code")
    private Integer wkPartnerPersonHistoryCode = INIT_Integer;

    /**
     * 関連者企業・団体コードを取得する
     *
     * @return 関連者企業・団体コード
     */
    public Integer getWkPartnerPersonHistoryCode() {
        return wkPartnerPersonHistoryCode;
    }

    /**
     * 関連者企業・団体コードを設定する
     *
     * @param wkPartnerPersonHistoryCode 関連者企業・団体コード
     */
    public void setWkPartnerPersonHistoryCode(final Integer wkPartnerPersonHistoryCode) {
        this.wkPartnerPersonHistoryCode = wkPartnerPersonHistoryCode;
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

    /** 企業・団体名 */
    @Column(name = "partner_name")
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
    @Column(name = "all_address")
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
    @Column(name = "person_shokugyou")
    private String personShokugyou = INIT_String;

    /**
     * 企業・団体代表者を取得する
     *
     * @return 企業・団体代表者
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

    /**
     * 企業・団体代表者を設定する
     *
     * @param personShokugyou 企業・団体代表者
     */
    public void setPersonShokugyou(final String personShokugyou) {
        this.personShokugyou = personShokugyou;
    }

    /** 企業・団体関連者コード */
    @Column(name = "person_kanrensha_code")
    private String personKanrenshaCode = INIT_String;

    /**
     * 企業・団体関連者コードを取得する
     *
     * @return 企業・団体関連者コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 企業・団体関連者コードを設定する
     *
     * @param personKanrenshaCode 企業・団体関連者コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
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
