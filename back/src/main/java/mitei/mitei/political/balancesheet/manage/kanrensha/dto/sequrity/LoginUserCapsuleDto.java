package mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

/**
 * ユーザログインDto
 */
public class LoginUserCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** ユーザId */
    private String userId = INIT_String;

    /** パスワード */
    private String password = INIT_String;

    /**
     * ユーザIdを取得する
     *
     * @return ユーザId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIdを設定する
     *
     * @param userId ユーザId
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * パスワードを取得する
     * 
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

}
