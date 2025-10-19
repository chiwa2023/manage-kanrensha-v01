package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;

/**
 * 利用者API接続者格納Dto
 */
public class GetRiyoushaComradeCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者API接続者Entity */
    private RiyoushaComradeEntity riyoushaComradeEntity = new RiyoushaComradeEntity();

    /**
     * 利用者API接続者Entityを取得する
     *
     * @return 利用者API接続者Entity
     */
    public RiyoushaComradeEntity getRiyoushaComradeEntity() {
        return riyoushaComradeEntity;
    }

    /**
     * 利用者API接続者Entityを設定する
     *
     * @param riyoushaComradeEntity 利用者API接続者Entity
     */
    public void setRiyoushaComradeEntity(final RiyoushaComradeEntity riyoushaComradeEntity) {
        this.riyoushaComradeEntity = riyoushaComradeEntity;
    }

}
