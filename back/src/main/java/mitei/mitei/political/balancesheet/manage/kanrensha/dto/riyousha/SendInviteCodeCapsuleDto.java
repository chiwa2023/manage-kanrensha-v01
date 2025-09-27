package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;

/**
 * 利用者組織招待コード発行Service
 */
public class SendInviteCodeCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 組織Id */
    private Integer orgId = INIT_Integer;

    /** 組織紐づき権限 */
    private String orgRole = INIT_String;

    /** 個人メールアドレス */
    private String personMail = INIT_String;

    /**
     * 組織Idを取得する
     *
     * @return 組織Id
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 組織Idを設定する
     *
     * @param orgId 組織Id
     */
    public void setOrgId(final Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 組織紐づき権限を取得する
     *
     * @return 組織紐づき権限
     */
    public String getOrgRole() {
        return orgRole;
    }

    /**
     * 組織紐づき権限を設定する
     *
     * @param orgRole 組織紐づき権限
     */
    public void setOrgRole(final String orgRole) {
        this.orgRole = orgRole;
    }

    /**
     * 個人メールアドレスを取得する
     *
     * @return 個人メールアドレス
     */
    public String getPersonMail() {
        return personMail;
    }

    /**
     * 個人メールアドレスを設定する
     *
     * @param personMail 個人メールアドレス
     */
    public void setPersonMail(final String personMail) {
        this.personMail = personMail;
    }

}
