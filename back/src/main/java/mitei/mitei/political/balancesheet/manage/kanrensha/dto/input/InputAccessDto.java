package mitei.mitei.political.balancesheet.manage.kanrensha.dto.input;

import java.io.Serializable;

/**
 * 連絡先入力Dto
 */
public class InputAccessDto implements Serializable { // NOPMD DataCalss

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 電話番号1 */
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

    /** 代表(公式)サイトurl */
    private String myPortalUrl = INIT_String;

    /**
     * 代表(公式)サイトurlを取得する
     *
     * @return 代表(公式)サイトurl
     */
    public String getMyPortalUrl() {
        return myPortalUrl;
    }

    /**
     * 代表(公式)サイトurlを設定する
     *
     * @param myPortalUrl 代表(公式)サイトurl
     */
    public void setMyPortalUrl(final String myPortalUrl) {
        this.myPortalUrl = myPortalUrl;
    }

    /** SNSサービスコード */
    private Integer snsServiceId = INIT_Integer;

    /**
     * SNSサービスコードを取得する
     *
     * @return SNSサービスコード
     */
    public Integer getSnsServiceId() {
        return snsServiceId;
    }

    /**
     * SNSサービスコードを設定する
     *
     * @param snsServiceId SNSサービスコード
     */
    public void setSnsServiceId(final Integer snsServiceId) {
        this.snsServiceId = snsServiceId;
    }

    /** SNSサービスコード */
    private Integer snsServiceCode = INIT_Integer;

    /**
     * SNSサービスコードを取得する
     *
     * @return SNSサービスコード
     */
    public Integer getSnsServiceCode() {
        return snsServiceCode;
    }

    /**
     * SNSサービスコードを設定する
     *
     * @param snsServiceCode SNSサービスコード
     */
    public void setSnsServiceCode(final Integer snsServiceCode) {
        this.snsServiceCode = snsServiceCode;
    }

    /** SNSサービス名称 */
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

    /** SNS玄関url */
    private String snsPortalUrl = INIT_String;

    /**
     * SNS玄関urlを取得する
     *
     * @return SNS玄関url
     */
    public String getSnsPortalUrl() {
        return snsPortalUrl;
    }

    /**
     * SNS玄関urlを設定する
     *
     * @param snsPortalUrl SNS玄関url
     */
    public void setSnsPortalUrl(final String snsPortalUrl) {
        this.snsPortalUrl = snsPortalUrl;
    }

    /** SNSサービスアカウント */
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

}
