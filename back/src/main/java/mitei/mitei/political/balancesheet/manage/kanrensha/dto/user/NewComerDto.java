package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 新規ユーザDto
 */
public class NewComerDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** メールアドレス */
    private String mailAddress = INIT_String;

    /** 登録名 */
    private String nickName = INIT_String;

    /** パスワード */
    private String password = INIT_String;

    /** 登録用コード */
    private String registCode = INIT_String;

    /** ユーザ区分(ロール・権限) */
    private String role;

    /** 有効期限 */
    private LocalDateTime limitDateTime;

    /** メッセージ */
    private String message = INIT_String;

    /** 処理成功該否 */
    private Boolean isSuccess = INIT_Boolean; // NOPMD

    /**
     * メールアドレスを取得する
     *
     * @return メールアドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレスを設定する
     *
     * @param mailAddress メールアドレス
     */
    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * パスワードを取得する
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定する
     *
     * @param password パスワード
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * 登録用コードを取得する
     *
     * @return 登録用コード
     */
    public String getRegistCode() {
        return registCode;
    }

    /**
     * 登録用コードを設定する
     *
     * @param registCode 登録用コード
     */
    public void setRegistCode(final String registCode) {
        this.registCode = registCode;
    }

    /**
     * ユーザ区分(ロール・権限)を取得する
     *
     * @return ユーザ区分(ロール・権限)
     */
    public String getRole() {
        return role;
    }

    /**
     * ユーザ区分(ロール・権限)を設定する
     *
     * @param role ユーザ区分(ロール・権限)
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * 有効期限を取得する
     *
     * @return 有効期限
     */
    public LocalDateTime getLimitDateTime() {
        return limitDateTime;
    }

    /**
     * 有効期限を設定する
     *
     * @param limitDateTime 有効期限
     */
    public void setLimitDateTime(final LocalDateTime limitDateTime) {
        this.limitDateTime = limitDateTime;
    }

    /**
     * メッセージを取得する
     *
     * @return メッセージ
     */
    public String getMessage() {
        return message;
    }

    /**
     * メッセージを設定する
     *
     * @param message メッセージ
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 処理成功該否を取得する
     *
     * @return 処理成功該否
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * 処理成功該否を設定する
     *
     * @param isSuccess 処理成功該否
     */
    public void setIsSuccess(final Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * 登録名を取得する
     *
     * @return 登録名
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 登録名を設定する
     *
     * @param nickName 登録名
     */
    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

}
