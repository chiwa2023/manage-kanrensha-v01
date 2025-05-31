package mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025;

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
 * login_history_2025接続用Entity
 */
@Entity
@Table(name = "login_history_2025")
public class LoginHistory2025Entity  implements Serializable{ // NOPMD DataClass

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

    /** テーブルid */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_history_id")
    private Integer loginHistoryId = INIT_Integer;

    /**
     * テーブルidを取得する
     *
     * @return テーブルid
     */
    public Integer getLoginHistoryId() {
        return loginHistoryId;
    }

    /**
     * テーブルidを設定する
     *
     * @param loginHistoryId テーブルid
     */
    public void setLoginHistoryId(final Integer loginHistoryId) {
        this.loginHistoryId = loginHistoryId;
    }

    /** メールアドレス */
    @Column(name = "email")
    private String email = INIT_String;

    /**
     * メールアドレスを取得する
     *
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定する
     *
     * @param email メールアドレス
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** ログイン可否 */
    @Column(name = "is_success")
    private Boolean isSuccess = INIT_Boolean;

    /**
     * ログイン可否を取得する
     *
     * @return ログイン可否
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * ログイン可否を設定する
     *
     * @param isSuccess ログイン可否
     */
    public void setIsSuccess(final Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /** ログイン失敗理由 */
    @Column(name = "fail_reason")
    private String failReason = INIT_String;

    /**
     * ログイン失敗理由を取得する
     *
     * @return ログイン失敗理由
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * ログイン失敗理由を設定する
     *
     * @param failReason ログイン失敗理由
     */
    public void setFailReason(final String failReason) {
        this.failReason = failReason;
    }

    /** 無効状態 */
    @Column(name = "disabled")
    private Boolean disabled = INIT_Boolean;

    /**
     * 無効状態を取得する
     *
     * @return 無効状態
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * 無効状態を設定する
     *
     * @param disabled 無効状態
     */
    public void setDisabled(final Boolean disabled) {
        this.disabled = disabled;
    }

    /** 無効状態理由 */
    @Column(name = "diabled_reason")
    private String diabledReason = INIT_String;

    /**
     * 無効状態理由を取得する
     *
     * @return 無効状態理由
     */
    public String getDiabledReason() {
        return diabledReason;
    }

    /**
     * 無効状態理由を設定する
     *
     * @param diabledReason 無効状態理由
     */
    public void setDiabledReason(final String diabledReason) {
        this.diabledReason = diabledReason;
    }

    /** ログイン日時 */
    @Column(name = "login_time")
    private LocalDateTime loginTime = INIT_Timestamp;

    /**
     * ログイン日時を取得する
     *
     * @return ログイン日時
     */
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    /**
     * ログイン日時を設定する
     *
     * @param loginTime ログイン日時
     */
    public void setLoginTime(final LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    /** パスワード変更時間 */
    @Column(name = "pass_change_time")
    private LocalDateTime passChangeTime = INIT_Timestamp;

    /**
     * パスワード変更時間を取得する
     *
     * @return パスワード変更時間
     */
    public LocalDateTime getPassChangeTime() {
        return passChangeTime;
    }

    /**
     * パスワード変更時間を設定する
     *
     * @param passChangeTime パスワード変更時間
     */
    public void setPassChangeTime(final LocalDateTime passChangeTime) {
        this.passChangeTime = passChangeTime;
    }

}
