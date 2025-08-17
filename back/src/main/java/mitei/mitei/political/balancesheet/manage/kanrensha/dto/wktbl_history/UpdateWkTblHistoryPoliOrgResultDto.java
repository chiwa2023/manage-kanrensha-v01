package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;

/**
 * 政治団体一括登録履歴ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblHistoryPoliOrgResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblPartnerPoliOrgHistoryEntity wkTblPartnerPoliOrgHistoryEntity = new WkTblPartnerPoliOrgHistoryEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblPartnerPoliOrgHistoryEntity getWkTblPartnerPoliOrgHistoryEntity() {
        return wkTblPartnerPoliOrgHistoryEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblPartnerPoliOrgHistoryEntity 編集対象Entity
     */
    public void setWkTblPartnerPoliOrgHistoryEntity(
            final WkTblPartnerPoliOrgHistoryEntity wkTblPartnerPoliOrgHistoryEntity) {
        this.wkTblPartnerPoliOrgHistoryEntity = wkTblPartnerPoliOrgHistoryEntity;
    }

}
