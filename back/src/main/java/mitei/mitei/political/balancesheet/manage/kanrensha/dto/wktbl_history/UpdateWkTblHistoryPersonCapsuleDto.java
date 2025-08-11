package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;

/**
 * 個人一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblHistoryPersonCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblPartnerPersonHistoryEntity wkTblPartnerPersonHistoryEntity = new WkTblPartnerPersonHistoryEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblPartnerPersonHistoryEntity getWkTblPartnerPersonHistoryEntity() {
        return wkTblPartnerPersonHistoryEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblPartnerPersonHistoryEntity 編集対象Entity
     */
    public void setWkTblPartnerPersonHistoryEntity(
            final WkTblPartnerPersonHistoryEntity wkTblPartnerPersonHistoryEntity) {
        this.wkTblPartnerPersonHistoryEntity = wkTblPartnerPersonHistoryEntity;
    }

}
