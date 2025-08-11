package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblHistoryCorpCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblPartnerCorpHistoryEntity wkTblPartnerCorpHistoryEntity = new WkTblPartnerCorpHistoryEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblPartnerCorpHistoryEntity getWkTblPartnerCorpHistoryEntity() {
        return wkTblPartnerCorpHistoryEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblPartnerCorpHistoryEntity 編集対象Entity
     */
    public void setWkTblPartnerCorpHistoryEntity(final WkTblPartnerCorpHistoryEntity wkTblPartnerCorpHistoryEntity) {
        this.wkTblPartnerCorpHistoryEntity = wkTblPartnerCorpHistoryEntity;
    }

}
