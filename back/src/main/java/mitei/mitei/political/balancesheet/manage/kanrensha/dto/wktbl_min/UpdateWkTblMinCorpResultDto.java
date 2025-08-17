package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;

/**
 * 企業／団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblMinCorpResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblPartnerCorpAddMinEntity wkTblPartnerCorpAddMinEntity = new WkTblPartnerCorpAddMinEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblPartnerCorpAddMinEntity getWkTblPartnerCorpAddMinEntity() {
        return wkTblPartnerCorpAddMinEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblPartnerCorpAddMinEntity 編集対象Entity
     */
    public void setWkTblPartnerCorpAddMinEntity(final WkTblPartnerCorpAddMinEntity wkTblPartnerCorpAddMinEntity) {
        this.wkTblPartnerCorpAddMinEntity = wkTblPartnerCorpAddMinEntity;
    }

}
