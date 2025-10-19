package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_combine;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblCombineOrgCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblPartnerCombineOrgEntity wkTblPartnerCombineOrgEntity = new WkTblPartnerCombineOrgEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblPartnerCombineOrgEntity getWkTblPartnerCombineOrgEntity() {
        return wkTblPartnerCombineOrgEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblPartnerCombineOrgEntity 編集対象Entity
     */
    public void setWkTblPartnerCombineOrgEntity(final WkTblPartnerCombineOrgEntity wkTblPartnerCombineOrgEntity) {
        this.wkTblPartnerCombineOrgEntity = wkTblPartnerCombineOrgEntity;
    }

}
