package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;

/**
 * 個人一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblMinPersonResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblPartnerPersonAddMinEntity wkTblPartnerPersonAddMinEntity = new WkTblPartnerPersonAddMinEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblPartnerPersonAddMinEntity getWkTblPartnerPersonAddMinEntity() {
        return wkTblPartnerPersonAddMinEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblPartnerPersonAddMinEntity 編集対象Entity
     */
    public void setWkTblPartnerPersonAddMinEntity(final WkTblPartnerPersonAddMinEntity wkTblPartnerPersonAddMinEntity) {
        this.wkTblPartnerPersonAddMinEntity = wkTblPartnerPersonAddMinEntity;
    }

}
