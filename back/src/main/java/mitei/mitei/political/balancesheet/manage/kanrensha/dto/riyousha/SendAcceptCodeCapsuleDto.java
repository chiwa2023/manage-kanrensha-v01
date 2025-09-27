package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;

/**
 * 利用者組織個人承認コード入力格納Dto
 */
public class SendAcceptCodeCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 利用者組織と個人紐づけ承認コードEntity */
    private RiyoushaInviteNewEntity riyoushaInviteNewEntity;

    /**
     * 利用者組織と個人紐づけ承認コードEntityを取得する
     *
     * @return 利用者組織と個人紐づけ承認コードEntity
     */
    public RiyoushaInviteNewEntity getRiyoushaInviteNewEntity() {
        return riyoushaInviteNewEntity;
    }

    /**
     * 利用者組織と個人紐づけ承認コードEntityを設定する
     *
     * @param riyoushaInviteNewEntity 利用者組織と個人紐づけ承認コードEntity
     */
    public void setRiyoushaInviteNewEntity(final RiyoushaInviteNewEntity riyoushaInviteNewEntity) {
        this.riyoushaInviteNewEntity = riyoushaInviteNewEntity;
    }

    /** 入力承認コード */
    private String inputAcceptCode = INIT_String;

    /**
     * 入力承認コードを取得する
     *
     * @return 入力承認コード
     */
    public String getInputAcceptCode() {
        return inputAcceptCode;
    }

    /**
     * 入力承認コードを設定する
     *
     * @param inputAcceptCode 入力承認コード
     */
    public void setInputAcceptCode(final String inputAcceptCode) {
        this.inputAcceptCode = inputAcceptCode;
    }

}
