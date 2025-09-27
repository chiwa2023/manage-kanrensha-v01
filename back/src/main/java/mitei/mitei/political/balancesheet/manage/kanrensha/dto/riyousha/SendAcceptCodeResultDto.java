package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;

/**
 * 利用者組織個人承認コード取得格納Dto
 */
public class SendAcceptCodeResultDto extends FrameworkMessageAndResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 承認コードリスト */
    private List<RiyoushaInviteNewEntity> listAcceptCode = new ArrayList<>();

    /**
     * 承認コードリストを取得する
     *
     * @return 承認コードリスト
     */
    public List<RiyoushaInviteNewEntity> getListAcceptCode() {
        return listAcceptCode;
    }

    /**
     * 承認コードリストを設定する
     *
     * @param listAcceptCode 承認コードリスト
     */
    public void setListAcceptCode(final List<RiyoushaInviteNewEntity> listAcceptCode) {
        this.listAcceptCode = listAcceptCode;
    }

}
