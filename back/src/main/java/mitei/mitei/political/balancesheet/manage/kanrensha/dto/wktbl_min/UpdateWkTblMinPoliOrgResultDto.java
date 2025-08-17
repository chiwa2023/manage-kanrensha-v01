package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;

/**
 * 政治団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblMinPoliOrgResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblPartnerPoliOrgAddMinEntity wkTblPartnerPoliOrgAddMinEntity = new WkTblPartnerPoliOrgAddMinEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblPartnerPoliOrgAddMinEntity getWkTblPartnerPoliOrgAddMinEntity() {
        return wkTblPartnerPoliOrgAddMinEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblPartnerPoliOrgAddMinEntity 編集対象Entity
     */
    public void setWkTblPartnerPoliOrgAddMinEntity(
            final WkTblPartnerPoliOrgAddMinEntity wkTblPartnerPoliOrgAddMinEntity) {
        this.wkTblPartnerPoliOrgAddMinEntity = wkTblPartnerPoliOrgAddMinEntity;
    }

}
