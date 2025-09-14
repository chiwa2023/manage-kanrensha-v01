package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;

/**
 * 関連者企業団体格納Dto
 */
public class GetRiyoushaAdminCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者管理者Entity */
    private RiyoushaManagerEntity riyoushaadEntity;

    /**
     * 利用者管理者Entityを取得する
     *
     * @return 利用者管理者Entity
     */
    public RiyoushaManagerEntity getRiyoushaadEntity() {
        return riyoushaadEntity;
    }

    /**
     * 利用者管理者Entityを設定する
     *
     * @param riyoushaadEntity 利用者管理者Entity
     */
    public void setRiyoushaadEntity(final RiyoushaManagerEntity riyoushaadEntity) {
        this.riyoushaadEntity = riyoushaadEntity;
    }

}
