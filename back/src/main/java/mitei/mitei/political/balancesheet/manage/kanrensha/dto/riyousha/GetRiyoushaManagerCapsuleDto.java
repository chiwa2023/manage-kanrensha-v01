package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;

/**
 * 利用者運営者格納Dto
 */
public class GetRiyoushaManagerCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者運営者Entity */
    private RiyoushaManagerEntity riyoushaManagerEntity;

    /**
     * 利用者運営者Entityを取得する
     *
     * @return 利用者運営者Entity
     */
    public RiyoushaManagerEntity getRiyoushaManagerEntity() {
        return riyoushaManagerEntity;
    }

    /**
     * 利用者運営者Entityを設定する
     *
     * @param riyoushaManagerEntity 利用者運営者Entity
     */
    public void setRiyoushaManagerEntity(final RiyoushaManagerEntity riyoushaManagerEntity) {
        this.riyoushaManagerEntity = riyoushaManagerEntity;
    }

}
